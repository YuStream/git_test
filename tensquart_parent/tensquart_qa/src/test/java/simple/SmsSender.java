package simple;

public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is Sms Sender");
    }
}
