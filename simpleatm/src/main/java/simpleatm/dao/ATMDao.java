package simpleatm.dao;

import simpleatm.model.entity.Account;
import simpleatm.model.entity.NoteHolder;

import java.util.List;

/**
 * ATMDao - interface for accessing the data layer
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

public interface ATMDao {
    /**
     * getAccount - retrieves the appropriate account details
     *
     * @param accountNumber
     * @param pin
     * @return {@link Account}
     */
    Account getAccount(Long accountNumber, Long pin);

    /**
     * findAvailableFunds - retrieves the appropriate account details
     *
     * @return a list of {@link NoteHolder}
     */
    List<NoteHolder> findAvailableFunds();

    /**
     * updateAfterWithdrawal - makes the updates to the database for the changes to the Account and the NoteHolder list
     *
     * @param noteHolderList
     * @param account
     */
    void updateAfterWithdrawal(List<NoteHolder> noteHolderList, Account account);
}
