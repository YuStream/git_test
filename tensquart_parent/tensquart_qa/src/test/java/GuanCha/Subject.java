package GuanCha;

public interface Subject {
    public void addObserve(Observe observe);

    public void delObserve(Observe observe);

    public void notifyObserve();

    public void operation();
}
