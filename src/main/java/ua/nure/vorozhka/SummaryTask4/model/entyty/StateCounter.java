package ua.nure.vorozhka.SummaryTask4.model.entyty;

import ua.nure.vorozhka.SummaryTask4.model.constant.State;

/**
 * Created by Stanislav on 30.05.2017.
 */
public class StateCounter {

    private State state;
    private int count;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "state - " + state +
                ", count - " + count;
    }
}
