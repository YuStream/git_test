package GuanCha;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public abstract class AbstractSubject implements Subject {

    Vector<Observe> vector = new Vector<>();

    @Override
    public void addObserve(Observe observe) {
        vector.add(observe);
    }

    @Override
    public void delObserve(Observe observe) {
        vector.remove(observe);
    }

    @Override
    public void notifyObserve() {
        Enumeration<Observe> elements = vector.elements();
        while (elements.hasMoreElements()){
            elements.nextElement().update();
        }
    }

}
