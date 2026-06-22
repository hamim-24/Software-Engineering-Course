package stringBuilder;

public class Main {
    public static void main(String[] args) {
        String result = new StringBuilder().append("Hello").reverse().append(" ").append("World").reverse().build();
        System.out.println(result);
    }
    
}
