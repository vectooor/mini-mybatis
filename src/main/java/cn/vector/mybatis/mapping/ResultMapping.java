package cn.vector.mybatis.mapping;

/**
 * @author 勤恳的小码农
 * @date 2020/2/24 15:55
 */
public class ResultMapping {
    /**
     * 数据库字段名
     */
    private String column;
    /**
     * 对象属性名
     */
    private String property;
    private String jdbcType;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
