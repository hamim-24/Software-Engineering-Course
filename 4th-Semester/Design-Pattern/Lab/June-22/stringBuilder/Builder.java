package stringBuilder;

public interface Builder {
    Builder append(String str);
    Builder append(int num);
    Builder append(char ch);
    Builder insert(int pos, String str);
    Builder reverse();
    Builder setCharAt(int pos, char ch);
    String build();
}
