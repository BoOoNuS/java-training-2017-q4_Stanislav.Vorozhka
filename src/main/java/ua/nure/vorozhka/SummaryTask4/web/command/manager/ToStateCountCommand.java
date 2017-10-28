package ua.nure.vorozhka.SummaryTask4.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.db.Facade;
import ua.nure.vorozhka.SummaryTask4.db.TransactionManagerFacade;
import ua.nure.vorozhka.SummaryTask4.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.vorozhka.SummaryTask4.exception.AppException;
import ua.nure.vorozhka.SummaryTask4.model.entyty.StateCounter;
import ua.nure.vorozhka.SummaryTask4.web.Paths;
import ua.nure.vorozhka.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Stanislav on 30.05.2017.
 */
public class ToStateCountCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToStateCountCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        List<StateCounter> stateCounters = FACADE.getStateCountOnOrders();
        LOG.trace(String.format("stateCounters --> %s", stateCounters.toString()));
        req.setAttribute("stateCounters", stateCounters);

        LOG.debug("Command finished");
        return Paths.STATE_STATS_PAGE;
    }
}
