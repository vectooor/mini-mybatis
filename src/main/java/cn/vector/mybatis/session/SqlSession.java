package cn.vector.mybatis.session;

/**
 * The primary Java interface for working with this mini-mybatis.
 *
 * @author 勤恳的小码农
 * @date 2020/2/22 22:36
 */
public interface SqlSession extends Cloneable {

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     * 通过指定的statement key(值=Mapper接口方法的全称)和参数查询一行记录
     *
     * @param <T>       the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @param args      A parameter object to pass to the statement.
     * @return Mapped object
     */
    <T> T selectOne(String statement, Object[] args);

    /**
     * Retrieves a mapper.
     *
     * @param <T>  the mapper type
     * @param type Mapper interface class
     * @return a mapper bound to this SqlSession
     */
    <T> T getMapper(Class<T> type);
}
