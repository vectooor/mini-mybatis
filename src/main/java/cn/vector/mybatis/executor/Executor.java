package cn.vector.mybatis.executor;

import cn.vector.mybatis.mapping.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    <E> List<E> query(MappedStatement statement, Object[] args) throws SQLException;
}
