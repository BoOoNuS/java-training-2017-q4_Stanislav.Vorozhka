package ua.nure.vorozhka.SummaryTask4.db.connector.postgres.transaction;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.db.connector.DAOFactory;
import ua.nure.vorozhka.SummaryTask4.db.transaction.UserTransactionManager;
import ua.nure.vorozhka.SummaryTask4.exception.ExceptionMessages;
import ua.nure.vorozhka.SummaryTask4.exception.db.DBException;
import ua.nure.vorozhka.SummaryTask4.model.constant.Role;
import ua.nure.vorozhka.SummaryTask4.model.entyty.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Stanislav on 21.05.2017.
 */
public class PostgresUserTransactionManager implements UserTransactionManager {

    private static final Logger LOG = Logger.getLogger(PostgresUserTransactionManager.class);

    private static PostgresUserTransactionManager postgresUserTransactionManager;

    private DAOFactory daoFactory;

    private PostgresUserTransactionManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PostgresUserTransactionManager getInstance(DAOFactory daoFactory) {
        if (postgresUserTransactionManager == null) {
            synchronized (PostgresUserTransactionManager.class) {
                if (postgresUserTransactionManager == null) {
                    postgresUserTransactionManager = new PostgresUserTransactionManager(daoFactory);
                }
            }
        }
        return postgresUserTransactionManager;
    }

    @Override
    public boolean createUser(User user) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getUserDAO().createUser(connection, user);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_CREATE_USER_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_CREATE_USER_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public User getUserByLogin(String login) throws DBException {
        Connection connection = daoFactory.getConnection();
        User user;
        try {
            user = daoFactory.getUserDAO().getUserByLogin(connection, login);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_USER_BY_LOGIN_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_USER_BY_LOGIN_MESSAGE, e);
        }
        daoFactory.close(connection);
        return user;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        Connection connection = daoFactory.getConnection();
        User user;
        try {
            user = daoFactory.getUserDAO().getUserByLoginAndPassword(connection, login, password);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.FAILED_TO_GET_USER_BY_LOGIN_AND_PASS_MESSAGE, e);
            throw new DBException(ExceptionMessages.FAILED_TO_GET_USER_BY_LOGIN_AND_PASS_MESSAGE, e);
        }
        daoFactory.close(connection);
        return user;
    }

    @Override
    public boolean updateUserByLogin(User user, String oldLogin) throws DBException {
        // TODO Create trigger for auditing user update
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getUserDAO().updateUserByLogin(connection, user, oldLogin);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_EDIT_USER_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_EDIT_USER_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setUserFieldRoleByUserLogin(Role role, String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            result = daoFactory.getUserDAO().setUserFieldRoleByUserLogin(connection, role, userLogin);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_USER_FIELD_ROLE_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_USER_FIELD_ROLE_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setUserFieldPassportByUserLogin(String passport, String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            // TODO Create trigger for auditing passport date
            result = daoFactory.getUserDAO().setUserFieldPassportByUserLogin(connection, passport, userLogin);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_USER_FIELD_PASSPORT_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_USER_FIELD_PASSPORT_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }

    @Override
    public boolean setUserFieldBlockedByUserLogin(boolean blocked, String userLogin) throws DBException {
        Connection connection = daoFactory.getConnection();
        // TODO Create trigger for auditing blocking
        boolean result;
        try {
            result = daoFactory.getUserDAO().setUserFieldBlockedByUserLogin(connection, blocked, userLogin);
        } catch (SQLException e) {
            LOG.error(ExceptionMessages.COULD_NOT_SET_USER_FIELD_BLOCKED_MESSAGE, e);
            daoFactory.rollBack(connection);
            throw new DBException(ExceptionMessages.COULD_NOT_SET_USER_FIELD_BLOCKED_MESSAGE, e);
        }
        daoFactory.commit(connection);
        daoFactory.close(connection);
        return result;
    }
}
