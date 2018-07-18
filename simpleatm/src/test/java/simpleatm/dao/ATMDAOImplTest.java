package simpleatm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import simpleatm.ATMTestConstants;
import simpleatm.exception.ServiceException;
import simpleatm.model.entity.Account;
import simpleatm.model.entity.NoteHolder;
import simpleatm.model.entity.NoteHolderRepository;
import simpleatm.util.ATMTestUtil;

import java.util.List;

import static org.junit.Assert.*;
import static simpleatm.ATMTestConstants.ZERO;
import static simpleatm.model.ATMServiceErrorCodes.ATM_ERROR_CODE_DUPLICATE_ACCOUNT_PIN;
import static simpleatm.model.ATMServiceErrorCodes.ATM_ERROR_CODE_NO_SUCH_ACCOUNT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ATMDAOImplTest {
    @Autowired
    ATMDao atmDao;

    @Autowired
    NoteHolderRepository noteHolderRepository;

    @Test
    public void testGetAccountExists() {
        Account account = atmDao.getAccount(ATMTestConstants.ACCOUNT_1, ATMTestConstants.PIN_1);
        assertNotNull(account);
        assertNotNull(account.getAccountNumber());
        assertNotNull(account.getPin());
        assertNotNull(account.getOverdraftAllowance());
        assertNotNull(account.getBalance());
    }

    @Test
    public void testGetAccountDuplicate() {
        try {
            atmDao.getAccount(ATMTestConstants.BAD_ACCOUNT_1, ATMTestConstants.BAD_PIN_1);
        } catch (ServiceException se) {
            assertNotNull(se);
            assertEquals(ATM_ERROR_CODE_DUPLICATE_ACCOUNT_PIN, se.getServiceErrorCode().getCode());
        }
    }

    @Test
    public void testGetAccountNotExists() {
        try {
            atmDao.getAccount(ATMTestConstants.INVALID_ACCOUNT, ATMTestConstants.INVALID_PIN);
        } catch (ServiceException se) {
            assertNotNull(se);
            assertEquals(ATM_ERROR_CODE_NO_SUCH_ACCOUNT, se.getServiceErrorCode().getCode());
        }
    }

    @Test
    public void testGetAccountExistsBadPin() {
        try {
            atmDao.getAccount(ATMTestConstants.ACCOUNT_1, ATMTestConstants.INVALID_PIN);
        } catch (ServiceException se) {
            assertNotNull(se);
            assertEquals(ATM_ERROR_CODE_NO_SUCH_ACCOUNT, se.getServiceErrorCode().getCode());
        }
    }

    @Test
    public void testFindAvailableFundsStart() {
        ATMTestUtil.initialiseNoteHolderRepository(noteHolderRepository);
        List<NoteHolder> noteHolderList = atmDao.findAvailableFunds();
        Long runningTotal = 0L;
        for (NoteHolder noteHolder : noteHolderList) {
            if (noteHolder.getNoteValue().equals(ATMTestConstants.NOTE_VALUE_50)) {
                assertEquals(ATMTestConstants.STARTING_QUANTITY_50, noteHolder.getNoteQuantity());
            } else if (noteHolder.getNoteValue().equals(ATMTestConstants.NOTE_VALUE_20)) {
                assertEquals(noteHolder.getNoteQuantity(), ATMTestConstants.STARTING_QUANTITY_20);
            } else if (noteHolder.getNoteValue().equals(ATMTestConstants.NOTE_VALUE_10)) {
                assertEquals(noteHolder.getNoteQuantity(), ATMTestConstants.STARTING_QUANTITY_10);
            } else {
                assertEquals(noteHolder.getNoteQuantity(), ATMTestConstants.STARTING_QUANTITY_5);
            }
            runningTotal += noteHolder.getNoteQuantity() * noteHolder.getNoteValue();
        }
        Long expectedATMTotal = 2000L;
        assertEquals(expectedATMTotal, runningTotal);
    }

    @Test
    public void testUpdateAfterWithdrawal() {

        Account account = atmDao.getAccount(ATMTestConstants.ACCOUNT_1, ATMTestConstants.PIN_1);
        List<NoteHolder> noteHolderList = atmDao.findAvailableFunds();

        for (NoteHolder noteHolder : noteHolderList) {
            if (noteHolder.getNoteValue().equals(ATMTestConstants.NOTE_VALUE_50)) {
                noteHolder.setNoteQuantity(ZERO);
                break;
            }
        }
        account.setBalance(ZERO);
        atmDao.updateAfterWithdrawal(noteHolderList, account);
        account = atmDao.getAccount(ATMTestConstants.ACCOUNT_1, ATMTestConstants.PIN_1);

        assertEquals(ZERO, account.getBalance());

        noteHolderList = atmDao.findAvailableFunds();
        // There should be no note information returned where the note are all dispensed so expect that the
        // one set to 0 does not exist in the list
        for (NoteHolder noteHolder : noteHolderList) {
            if (noteHolder.getNoteValue().equals(ATMTestConstants.NOTE_VALUE_50)) {
                assertNull("We should never reach this, this note should not be returned", noteHolder);
            }
        }


    }


}
