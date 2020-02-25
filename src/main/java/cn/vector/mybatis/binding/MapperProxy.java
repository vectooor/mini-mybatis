package cn.vector.mybatis.binding;

import cn.vector.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 勤恳的小码农
 * @date 2020/2/23 23:46
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 这里可以做一些预处理
        return sqlSession.selectOne(mapperInterface.getName() + "." + method.getName(), args);
    }
}
