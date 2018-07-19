package simpleatm.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import simpleatm.dao.ATMDao;
import simpleatm.exception.ServiceException;
import simpleatm.model.ATMServiceErrorCodes;
import simpleatm.model.ServiceErrorCode;
import simpleatm.model.entity.Account;
import simpleatm.model.entity.AccountRepository;
import simpleatm.model.entity.NoteHolder;
import simpleatm.model.entity.NoteHolderRepository;

import java.util.List;


/**
 * ATMDaoImpl - handles the data access, manages the objects and their updates.
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

@Repository
@Qualifier("atmDao")
public class ATMDaoImpl implements ATMDao {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    NoteHolderRepository noteHolderRepository;

    @Override
    public Account getAccount(Long accountNumber, Long pin) {
        List<Account> accounts = accountRepository.findByNumberAndPin(accountNumber, pin);
        if (accounts.size() > 1) {
            throw new ServiceException(new ServiceErrorCode(ATMServiceErrorCodes.ATM_ERROR_CODE_DUPLICATE_ACCOUNT_PIN, "There are multiple accounts found for this pin and account number"));
        } else if (accounts.isEmpty()) {
            throw new ServiceException(new ServiceErrorCode(ATMServiceErrorCodes.ATM_ERROR_CODE_NO_SUCH_ACCOUNT, "There is no account found for this pin and account number"));
        }

        return accounts.get(0);
    }

    @Override
    public List<NoteHolder> findAvailableFunds() {
        return noteHolderRepository.findAvailableFundsOrderByNoteValueDesc();
    }

    /**
     * This must be transactional as all the changes are carried out here. If we fail making any part of update, the
     * entire transaction must be rolled back.
     * @param noteHolderList
     * @param account
     */
    @Transactional
    @Override
    public void updateAfterWithdrawal(List<NoteHolder> noteHolderList, Account account) {

        for (NoteHolder noteHolder : noteHolderList) {
            noteHolderRepository.save(noteHolder);
        }

        accountRepository.save(account);
    }
}
