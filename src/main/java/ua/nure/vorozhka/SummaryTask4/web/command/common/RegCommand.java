package ua.nure.vorozhka.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.db.Facade;
import ua.nure.vorozhka.SummaryTask4.db.TransactionManagerFacade;
import ua.nure.vorozhka.SummaryTask4.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.vorozhka.SummaryTask4.exception.AppException;
import ua.nure.vorozhka.SummaryTask4.exception.ExceptionMessages;
import ua.nure.vorozhka.SummaryTask4.model.entyty.User;
import ua.nure.vorozhka.SummaryTask4.web.Paths;
import ua.nure.vorozhka.SummaryTask4.web.command.Command;
import ua.nure.vorozhka.SummaryTask4.web.command.util.CommandUtils;
import ua.nure.vorozhka.SummaryTask4.web.validator.Validator;
import ua.nure.vorozhka.SummaryTask4.web.validator.user.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Stanislav on 03.01.2017.
 */
public class RegCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RegCommand.class);

    private static final Validator<User> USER_VALIDATOR = UserValidator.getInstance();

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        checkCaptcha(req);

        User user = CommandUtils.getUserFromRequest(req);
        LOG.trace(String.format("Request parameter: user --> %s", user.toString()));

        USER_VALIDATOR.validate(user);

        LOG.trace(String.format("User created (true, false) --> %b", FACADE.createUser(user)));

        LOG.debug("Command finished");
        return Paths.AUTHENTICATE_SERVLET;
    }

    private void checkCaptcha(HttpServletRequest req) throws AppException {
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");

        if (gRecaptchaResponse.isEmpty()) {
            throw new AppException(ExceptionMessages.MAYBE_YOUR_ROBOT_MESSAGE);
        }
    }
}
