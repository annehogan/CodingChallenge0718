package simpleatm.service;

import simpleatm.model.BalanceDTO;
import simpleatm.model.WithdrawalDTO;

/**
 * ATMService - Service interface to separate the controller and dao from any implementation details.
 */
public interface ATMService {
    /**
     * Gets the balance for the given account number and pin, throws {@link ServiceException} for expected problems -
     * no match for account and pin etc.
     *
     * @param accountNumber
     * @param pin
     * @return {@link BalanceDTO}
     */
    BalanceDTO getBalance(Long accountNumber, Long pin);

    /**
     * Handles the withdrawal of amountToWithdraw from account with accountNumber and pin. Throws
     * {@link ServiceException} for expected problems - not enough funds in account, no account matches account number
     * and pin, not enough funds in ATM etc.
     *
     * @param accountNumber
     * @param pin
     * @param amountToWithdraw
     * @return {@link WithdrawalDTO}
     */
    WithdrawalDTO withdraw(Long accountNumber, Long pin, Long amountToWithdraw);
}
