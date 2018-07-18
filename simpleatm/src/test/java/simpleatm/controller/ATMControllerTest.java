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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static simpleatm.ATMTestConstants.ACCOUNT_1;
import static simpleatm.ATMTestConstants.PIN_1;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ATMControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testAccountExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + ACCOUNT_1.toString() + "/pin/" + PIN_1.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(CoreMatchers.containsString("currentBalance")))
                .andExpect(content().string(CoreMatchers.containsString("maxWithdrawal")));
    }

    // TODO test rest of the interface
//    @Test
//    public void testBalanceAccountExists() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/balance")
//                .param("accountNumber", ACCOUNT_1.toString())
//                .param("pin", PIN_1.toString()))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(content().string(CoreMatchers.containsString("currentBalance")))
//                .andExpect(content().string(CoreMatchers.containsString("maxWithdrawal")));
//    }

}
