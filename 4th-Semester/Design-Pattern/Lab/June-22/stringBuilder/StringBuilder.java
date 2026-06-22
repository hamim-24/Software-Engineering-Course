package stringBuilder;

public class StringBuilder implements Builder {
    private String result = "";
    
    public Builder append(String str) {
        result += str;
        return this;
    }

    public Builder append(int num) {
        result += num;
        return this;
    }

    public Builder append(char ch) {
        result += ch;
        return this;
    }

    public Builder insert(int pos, String str) {
        result = result.substring(0, pos) + str + result.substring(pos);
        return this;
    }

    public Builder reverse() {
        result = new java.lang.StringBuilder(result).reverse().toString();
        return this;
    }

    public Builder setCharAt(int pos, char ch) {
        result = result.substring(0, pos) + ch + result.substring(pos + 1);
        return this;
    }

    public String build() {
        return result;
    }
}
