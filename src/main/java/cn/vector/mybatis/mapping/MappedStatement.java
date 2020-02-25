package cn.vector.mybatis.mapping;

/**
 * @author 勤恳的小码农
 * @date 2020/2/24 15:50
 */
public final class MappedStatement {
    /**
     * 执行的SQL
     */
    private String sql;
    /**
     * 方法调用时的参数
     */
    private Object[] args;
    /**
     * 为了简化，这里我们只写一个
     */
    private ResultMap resultMap;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public ResultMap getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMap resultMap) {
        this.resultMap = resultMap;
    }
}
