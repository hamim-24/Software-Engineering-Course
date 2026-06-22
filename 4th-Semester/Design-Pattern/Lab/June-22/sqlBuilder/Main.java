package sqlBuilder;

public class Main {
    public static void main(String[] args) {
        String query = new Query().select("id", "name", "email").from("users").where("age > 18").orderBy("name").build();
        System.out.println(query);
    }
}
