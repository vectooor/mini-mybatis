package cn.vector.mybatis.executor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author 勤恳的小码农
 * @date 2020/2/25 9:50
 */
public interface ResultSetHandler {
    /**
     * 将查询结果封装成目标对象
     *
     * @param stmt 执行完以后的statement
     * @param <E>
     * @return
     * @throws SQLException
     */
    <E> List<E> handleResultSets(Statement stmt) throws SQLException;
}
