package ua.nure.vorozhka.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.db.Facade;
import ua.nure.vorozhka.SummaryTask4.db.TransactionManagerFacade;
import ua.nure.vorozhka.SummaryTask4.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.vorozhka.SummaryTask4.exception.AppException;
import ua.nure.vorozhka.SummaryTask4.model.constant.Role;
import ua.nure.vorozhka.SummaryTask4.web.Paths;
import ua.nure.vorozhka.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Stanislav on 29.05.2017.
 */
public class SetPermissionCommand extends Command {

    private static final Logger LOG = Logger.getLogger(SetPermissionCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        String login = req.getParameter("login");
        LOG.trace(String.format("Request parameter: login --> %s", login));
        Role role = Role.valueOf(req.getParameter("role"));
        LOG.trace(String.format("Request parameter: role --> %s", role));

        FACADE.setUserFieldRoleByUserLogin(role, login);

        LOG.debug("Command finished");
        return Paths.PERMISSION_SERVLET;
    }
}
