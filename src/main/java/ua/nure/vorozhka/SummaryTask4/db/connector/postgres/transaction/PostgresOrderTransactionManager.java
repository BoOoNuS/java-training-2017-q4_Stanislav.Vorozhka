package ua.nure.vorozhka.SummaryTask4.db.connector.postgres.transaction;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.db.connector.DAOFactory;
import ua.nure.vorozhka.SummaryTask4.db.transaction.OrderTransactionManager;
import ua.nure.vorozhka.SummaryTask4.exception.ExceptionMessages;
import ua.nure.vorozhka.SummaryTask4.exception.db.DBException;
import ua.nure.vorozhka.SummaryTask4.model.constant.State;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Order;
import ua.nure.vorozhka.SummaryTask4.model.entyty.StateCounter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stanislav on 22.05.2017.
 */
public class PostgresOrderTransactionManager implements OrderTransactionManager {

    private static final Logger LOG = Logger.getLogger(PostgresOrderTransactionManager.class);

    private static PostgresOrderTransactionManager postgresOrderTransactionManager;

    private DAOFactory daoFactory;

    private PostgresOrderTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresOrderTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresOrderTransactionManager == null) {
            synchronized (PostgresOrderTransactionManager.class) {
                if (postgresOrderTransactionManager == null) {
                    postgresOrderTransactionManager = new PostgresOrderTransactionManager(daoFactory);
                }
            }
        }
        return postgresOrderTransactionManager;
    }

    @Override
    public boolean createOrder(Order order) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getOrderDAO().createOrder(connection, order);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_CREATE_ORDER_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_CREATE_ORDER_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public Order getOrderByNumber(int number) throws DBException {
        Connection connection = daoFactory.getConnection();
        Order result;
        try {
            result = daoFactory.getOrderDAO().getOrderByNumber(connection, number);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_ORDER_BY_NUMBER_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_ORDER_BY_NUMBER_MESSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<Order> getOrders() throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Order> result;
        try {
            result = daoFactory.getOrderDAO().getOrders(connection);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_ORDERS_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_ORDERS_MESSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<Order> getOrdersByLogin(String login) throws DBException {
        Connection connection = daoFactory.getConnection();
        List<Order> result;
        try {
            result = daoFactory.getOrderDAO().getOrdersByLogin(connection, login);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_ORDERS_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_ORDERS_MESSAGE, e);
        }
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setOrderFieldPenaltyIdByOrderNumber(int penaltyId, int orderNumber) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getOrderDAO().setOrderFieldPenaltyIdByOrderNumber(connection, penaltyId, orderNumber);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_ORDER_FIELD_PENALTY_ID_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_ORDER_FIELD_PENALTY_ID_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setNullToOrderFieldPenaltyIdByOrderNumber(int orderNumber) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getOrderDAO().setNullToOrderFieldPenaltyIdByOrderNumber(connection, orderNumber);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_ORDER_FIELD_PENALTY_ID_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_ORDER_FIELD_PENALTY_ID_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setOrderFieldStateByOrderNumber(State state, int orderNumber) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getOrderDAO().setOrderFieldStateByOrderNumber(connection, state, orderNumber);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_ORDER_FIELD_STATE_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_ORDER_FIELD_STATE_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public List<StateCounter> getStateCountOnOrders() throws DBException {
        Connection connection = daoFactory.getConnection();
        List<StateCounter> result;
        try {
            result = daoFactory.getOrderDAO().getStateCountOnOrders(connection);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_GET_STATE_CINTERS, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_GET_STATE_CINTERS, e);
        }
        daoFactory.close(connection);
        return result;
    }
}
