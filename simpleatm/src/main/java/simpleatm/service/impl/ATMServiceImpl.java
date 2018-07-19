package simpleatm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import simpleatm.dao.ATMDao;
import simpleatm.exception.ServiceException;
import simpleatm.model.BalanceDTO;
import simpleatm.model.BanknotePile;
import simpleatm.model.ServiceErrorCode;
import simpleatm.model.WithdrawalDTO;
import simpleatm.model.entity.Account;
import simpleatm.model.entity.NoteHolder;
import simpleatm.service.ATMService;

import java.util.ArrayList;
import java.util.List;

import static simpleatm.model.ATMServiceErrorCodes.*;

/**
 * The ATMServiceImpl is the implementation of {@link ATMService}. It performs the operations required by the front
 * end and interacts with the database via the {@link ATMDAO}.
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */
@Qualifier("atmService")
@Service
public class ATMServiceImpl implements ATMService {

    @Autowired
    private ATMDao atmDao;

    @Override
    public BalanceDTO getBalance(Long accountNumber, Long pin) {
        Account account = atmDao.getAccount(accountNumber, pin);
        return new BalanceDTO(account.getBalance(), account.getOverdraftAllowance() + account.getBalance());
    }

    /**
     * Handles the withdrawal. This method is transactional to ensure that if there is a failure in any step the
     * entire transaction rolls back.
     *
     * Usual parameter and return details are described in the interface javadoc.
     *
     */
    @Transactional
    @Override
    public WithdrawalDTO withdraw(Long accountNumber, Long pin, Long amountToWithdraw) {
        // Initial paramater checks
        if (amountToWithdraw <= 0) {
            throw new ServiceException(new ServiceErrorCode(ATM_ERROR_CODE_POSITIVE_AMOUNT_REQD, "The amount to withdraw must be a positive value!"));
        }

        // Initialise variables
        Long runningTotal = 0L;
        List<BanknotePile> banknotesDispensed = new ArrayList<>();
        // This will throw an exception if the account and pin number are not correct
        Account account = atmDao.getAccount(accountNumber, pin);

        // Check the account has sufficient funds to withdraw amountToWithdraw
        Long maxWithdrawalAllowed = account.getBalance() + account.getOverdraftAllowance();
        if (maxWithdrawalAllowed <= 0 || maxWithdrawalAllowed < amountToWithdraw) {
            throw new ServiceException(new ServiceErrorCode(ATM_ERROR_CODE_ACCOUNT_INSUFFICIENT_FUNDS, "Not enough funds in your account"));
        }

        List<NoteHolder> noteHolderList = atmDao.findAvailableFunds();
        for (NoteHolder noteHolder : noteHolderList) {
            Long amount = amountToWithdraw - runningTotal;

            // This is a short list of 4 itenms (in a long or unpredictable length list I would break out of the for loop if the amount is 0
            if (amount > 0) {
                Long notesRequired = amount / noteHolder.getNoteValue();
                Long notesUsed = 0L;

                // If there are sufficient notes to reach the notes required fulfil the request
                if (notesRequired <= noteHolder.getNoteQuantity()) {
                    notesUsed = notesRequired;
                } else if (noteHolder.getNoteQuantity() > 0) { // we'll need all the available notes in this denomination
                    notesUsed = noteHolder.getNoteQuantity();
                }

                // If we have updated the notesUsed we need to track which notes are to be dispensed
                if (notesUsed > 0) {
                    noteHolder.setNoteQuantity(noteHolder.getNoteQuantity() - notesUsed);
                    banknotesDispensed.add(new BanknotePile(noteHolder.getNoteValue(), notesUsed));
                    runningTotal += notesUsed * noteHolder.getNoteValue();
                }
            }
        }

        // If we never reach the amount to withdraw then the ATM has insufficient funds
        if (runningTotal < amountToWithdraw) {
            throw new ServiceException(new ServiceErrorCode(ATM_ERROR_CODE_ATM_INSUFFICIENT_FUNDS, "Not enough funds in the ATM!"));
        }

        account.setBalance(account.getBalance() - runningTotal);

        // Update the database
        atmDao.updateAfterWithdrawal(noteHolderList, account);
        return new WithdrawalDTO(banknotesDispensed, account.getBalance());
    }

}
