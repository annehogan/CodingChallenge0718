package simpleatm;

public class ATMTestConstants {
    public static final Long STARTING_QUANTITY_50 = 20L;
    public static final Long STARTING_QUANTITY_20 = 30L;
    public static final Long STARTING_QUANTITY_10 = 30L;
    public static final Long STARTING_QUANTITY_5 = 20L;
    public static final Long NOTE_VALUE_50 = 50L;
    public static final Long NOTE_VALUE_20 = 20L;
    public static final Long NOTE_VALUE_10 = 10L;
    public static final Long NOTE_VALUE_5 = 5L;
    public static final Long ONE_OF_EACH = 85L;
    public static final Long ACCOUNT_1 = 3456789012L;
    public static final Long ACCOUNT_2 = 4567890123L;
    public static final Long ACCOUNT_3 = 5678901234L;
    public static final Long PIN_1 = 5678L;
    public static final Long PIN_2 = 7890L;
    public static final Long PIN_3 = 9012L;
    public static final Long BAD_ACCOUNT_1 = 4444444444L;
    public static final Long BAD_PIN_1 = 4444L;
    public static final Long INVALID_PIN = 3232L;
    public static final Long INVALID_ACCOUNT = 2345678L;
    public static final Long BALANCE_1 = 200L;
    public static final Long BALANCE_2 = 385L;
    public static final Long BALANCE_3 = 2000L;
    public static final Long OVERDRAFT_1 = 200L;
    public static final Long OVERDRAFT_2 = 100L;
    public static final Long OVERDRAFT_3 = 1000L;
    public static final Long ZERO = 0L;
    public static final String EXPECTED_NULL_STRING = "Expected to be empty";

    private ATMTestConstants() {
        // prevent calls to a public constructor in constants class
    }

}
