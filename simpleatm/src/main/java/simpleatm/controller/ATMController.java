package simpleatm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import simpleatm.exception.ServiceException;
import simpleatm.model.ATMServiceErrorCodes;
import simpleatm.model.BalanceDTO;
import simpleatm.model.ServiceErrorCode;
import simpleatm.model.WithdrawalDTO;
import simpleatm.service.ATMService;

@RestController
public class ATMController {

    @Autowired
    private ATMService atmService;

    @RequestMapping(value = "/account/{ACCOUNT_NUMBER}/pin/{PIN}", method = RequestMethod.GET)
    public BalanceDTO balance(@PathVariable("ACCOUNT_NUMBER") final String accountNumberString, @PathVariable("PIN") final String pinString) {
        // If desired we could give a more meaningful message to state which parameter has failed to parse
        Long accountNumber, pin;
        try {
            accountNumber = Long.parseLong(accountNumberString);
            pin = Long.parseLong(pinString);
            return atmService.getBalance(accountNumber, pin);
        } catch (NumberFormatException nfe) {
            throw new ServiceException(new ServiceErrorCode(ATMServiceErrorCodes.ATM_ERROR_CODE_NON_NUMERIC_PARAMETER, "Parameters must be numeric"));
        }
    }

    @RequestMapping(value = "/account/{ACCOUNT_NUMBER}/pin/{PIN}/withdraw/{AMOUNT}", method = RequestMethod.GET)
    public WithdrawalDTO withdraw(@PathVariable(value = "ACCOUNT_NUMBER") final String accountNumberString, @PathVariable(value = "PIN") final String pinString, @PathVariable(value = "AMOUNT") final String amountString) {
        // If desired we could give a more meaningful message to state which parameter has failed to parse
        Long accountNumber, pin, amount;
        try {
            accountNumber = Long.parseLong(accountNumberString);
            pin = Long.parseLong(pinString);
            amount = Long.parseLong(amountString);
            return atmService.withdraw(accountNumber, pin, amount);
        } catch (NumberFormatException nfe) {
            throw new ServiceException(new ServiceErrorCode(ATMServiceErrorCodes.ATM_ERROR_CODE_NON_NUMERIC_PARAMETER, "Parameters must be numeric"));
        }
    }

}
