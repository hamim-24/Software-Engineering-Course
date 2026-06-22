package sqlBuilder;

public class Query implements Builder {
    private String columns = "*";
    private String table;
    private String condition;
    private String order;

    public Builder select(String... columns) {
        this.columns = String.join(", ", columns);
        return this;
    }

    public Builder from(String table) {
        this.table = table;
        return this;
    }

    public Builder where(String condition) {
        this.condition = condition;
        return this;
    }

    public Builder orderBy(String column) {
        this.order = column;
        return this;
    }

    public String build() {
        String query = "SELECT " + columns + " FROM " + table;
        if (condition != null) query += " WHERE " + condition;
        if (order != null) query += " ORDER BY " + order;
        return query;
    }
}
