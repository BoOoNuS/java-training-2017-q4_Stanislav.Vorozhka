package ua.nure.vorozhka.SummaryTask4.db.dao;

import ua.nure.vorozhka.SummaryTask4.model.entyty.StateCounter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stanislav on 30.05.2017.
 */
public abstract class StateDAO {

    public abstract List<StateCounter> getStateCounters(Connection connection) throws SQLException;
}
