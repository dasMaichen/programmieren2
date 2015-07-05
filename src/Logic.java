import java.util.ArrayList;
import java.util.List;

/**
 * Created by mai on 27.06.15.
 */
class Logic {
    private int counter = 0;
    private int maximum = 0;
    private List<PunkteListener> listeners = new ArrayList<PunkteListener>();

    public void addLogicListener(PunkteListener logicListener) {
        this.listeners.add(logicListener);
    }

    public void increaseCounter() {
        counter++;
        for (PunkteListener logicListener : listeners) {
            logicListener.onCounterHasChanged(counter,maximum);
        }
    }
}
