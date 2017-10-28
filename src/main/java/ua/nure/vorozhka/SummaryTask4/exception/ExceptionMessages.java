package ua.nure.vorozhka.SummaryTask4.exception;

/**
 * Created by Stanislav on 22.05.2017.
 */
public final class ExceptionMessages {

    private ExceptionMessages() {
    }

    // DB
    public static final String ERRORS_WHEN_INITIALIZING_DS_MESSAGE = "Errors when initializing the DataSource";
    public static final String FAILED_TO_GET_CONNECTION_MESSAGE = "Failed to get connection";
    public static final String COULD_NOT_CLOSE_CONNECTION_MESSAGE = "Could not close connection";
    public static final String FAILED_TO_MAKE_CHANGES_MESSAGE = "Failed to make changes";
    public static final String COULD_NOT_ROLLBACK_TRANSACTION_MESSAGE = "Could not rollback transaction";
    public static final String TRANSACTION_ERROR_MESSAGE = "Transaction error";

    // User
    public static final String COULD_NOT_EDIT_USER_MESSAGE = "Could not edit user";
    public static final String FAILED_TO_GET_USER_BY_LOGIN_AND_PASS_MESSAGE =
            "Failed to get user by login and password";

    public static final String USER_NOT_EXIST_MESSAGE = "User with such login and password does not exist";
    public static final String FAILED_TO_GET_USER_BY_LOGIN_MESSAGE = "Failed to get user by login";
    public static final String COULD_NOT_CREATE_USER_MESSAGE =
            "Could not create user, maybe this login or password already exists";

    public static final String COULD_NOT_SET_USER_FIELD_ROLE_MESSAGE = "Could not set user field role";
    public static final String COULD_NOT_SET_USER_FIELD_PASSPORT_MESSAGE = "Could not set user field passport";
    public static final String COULD_NOT_SET_USER_FIELD_BLOCKED_MESSAGE = "Could not set user field blocked";

    // Order
    public static final String COULD_NOT_CREATE_ORDER_MESSAGE = "Could not create order";
    public static final String FAILED_TO_GET_ORDER_BY_NUMBER_MESSAGE = "Failed to get number by number";
    public static final String FAILED_TO_GET_ORDERS_MESSAGE = "Failed to get orders";
    public static final String COULD_NOT_SET_ORDER_FIELD_PENALTY_ID_MESSAGE = "Could not set user field penalty id";
    public static final String COULD_NOT_SET_ORDER_FIELD_STATE_MESSAGE = "Could not set user field state";

    // Car
    public static final String COULD_NOT_CREATE_CAR_MESSAGE = "Could not create car";
    public static final String FAILED_TO_GET_CARS_MESSAGE = "Failed to get cars";
    public static final String FAILED_TO_GET_CARS_BY_CLASS_MESSAGE = "Failed to get cars by class";
    public static final String FAILED_TO_GET_CARS_BY_MARK_MESSAGE = "Failed to get cars by mark";
    public static final String COULD_NOT_EDIT_CAR_MESSAGE = "Could not edit car";
    public static final String COULD_NOT_DELETE_CAR_BY_ID_MESSAGE = "Could not delete car by id";
    public static final String COULD_NOT_SET_CAR_FIELD_THERE_IS_MESSAGE = "Could not set car field there is";

    // Penalty
    public static final String COULD_NOT_CREATE_PENALTY_MESSAGE = "Could not create penalty";
    public static final String COULD_NOT_GET_PENALTY_BY_ID_MESSAGE = "Could not get penalty by id";
    public static final String COULD_NOT_DELETE_PENALTY_BY_ID_MESSAGE = "Could not delete penalty by id";

    // State
    public static final String COULD_NOT_GET_STATE_CINTERS = "Could not get state counters";

    // Common
    public static final String MAYBE_YOUR_ROBOT_MESSAGE = "Maybe your are robot";
    public static final String INCORRECT_SEARCH_PARAMETER_MESSAGE = "Incorrect search parameter";
    public static final String COULD_NOT_CREATE_CHECK_MESSAGE = "Could not create check";
    public static final String YOU_TRY_TO_ENTER_THE_SAME_SEARCH_MESSAGE =
            "You try to enter the same search query 5 times. " +
                    "Wait a bit before continuing to make the same searches...";
}
