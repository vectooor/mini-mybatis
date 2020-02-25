package cn.vector.mybatis.executor.resultset;

import cn.vector.mybatis.mapping.MappedStatement;
import cn.vector.mybatis.mapping.ResultMapping;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 勤恳的小码农
 * @date 2020/2/25 9:55
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final MappedStatement mappedStatement;

    public DefaultResultSetHandler(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        ResultSet resultSet = stmt.getResultSet();

        final List<ResultMapping> columns = mappedStatement.getResultMap().getColumns();
        List<E> result = new ArrayList<>();
        while (resultSet.next()) {
            try {
                E entity = (E) Class.forName(mappedStatement.getResultMap().getType()).newInstance();
                for (ResultMapping item : columns) {
                    Field name = entity.getClass().getDeclaredField(item.getProperty());
                    name.setAccessible(true);
                    if ("INTEGER".equals(item.getJdbcType())) {
                        name.set(entity, resultSet.getInt(item.getColumn()));
                    } else if ("VARCHAR".equals(item.getJdbcType())) {
                        name.set(entity, resultSet.getString(item.getColumn()));
                    } else if ("TIMESTAMP".equals(item.getJdbcType())) {
                        name.set(entity, resultSet.getDate(item.getColumn()));
                    }
                }
                result.add(entity);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException e) {
                throw new SQLException("result set handler cause exception: " + e.getMessage());
            }
        }

        return result;
    }
}
