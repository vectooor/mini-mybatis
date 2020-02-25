package cn.vector.mybatis.session.defaults;

import cn.vector.mybatis.exceptions.ExecutorException;
import cn.vector.mybatis.exceptions.TooManyResultsException;
import cn.vector.mybatis.executor.Executor;
import cn.vector.mybatis.mapping.MappedStatement;
import cn.vector.mybatis.session.Configuration;
import cn.vector.mybatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * The default implementation for {@link SqlSession}.
 * Note that this class is not Thread-Safe.
 *
 * @author 勤恳的小码农
 * @date 2020-02-23
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement, Object[] args) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        List<T> list;
        try {
            list = executor.query(mappedStatement, args);
        } catch (SQLException e) {
            throw new ExecutorException("invoke executor cause exception: " + e.getMessage(), e);
        }

        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
