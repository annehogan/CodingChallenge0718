package simpleatm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import simpleatm.dao.ATMDao;
import simpleatm.exception.ServiceException;
import simpleatm.model.BalanceDTO;
import simpleatm.model.WithdrawalDTO;
import simpleatm.model.entity.AccountRepository;
import simpleatm.model.entity.NoteHolderRepository;
import simpleatm.util.ATMTestUtil;

import static org.junit.Assert.*;
import static simpleatm.ATMTestConstants.*;
import static simpleatm.model.ATMServiceErrorCodes.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ATMServiceImplTest {

    @Autowired
    ATMService atmService;

    @Autowired
    ATMDao atmDao;

    @Autowired
    NoteHolderRepository noteHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    private void setUp() {
        ATMTestUtil.initialiseNoteHolderRepository(noteHolderRepository);
        ATMTestUtil.initialiseAccountRepository(accountRepository);
    }

    @Test
    public void testWithdrawSuccessToTheATMLimit() {
        setUp();
        WithdrawalDTO withdrawalDTO = atmService.withdraw(ACCOUNT_3, PIN_3, BALANCE_3);
        assertNotNull(withdrawalDTO);
        assertEquals(ZERO, withdrawalDTO.getRemainingBalance());
    }

    @Test
    public void testWithdrawFailureTheATMLimit() {
        setUp();
        WithdrawalDTO withdrawalDTO = null;
        try {
            withdrawalDTO = atmService.withdraw(ACCOUNT_3, PIN_3, ATM_LIMIT + 5L);
        } catch (ServiceException se) {
            assertNotNull(se);
            assertEquals(ATM_ERROR_CODE_ATM_INSUFFICIENT_FUNDS, se.getServiceErrorCode().getCode());
        }
        assertNull(EXPECTED_NULL_STRING, withdrawalDTO);
    }

    @Test
    public void testGetBalance() {
        setUp();
        BalanceDTO balance = atmService.getBalance(ACCOUNT_1, PIN_1);
        assertNotNull(balance);
        assertNotNull(balance.getCurrentBalance());
        assertNotNull(balance.getMaxWithdrawal());
    }

    @Test
    public void testWithdrawANegativeValue() {
        setUp();
        WithdrawalDTO withdrawalDTO = null;
        try {
            withdrawalDTO = atmService.withdraw(ACCOUNT_1, PIN_1, -1L);
        } catch (ServiceException se) {
            assertNotNull(se);
            assertEquals(ATM_ERROR_CODE_POSITIVE_AMOUNT_REQD, se.getServiceErrorCode().getCode());
        }
        assertNull(EXPECTED_NULL_STRING, withdrawalDTO);
        try {
            withdrawalDTO = atmService.withdraw(ACCOUNT_1, PIN_1, 0L);
        } catch (ServiceException se) {
            assertNotNull(se);
            assertEquals(ATM_ERROR_CODE_POSITIVE_AMOUNT_REQD, se.getServiceErrorCode().getCode());
        }
        assertNull(EXPECTED_NULL_STRING, withdrawalDTO);
    }

    @Test
    public void testWithdrawMoreThanAccountHolds() {
        setUp();
        WithdrawalDTO withdrawalDTO = null;
        try {
            withdrawalDTO = atmService.withdraw(ACCOUNT_2, PIN_2, BALANCE_2 + OVERDRAFT_2 + 5L);
        } catch (ServiceException se) {
            assertNotNull(se);
            assertEquals(ATM_ERROR_CODE_ACCOUNT_INSUFFICIENT_FUNDS, se.getServiceErrorCode().getCode());
        }
        assertNull(EXPECTED_NULL_STRING, withdrawalDTO);
    }

    @Test
    public void testWithdrawSuccess() {
        withdrawSuccess(NOTE_VALUE_5);
        withdrawSuccess(NOTE_VALUE_10);
        withdrawSuccess(NOTE_VALUE_20);
        withdrawSuccess(NOTE_VALUE_50);
        withdrawSuccess(ONE_OF_EACH);
    }

    private void withdrawSuccess(Long withdrawalAmount) {
        setUp();
        BalanceDTO balance = atmService.getBalance(ACCOUNT_1, PIN_1);
        WithdrawalDTO withdrawalDTO = atmService.withdraw(ACCOUNT_1, PIN_1, withdrawalAmount);
        assertNotNull(withdrawalDTO);
        Long expectedBalance = balance.getCurrentBalance() - withdrawalAmount;
        assertEquals(expectedBalance, withdrawalDTO.getRemainingBalance());
    }

    @Test
    public void testWithdrawSuccessToTheAccountLimit() {
        setUp();
        WithdrawalDTO withdrawalDTO = atmService.withdraw(ACCOUNT_2, PIN_2, BALANCE_2 + OVERDRAFT_2);
        assertNotNull(withdrawalDTO);
        Long expectedBalance = -1L * OVERDRAFT_2;
        assertEquals(expectedBalance, withdrawalDTO.getRemainingBalance());
    }

}
