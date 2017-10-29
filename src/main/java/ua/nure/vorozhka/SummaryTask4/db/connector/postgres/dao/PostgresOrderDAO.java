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

    private static final String RECORDS_RESULT_ALIAS_SQL =
            "result(number integer, user_login character varying(16), car_id integer, " +
                    "state_id integer, penalty_id integer, " +
                    "driver boolean, term date, pen_id integer, penalty_cost integer, cause character varying(96), " +
                    "login character varying(16), password character varying(32), " +
                    "fullname character varying(32), passport character varying(8), " +
                    "blocked boolean, role_id integer, ca_id integer, mark_id integer, " +
                    "class_id integer, name character varying(16), car_cost integer, there_is boolean);";

    private static final String INSERT_INTO_ORDERS_SQL =
            "INSERT INTO orders (user_login, car_id, driver, term) VALUES (?, ?, ?, ?)";

    private static final String SELECT_FROM_ORDERS_SQL =
            String.format("SELECT * FROM select_orders_by_order_number(?) AS %s", RECORDS_RESULT_ALIAS_SQL);

    private static final String SELECT_ALL_FROM_ORDERS_SQL =
            "SELECT * FROM select_all_from_orders() AS " + RECORDS_RESULT_ALIAS_SQL;

    private static final String UPDATE_PENALTY_SQL = "UPDATE orders SET penalty_id = ? WHERE number = ?";

    private static final String UPDATE_STATE_ID_SQL = "UPDATE orders SET state_id = ? WHERE number = ?";

    private static final String SELECT_FROM_ORDERS_BY_USER_LOGIN_SQL =
            String.format("SELECT * FROM select_orders_by_user_login(?) AS %s", RECORDS_RESULT_ALIAS_SQL);

    private static final String UPDATE_ORDERS_SET_NULL_BY_NUMBER_SQL =
            "UPDATE orders SET penalty_id = NULL WHERE number = ?";

    private static final String GET_STATE_COUNT_SQL =
            "SELECT * FROM get_state_count_on_orders() result(count bigint, name text)";

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
