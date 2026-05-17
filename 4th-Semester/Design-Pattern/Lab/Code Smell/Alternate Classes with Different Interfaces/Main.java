package problem1;
public class Main {
    public static void main(String[] args) {
        Payable payment1 = new OnlineOrder("Hamim", "alu, potol");
        payment1.processPayment(150.0);
    }    
}
