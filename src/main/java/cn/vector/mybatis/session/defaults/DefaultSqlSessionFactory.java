package cn.vector.mybatis.session.defaults;

import cn.vector.mybatis.exceptions.SessionException;
import cn.vector.mybatis.executor.BaseExecutor;
import cn.vector.mybatis.session.Configuration;
import cn.vector.mybatis.session.SqlSession;
import cn.vector.mybatis.session.SqlSessionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 这里的核心操作就是创建一个Executor作为DefaultSqlSession的构造参数，然后返回DefaultSqlSession对象
     * Executor包含了数据库的连接信息。mybatis的源码是在Executor的构造函数中传入了一个Transaction对象，其中包含数据库连接信息
     * 我们这里为了简化，直接传入一个Connection对象即可
     *
     * @return
     */
    @Override
    public SqlSession openSession() {
        try {
            Connection connection = configuration.getConnection();
            // mybatis的源码这里的new操作放在Configuration中
            BaseExecutor executor = new BaseExecutor(connection);
            return new DefaultSqlSession(configuration, executor);
        } catch (SQLException | ClassNotFoundException e) {
            throw new SessionException("open session failed, cause exception: " + e.getMessage(), e);
        }
    }
}
