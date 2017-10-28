package ua.nure.vorozhka.SummaryTask4.db.connector.postgres.dao;

import ua.nure.vorozhka.SummaryTask4.db.connector.postgres.utli.DAOUtils;
import ua.nure.vorozhka.SummaryTask4.db.dao.OrderDAO;
import ua.nure.vorozhka.SummaryTask4.model.constant.State;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Order;
import ua.nure.vorozhka.SummaryTask4.model.entyty.StateCounter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stanislav on 22.05.2017.
 */
public class PostgresOrderDAO extends OrderDAO {

    private static final String INSERT_INTO_ORDERS_SQL =
            "INSERT INTO orders (user_login, car_id, driver, term) VALUES (?, ?, ?, ?)";

    private static final String SELECT_FROM_ORDERS_SQL =
            "SELECT * FROM orders o LEFT OUTER JOIN penalty p ON o.penalty_id = p.id " +
                    "INNER JOIN users u ON o.user_login = u.login " +
                    "INNER JOIN cars c ON o.car_id = c.id AND o.number = ?";

    private static final String SELECT_ALL_FROM_ORDERS_SQL =
            "SELECT * FROM orders o LEFT OUTER JOIN penalty p ON o.penalty_id = p.id " +
                    "INNER JOIN users u ON o.user_login = u.login INNER JOIN cars c ON o.car_id = c.id";

    private static final String UPDATE_PENALTY_SQL = "UPDATE orders SET penalty_id = ? WHERE number = ?";

    private static final String UPDATE_STATE_ID_SQL = "UPDATE orders SET state_id = ? WHERE number = ?";

    private static final String SELECT_FROM_ORDERS_BY_USER_LOGIN_SQL =
            "SELECT * FROM orders o LEFT OUTER JOIN penalty p ON o.penalty_id = p.id " +
                    "INNER JOIN users u ON o.user_login = u.login INNER JOIN cars c ON o.car_id = c.id WHERE o.user_login = ?";

    private static final String UPDATE_ORDERS_SET_NULL_BY_NUMBER_SQL = "UPDATE orders SET penalty_id = NULL WHERE number = ?";

    private static final String GET_STATE_COUNT_SQL = "SELECT COUNT(o.state_id), s.name FROM orders o" +
            " INNER JOIN states s ON o.state_id = s.id GROUP BY s.name";

    private static PostgresOrderDAO postgresOrderDAO;

    private PostgresOrderDAO() {
    }

    public static PostgresOrderDAO getInstance() {
        if (postgresOrderDAO == null) {
            synchronized (PostgresOrderDAO.class) {
                if (postgresOrderDAO == null) {
                    postgresOrderDAO = new PostgresOrderDAO();
                }
            }
        }
        return postgresOrderDAO;
    }

    @Override
    public boolean createOrder(Connection connection, Order order) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    order.getUser().getLogin(),
                    order.getCar().getId(),
                    order.isDriver(),
                    order.getTerm());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public Order getOrderByNumber(Connection connection, int number) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ORDERS_SQL)) {
            preparedStatement.setInt(1, number);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return getOrder(resultSet);
                }
            }
        }
        return new Order();
    }

    @Override
    public List<Order> getOrders(Connection connection) throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_ORDERS_SQL)) {

            while (resultSet.next()) {
                orders.add(getOrder(resultSet));
            }
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByLogin(Connection connection, String login) throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_ORDERS_BY_USER_LOGIN_SQL)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    orders.add(getOrder(resultSet));
                }
            }
        }
        return orders;
    }

    @Override
    public boolean setOrderFieldPenaltyIdByOrderNumber(
            Connection connection, int penaltyId, int orderNumber) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PENALTY_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    penaltyId,
                    orderNumber);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setNullToOrderFieldPenaltyIdByOrderNumber(Connection connection, int orderNumber)
            throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDERS_SET_NULL_BY_NUMBER_SQL)) {
            preparedStatement.setInt(1, orderNumber);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean setOrderFieldStateByOrderNumber(
            Connection connection, State state, int orderNumber) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATE_ID_SQL)) {
            DAOUtils.fillPreparedStatement(
                    preparedStatement,
                    state.getId(),
                    orderNumber);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public List<StateCounter> getStateCountOnOrders(Connection connection) throws SQLException {
        List<StateCounter> stateCounters = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_STATE_COUNT_SQL);

            while (resultSet.next()) {
                stateCounters.add(getStateCounter(resultSet));
            }
        }
        return stateCounters;
    }
}
