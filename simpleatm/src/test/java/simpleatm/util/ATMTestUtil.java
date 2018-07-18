package simpleatm.util;

import simpleatm.model.entity.Account;
import simpleatm.model.entity.AccountRepository;
import simpleatm.model.entity.NoteHolder;
import simpleatm.model.entity.NoteHolderRepository;

import java.util.List;

import static simpleatm.ATMTestConstants.*;

public class ATMTestUtil {

    private ATMTestUtil() {
    }

    public static final void initialiseNoteHolderRepository(NoteHolderRepository noteHolderRepository) {
        List<NoteHolder> noteHolderList = noteHolderRepository.findAll();
        for (NoteHolder noteHolder : noteHolderList) {
            if (noteHolder.getNoteValue().equals(NOTE_VALUE_50)) {
                noteHolder.setNoteQuantity(STARTING_QUANTITY_50);
                noteHolderRepository.save(noteHolder);
            } else if (noteHolder.getNoteValue().equals(NOTE_VALUE_20)) {
                noteHolder.setNoteQuantity(STARTING_QUANTITY_20);
                noteHolderRepository.save(noteHolder);
            } else if (noteHolder.getNoteValue().equals(NOTE_VALUE_10)) {
                noteHolder.setNoteQuantity(STARTING_QUANTITY_10);
                noteHolderRepository.save(noteHolder);
            } else {
                noteHolder.setNoteQuantity(STARTING_QUANTITY_5);
                noteHolderRepository.save(noteHolder);
            }
        }
    }

    public static final void initialiseAccountRepository(AccountRepository accountRepository) {
        List<Account> accounts = accountRepository.findAll();
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(ACCOUNT_1)) {
                account.setBalance(BALANCE_1);
                accountRepository.save(account);
            } else if (account.getAccountNumber().equals(ACCOUNT_2)) {
                account.setBalance(BALANCE_2);
                accountRepository.save(account);
            } else if (account.getAccountNumber().equals(ACCOUNT_3)) {
                account.setBalance(BALANCE_3);
                accountRepository.save(account);
            }
        }
    }
}
