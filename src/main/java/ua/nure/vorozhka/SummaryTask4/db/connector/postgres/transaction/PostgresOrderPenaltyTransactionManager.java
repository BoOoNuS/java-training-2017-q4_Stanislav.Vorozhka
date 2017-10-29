package ua.nure.vorozhka.SummaryTask4.db.connector.postgres.transaction;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.db.connector.DAOFactory;
import ua.nure.vorozhka.SummaryTask4.db.transaction.OrderPenaltyTransactionManager;
import ua.nure.vorozhka.SummaryTask4.exception.ExceptionMessages;
import ua.nure.vorozhka.SummaryTask4.exception.db.DBException;
import ua.nure.vorozhka.SummaryTask4.model.been.Penalty;
import ua.nure.vorozhka.SummaryTask4.model.constant.State;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Stanislav on 27.05.2017.
 */
public class PostgresOrderPenaltyTransactionManager implements OrderPenaltyTransactionManager {

    private static final Logger LOG = Logger.getLogger(PostgresOrderPenaltyTransactionManager.class);

    private static PostgresOrderPenaltyTransactionManager postgresOrderPenaltyTransactionManager;

    private DAOFactory daoFactory;

    private PostgresOrderPenaltyTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresOrderPenaltyTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresOrderPenaltyTransactionManager == null) {
            synchronized (PostgresOrderPenaltyTransactionManager.class) {
                if (postgresOrderPenaltyTransactionManager == null) {
                    postgresOrderPenaltyTransactionManager = new PostgresOrderPenaltyTransactionManager(daoFactory);
                }
            }
        }
        return postgresOrderPenaltyTransactionManager;
    }

    @Override
    public boolean createAndSetOrderFieldPenaltyIdByOrderNumber(
            Penalty penalty, int orderNumber) throws DBException {

        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getPenaltyDAO().createPenalty(connection, penalty);
            result &= daoFactory.getOrderDAO().setOrderFieldPenaltyIdByOrderNumber(
                    connection, penalty.getId(), orderNumber);

        } catch (SQLException e) {
            LOG.error(ExceptionMessages.TRANSACTION_ERROR_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.TRANSACTION_ERROR_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean createAndSetOrderFieldPenaltyIdByOrderNumberAndChangeState(
            Penalty penalty, int orderNumber, State state) throws DBException {
        //TODO Think about trigger for order state here

        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getPenaltyDAO().createPenalty(connection, penalty);
            result &= daoFactory.getOrderDAO().setOrderFieldPenaltyIdByOrderNumber(
                    connection, penalty.getId(), orderNumber);

            result &= daoFactory.getOrderDAO().setOrderFieldStateByOrderNumber(connection, state, orderNumber);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.TRANSACTION_ERROR_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.TRANSACTION_ERROR_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
