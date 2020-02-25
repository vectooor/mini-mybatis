package cn.vector.mybatis.session;

/**
 * Creates an {@link SqlSession} out of a connection or a DataSource
 *
 * @author 勤恳的小码农
 * @date 2020/2/22 22:36
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
