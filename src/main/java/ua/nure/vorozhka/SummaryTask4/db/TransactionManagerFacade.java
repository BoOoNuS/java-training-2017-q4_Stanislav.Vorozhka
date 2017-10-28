package ua.nure.vorozhka.SummaryTask4.db;

import ua.nure.vorozhka.SummaryTask4.db.connector.TransactionManagerFactory;
import ua.nure.vorozhka.SummaryTask4.exception.db.DBException;
import ua.nure.vorozhka.SummaryTask4.model.been.Penalty;
import ua.nure.vorozhka.SummaryTask4.model.constant.Role;
import ua.nure.vorozhka.SummaryTask4.model.constant.State;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Car;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Order;
import ua.nure.vorozhka.SummaryTask4.model.entyty.StateCounter;
import ua.nure.vorozhka.SummaryTask4.model.entyty.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stanislav on 21.05.2017.
 */
public class TransactionManagerFacade implements Facade {

    private static Map<TransactionManagerFactory, TransactionManagerFacade> abstractFactoryMap = new HashMap<>();

    private TransactionManagerFactory transactionManagerFactory;

    private TransactionManagerFacade(TransactionManagerFactory abstractFactory) {
        this.transactionManagerFactory = abstractFactory;
    }

    public static TransactionManagerFacade getInstance(TransactionManagerFactory transactionManagerFactory) {
        if (!abstractFactoryMap.containsKey(transactionManagerFactory)) {
            synchronized (TransactionManagerFacade.class) {
                if (!abstractFactoryMap.containsKey(transactionManagerFactory)) {
                    TransactionManagerFacade facade = new TransactionManagerFacade(transactionManagerFactory);
                    abstractFactoryMap.put(transactionManagerFactory, facade);
                    return facade;
                }
            }
        }
        return abstractFactoryMap.get(transactionManagerFactory);
    }

    // User
    @Override
    public boolean createUser(User user) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().createUser(user);
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().getUserByLogin(login);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().getUserByLoginAndPassword(login, password);
    }

    @Override
    public boolean updateUserByLogin(User user, String oldLogin) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().updateUserByLogin(user, oldLogin);
    }

    @Override
    public boolean setUserFieldRoleByUserLogin(Role role, String userLogin) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().setUserFieldRoleByUserLogin(role, userLogin);
    }

    @Override
    public boolean setUserFieldPassportByUserLogin(String passport, String userLogin) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().setUserFieldPassportByUserLogin(passport, userLogin);
    }

    @Override
    public boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException {
        return transactionManagerFactory.getUserTransactionManager().setUserFieldBlockedByUserLogin(blocked, userLogin);
    }

    // Order
    @Override
    public boolean createOrder(Order order) throws DBException {
        return transactionManagerFactory.getOrderTransactionManager().createOrder(order);
    }

    @Override
    public Order getOrderByNumber(int number) throws DBException {
        return transactionManagerFactory.getOrderTransactionManager().getOrderByNumber(number);
    }

    @Override
    public List<Order> getOrders() throws DBException {
        return transactionManagerFactory.getOrderTransactionManager().getOrders();
    }

    public List<Order> getOrdersByUserLogin(String login) throws DBException {
        return transactionManagerFactory.getOrderTransactionManager().getOrdersByLogin(login);
    }

    @Override
    public boolean setOrderFieldPenaltyIdByOrderNumber(int penaltyId, int orderNumber) throws DBException {
        return transactionManagerFactory.getOrderTransactionManager().
                setOrderFieldPenaltyIdByOrderNumber(penaltyId, orderNumber);
    }

    @Override
    public boolean setNullToOrderFieldPenaltyIdByOrderNumber(int orderNumber) throws DBException {
        return transactionManagerFactory.getOrderTransactionManager().
                setNullToOrderFieldPenaltyIdByOrderNumber(orderNumber);
    }

    @Override
    public boolean setOrderFieldStateByOrderNumber(State state, int orderNumber) throws DBException {
        return transactionManagerFactory.getOrderTransactionManager().
                setOrderFieldStateByOrderNumber(state, orderNumber);
    }

    @Override
    public List<StateCounter> getStateCountOnOrders() throws DBException {
        return transactionManagerFactory.getOrderTransactionManager().getStateCountOnOrders();
    }

    // Penalty
    @Override
    public boolean createPenalty(Penalty penalty) throws DBException {
        return transactionManagerFactory.getPenaltyTransactionManager().createPenalty(penalty);
    }

    @Override
    public Penalty getPenaltyById(int id) throws DBException {
        return transactionManagerFactory.getPenaltyTransactionManager().getPenaltyById(id);
    }

    @Override
    public boolean deletePenaltyById(int id) throws DBException {
        return transactionManagerFactory.getPenaltyTransactionManager().deletePenaltyById(id);
    }

    // Car
    @Override
    public boolean createCar(Car car) throws DBException {
        return transactionManagerFactory.getCarTransactionManager().createCar(car);
    }

    @Override
    public List<Car> getCars() throws DBException {
        return transactionManagerFactory.getCarTransactionManager().getCars();
    }

    @Override
    public List<Car> getCarsByClass(String clazz, String sort) throws DBException {
        return transactionManagerFactory.getCarTransactionManager().getCarsByClass(clazz, sort);
    }

    @Override
    public List<Car> getCarsByMark(String mark, String sort) throws DBException {
        return transactionManagerFactory.getCarTransactionManager().getCarsByMark(mark, sort);
    }

    @Override
    public boolean updateCar(Car car) throws DBException {
        return transactionManagerFactory.getCarTransactionManager().updateCar(car);
    }

    @Override
    public boolean setCarFieldThereIsByCarId(boolean thereIs, int carId) throws DBException {
        return transactionManagerFactory.getCarTransactionManager().setCarFieldThereIsByCarId(thereIs, carId);
    }

    @Override
    public boolean deleteCarById(int id) throws DBException {
        return transactionManagerFactory.getCarTransactionManager().deleteCarById(id);
    }

    // Car order
    @Override
    public boolean changeCarFieldThereIsAndOrderFieldStateByIDs(
            boolean thereIs, int carId, State state, int orderNumber) throws DBException {

        return transactionManagerFactory.getCarOrderTransactionManager().
                changeCarFieldThereIsAndOrderFieldStateByIDs(thereIs, carId, state, orderNumber);
    }

    // Penalty order
    @Override
    public boolean createAndSetOrderFieldPenaltyIdByOrderNumber(
            Penalty penalty, int orderNumber) throws DBException {

        return transactionManagerFactory.getOrderPenaltyTransactionManager().
                createAndSetOrderFieldPenaltyIdByOrderNumber(penalty, orderNumber);
    }

    @Override
    public boolean createAndSetOrderFieldPenaltyIdByOrderNumberAndChangeState(
            Penalty penalty, int orderNumber, State state) throws DBException {

        return transactionManagerFactory.getOrderPenaltyTransactionManager().
                createAndSetOrderFieldPenaltyIdByOrderNumberAndChangeState(penalty, orderNumber, state);
    }
}
