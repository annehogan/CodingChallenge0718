package simpleatm.dao;

import simpleatm.model.entity.Account;
import simpleatm.model.entity.NoteHolder;

import java.util.List;

public interface ATMDao {
    public Account getAccount(Long accountNumber, Long pin);

    public List<NoteHolder> findAvailableFunds();

    public void updateAfterWithdrawal(List<NoteHolder> noteHolderList, Account account);
}
