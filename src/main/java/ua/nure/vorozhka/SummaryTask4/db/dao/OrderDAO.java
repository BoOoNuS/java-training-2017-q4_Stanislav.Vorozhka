package ua.nure.vorozhka.SummaryTask4.db.dao;

import ua.nure.vorozhka.SummaryTask4.model.been.Penalty;
import ua.nure.vorozhka.SummaryTask4.model.constant.Class;
import ua.nure.vorozhka.SummaryTask4.model.constant.Mark;
import ua.nure.vorozhka.SummaryTask4.model.constant.Role;
import ua.nure.vorozhka.SummaryTask4.model.constant.State;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Car;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Order;
import ua.nure.vorozhka.SummaryTask4.model.entyty.StateCounter;
import ua.nure.vorozhka.SummaryTask4.model.entyty.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stanislav on 21.05.2017.
 */
public abstract class OrderDAO {

    public abstract boolean createOrder(Connection connection, Order order) throws SQLException;

    public abstract Order getOrderByNumber(Connection connection, int number) throws SQLException;

    public abstract List<Order> getOrders(Connection connection) throws SQLException;

    public abstract List<Order> getOrdersByLogin(Connection connection, String login) throws SQLException;

    public abstract boolean setOrderFieldPenaltyIdByOrderNumber(
            Connection connection, int penaltyId, int orderNumber) throws SQLException;

    public abstract boolean setNullToOrderFieldPenaltyIdByOrderNumber(Connection connection, int orderNumber)
            throws SQLException;

    public abstract boolean setOrderFieldStateByOrderNumber(
            Connection connection, State state, int orderNumber) throws SQLException;

    public abstract List<StateCounter> getStateCountOnOrders(Connection connection) throws SQLException;

    protected Order getOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setUser(getUser(resultSet));
        order.setCar(getCar(resultSet));
        order.setPenalty(getPenalty(resultSet));
        order.setNumber(resultSet.getInt(1));
        order.setState(State.getStateById(resultSet.getInt(4)));
        order.setDriver(resultSet.getBoolean(6));
        order.setTerm(resultSet.getDate(7));
        return order;
    }

    private Penalty getPenalty(ResultSet resultSet) throws SQLException {
        Penalty penalty = new Penalty();
        penalty.setId(resultSet.getInt(8));
        penalty.setCost(resultSet.getInt(9));
        penalty.setCause(resultSet.getString(10));
        return penalty;
    }

    private Car getCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt(17));
        car.setMark(Mark.getMarkById(resultSet.getInt(18)));
        car.setClazz(Class.getClassById(resultSet.getInt(19)));
        car.setName(resultSet.getString(20));
        car.setCost(resultSet.getInt(21));
        car.setThereIs(resultSet.getBoolean(22));
        return car;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString(11));
        user.setPassport(resultSet.getString(12));
        user.setFullName(resultSet.getString(13));
        user.setPassport(resultSet.getString(14));
        user.setBlocked(resultSet.getBoolean(15));
        user.setRole(Role.getRoleById(resultSet.getInt(16)));
        return user;
    }

    protected StateCounter getStateCounter(ResultSet resultSet) throws SQLException {
        StateCounter stateCounter = new StateCounter();
        stateCounter.setCount(resultSet.getInt(1));
        stateCounter.setState(State.valueOf(resultSet.getString(2).toUpperCase()));
        return stateCounter;
    }
}
