package simpleatm.service;

import simpleatm.model.BalanceDTO;
import simpleatm.model.WithdrawalDTO;

public interface ATMService {
    public BalanceDTO getBalance(Long accountNumber, Long pin);

    public WithdrawalDTO withdraw(Long accountNumber, Long pin, Long amountToWithdraw);
}
