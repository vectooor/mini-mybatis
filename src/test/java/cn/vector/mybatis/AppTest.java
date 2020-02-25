package cn.vector.mybatis;

import cn.vector.mybatis.entity.Tag;
import cn.vector.mybatis.mapper.TagMapper;
import cn.vector.mybatis.session.SqlSession;
import cn.vector.mybatis.session.SqlSessionFactory;
import cn.vector.mybatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @author 勤恳的小码农
 * @date 2020/2/23 22:36
 */
public class AppTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void testSelectOne() {
        InputStream inputStream = AppTest.class.getClassLoader().getResourceAsStream("mini-mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);
        Tag tag = tagMapper.selectByPrimaryKey(2);
        LOGGER.info(tag.toString());
    }

}
