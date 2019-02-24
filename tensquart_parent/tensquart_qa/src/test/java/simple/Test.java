package simple;

public class Test {
    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        Sender mail = factory.product("mail");
        mail.send();
    }
}
