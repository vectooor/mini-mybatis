package cn.vector.mybatis.binding;

import cn.vector.mybatis.session.Configuration;
import cn.vector.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @author 勤恳的小码农
 * @date 2020/2/23 23:49
 */
public class MapperRegistry {
    private final Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, type);
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, mapperProxy);
    }
}
