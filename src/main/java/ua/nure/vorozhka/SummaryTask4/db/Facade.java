package ua.nure.vorozhka.SummaryTask4.db;

import ua.nure.vorozhka.SummaryTask4.exception.db.DBException;
import ua.nure.vorozhka.SummaryTask4.model.been.Penalty;
import ua.nure.vorozhka.SummaryTask4.model.constant.Role;
import ua.nure.vorozhka.SummaryTask4.model.constant.State;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Car;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Order;
import ua.nure.vorozhka.SummaryTask4.model.entyty.StateCounter;
import ua.nure.vorozhka.SummaryTask4.model.entyty.User;

import java.util.List;

/**
 * Created by Stanislav on 22.05.2017.
 */
public interface Facade {

    // User
    boolean createUser(User user) throws DBException;

    User getUserByLogin(String login) throws DBException;

    User getUserByLoginAndPassword(String login, String password) throws DBException;

    boolean updateUserByLogin(User user, String oldLogin) throws DBException;

    boolean setUserFieldRoleByUserLogin(Role role, String userLogin) throws DBException;

    boolean setUserFieldPassportByUserLogin(String passport, String userLogin) throws DBException;

    boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException;

    // Car
    boolean createCar(Car car) throws DBException;

    List<Car> getCars() throws DBException;

    List<Car> getCarsByClass(String clazz, String sort) throws DBException;

    List<Car> getCarsByMark(String mark, String sort) throws DBException;

    boolean updateCar(Car car) throws DBException;

    boolean setCarFieldThereIsByCarId(boolean thereIs, int carId) throws DBException;

    boolean deleteCarById(int id) throws DBException;

    // Order
    boolean createOrder(Order order) throws DBException;

    Order getOrderByNumber(int number) throws DBException;

    List<Order> getOrders() throws DBException;

    List<Order> getOrdersByUserLogin(String login) throws DBException;

    boolean setOrderFieldPenaltyIdByOrderNumber(int penaltyId, int orderNumber) throws DBException;

    boolean setNullToOrderFieldPenaltyIdByOrderNumber(int orderNumber) throws DBException;

    boolean setOrderFieldStateByOrderNumber(State state, int orderNumber) throws DBException;

    List<StateCounter> getStateCountOnOrders() throws DBException;

    // Penalty
    boolean createPenalty(Penalty penalty) throws DBException;

    Penalty getPenaltyById(int id) throws DBException;

    boolean deletePenaltyById(int id) throws DBException;

    // Car order
    boolean changeCarFieldThereIsAndOrderFieldStateByIDs(
            boolean thereIs, int carId, State state, int orderNumber) throws DBException;

    // Penalty order
    boolean createAndSetOrderFieldPenaltyIdByOrderNumber(
            Penalty penalty, int orderNumber) throws DBException;

    boolean createAndSetOrderFieldPenaltyIdByOrderNumberAndChangeState(
            Penalty penalty, int orderNumber, State state) throws DBException;
}
