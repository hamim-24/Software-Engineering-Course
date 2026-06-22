package Builder;

public class Main {
    public static void main(String[] args) {
     
    User user = new User.Builder().setAge(10).setEmail("abc").setName("Hamim").setPhone("345678").build();
       
    }
}
