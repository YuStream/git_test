package GuanCha;

public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update myself");
        notifyObserve();
    }
}
