package ua.nure.vorozhka.SummaryTask4.web.command.manager;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.db.Facade;
import ua.nure.vorozhka.SummaryTask4.db.TransactionManagerFacade;
import ua.nure.vorozhka.SummaryTask4.db.connector.postgres.PostgresTransactionManagerFactory;
import ua.nure.vorozhka.SummaryTask4.exception.AppException;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Order;
import ua.nure.vorozhka.SummaryTask4.web.Paths;
import ua.nure.vorozhka.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Stanislav on 27.05.2017.
 */
public class ToManagerOrdersCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToManagerOrdersCommand.class);

    private static final Facade FACADE =
            TransactionManagerFacade.getInstance(PostgresTransactionManagerFactory.getInstance());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        long currentTimeMillis = System.currentTimeMillis();
        req.setAttribute("currentTimeMillis", currentTimeMillis);
        List<Order> orders = FACADE.getOrders();
        LOG.trace(String.format("Orders --> %s", orders.toString()));
        req.setAttribute("orders", orders);

        LOG.debug("Command finished");
        return Paths.ORDERS_MANAGER_PAGE;
    }
}
