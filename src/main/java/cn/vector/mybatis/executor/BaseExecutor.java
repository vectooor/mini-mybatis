package cn.vector.mybatis.executor;

import cn.vector.mybatis.executor.resultset.DefaultResultSetHandler;
import cn.vector.mybatis.mapping.MappedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 勤恳的小码农
 * @date 2020/2/23 22:43
 */
public class BaseExecutor implements Executor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExecutor.class);

    private Connection connection;

    public BaseExecutor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <E> List<E> query(MappedStatement statement, Object[] args) throws SQLException {
        try {
            LOGGER.debug("sql=[{}]", statement.getSql());
            PreparedStatement preparedStatement = connection.prepareStatement(statement.getSql());
            if (null != args) {
                for (int i = 0; i < args.length; i++) {
                    if (args[0] instanceof String) {
                        preparedStatement.setString(i + 1, (String)args[i]);
                    }
                    if (args[0] instanceof Integer) {
                        preparedStatement.setInt(i + 1, (Integer) args[i]);
                    }
                    // 其他类型的参数依次类推
                    // mybatis传入的参数有专门的类进行处理，我们这里未做任何处理，上层原样传入的参数
                }
            }
            LOGGER.debug("params=[{}]", args);
            preparedStatement.executeQuery();

            DefaultResultSetHandler resultSetHandler = new DefaultResultSetHandler(statement);

            return resultSetHandler.handleResultSets(preparedStatement);
        } catch (SQLException e) {
            throw e;
        }
    }
}
