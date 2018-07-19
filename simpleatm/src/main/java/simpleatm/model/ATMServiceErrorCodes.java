package simpleatm.model;

/**
 * Error Codes for use in service exceptions
 *
 * @Author Anne Hogan
 * @Copyright 2018
 */

public class ATMServiceErrorCodes {

    public static final String ATM_ERROR_CODE_POSITIVE_AMOUNT_REQD = "ATM_ERR_0001";
    public static final String ATM_ERROR_CODE_ACCOUNT_INSUFFICIENT_FUNDS = "ATM_ERR_0002";
    public static final String ATM_ERROR_CODE_ATM_INSUFFICIENT_FUNDS = "ATM_ERR_0003";
    public static final String ATM_ERROR_CODE_DUPLICATE_ACCOUNT_PIN = "ATM_ERR_0004";
    public static final String ATM_ERROR_CODE_NO_SUCH_ACCOUNT = "ATM_ERR_0005";
    public static final String ATM_ERROR_CODE_NON_NUMERIC_PARAMETER = "ATM_ERR_0006";

    private ATMServiceErrorCodes() {
        // prevent calls to a public constructor in constants class
    }
}
