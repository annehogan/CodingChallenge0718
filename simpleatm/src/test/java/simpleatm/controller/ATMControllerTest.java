package simpleatm.controller;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import simpleatm.model.entity.AccountRepository;
import simpleatm.model.entity.NoteHolderRepository;
import simpleatm.util.ATMTestUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static simpleatm.ATMTestConstants.*;
import static simpleatm.model.ATMServiceErrorCodes.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ATMControllerTest {

    private static final String ACCOUNT_PATH_STRING = "/account/";
    private static final String PIN_PATH_STRING = "/pin/";
    private static final String WITHDRAW_PATH_STRING = "/withdraw/";
    private static final String CHARSET_STRING = "application/json;charset=UTF-8";
    private static final String BALANCEDTO_CURRENT_BALANCE = "\"currentBalance\"";
    private static final String BALANCEDTO_MAX_WITHDRAWAL = "\"maxWithdrawal\"";
    private static final String WITHDRAWALDTO_BANKNOTE_PILES = "\"banknotePiles\"";
    private static final String WITHDRAWALDTO_REMAINING_BALANCE = "\"remainingBalance\"";
    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    NoteHolderRepository noteHolderRepository;

    // TODO expand the tests to test for actual values,application test for negative scenarios.
    @Test
    @SuppressWarnings("unchecked")
    public void testAccountExists() throws Exception {
        ATMTestUtil.initialiseAccountRepository(accountRepository);
        mockMvc.perform(MockMvcRequestBuilders.get(ACCOUNT_PATH_STRING + ACCOUNT_1.toString() + PIN_PATH_STRING + PIN_1.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CHARSET_STRING))
                .andExpect(content().string(CoreMatchers.containsString(BALANCEDTO_CURRENT_BALANCE + ":" + BALANCE_1)))
                .andExpect(content().string(CoreMatchers.containsString(BALANCEDTO_MAX_WITHDRAWAL)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testMultipleAccountsExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ACCOUNT_PATH_STRING + BAD_ACCOUNT_1.toString() + PIN_PATH_STRING + BAD_PIN_1.toString()))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(CHARSET_STRING))
                .andExpect(content().string(CoreMatchers.containsString(ATM_ERROR_CODE_DUPLICATE_ACCOUNT_PIN)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAccountDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ACCOUNT_PATH_STRING + INVALID_ACCOUNT.toString() + PIN_PATH_STRING + INVALID_PIN.toString()))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(CHARSET_STRING))
                .andExpect(content().string(CoreMatchers.containsString(ATM_ERROR_CODE_NO_SUCH_ACCOUNT)));
    }


    @Test
    @SuppressWarnings("unchecked")
    public void testWithdrawalInsufficientFundsInAccount() throws Exception {
        ATMTestUtil.initialiseNoteHolderRepository(noteHolderRepository);
        ATMTestUtil.initialiseAccountRepository(accountRepository);
        mockMvc.perform(MockMvcRequestBuilders.get(ACCOUNT_PATH_STRING + ACCOUNT_2.toString() + PIN_PATH_STRING + PIN_2.toString() + WITHDRAW_PATH_STRING + (BALANCE_2 + OVERDRAFT_2 + 5L)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(CHARSET_STRING))
                .andExpect(content().string(CoreMatchers.containsString(ATM_ERROR_CODE_ACCOUNT_INSUFFICIENT_FUNDS)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testWithdrawalNoFundsInATM() throws Exception {
        ATMTestUtil.initialiseNoteHolderRepository(noteHolderRepository);
        ATMTestUtil.initialiseAccountRepository(accountRepository);
        mockMvc.perform(MockMvcRequestBuilders.get(ACCOUNT_PATH_STRING + ACCOUNT_3.toString() + PIN_PATH_STRING + PIN_3.toString() + WITHDRAW_PATH_STRING + (ATM_LIMIT + 5L)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(CHARSET_STRING))
                .andExpect(content().string(CoreMatchers.containsString(ATM_ERROR_CODE_ATM_INSUFFICIENT_FUNDS)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testWithdrawal() throws Exception {
        ATMTestUtil.initialiseNoteHolderRepository(noteHolderRepository);
        ATMTestUtil.initialiseAccountRepository(accountRepository);
        mockMvc.perform(MockMvcRequestBuilders.get(ACCOUNT_PATH_STRING + ACCOUNT_1.toString() + PIN_PATH_STRING + PIN_1.toString() + WITHDRAW_PATH_STRING + BALANCE_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CHARSET_STRING))
                .andExpect(content().string(CoreMatchers.containsString(WITHDRAWALDTO_BANKNOTE_PILES)))
                .andExpect(content().string(CoreMatchers.containsString(WITHDRAWALDTO_REMAINING_BALANCE)));
    }

}
