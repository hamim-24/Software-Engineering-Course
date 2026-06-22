package sqlBuilder;

public interface Builder {
    Builder select(String... columns);
    Builder from(String table);
    Builder where(String condition);
    Builder orderBy(String column);
    String build();
}
