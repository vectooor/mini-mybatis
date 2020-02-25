package cn.vector.mybatis.session;

import cn.vector.mybatis.exceptions.XmlParseException;
import cn.vector.mybatis.session.defaults.DefaultSqlSessionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 勤恳的小码农
 * @date 2020/2/23 22:36
 */
public class SqlSessionFactoryBuilder {

    /**
     * 解析xml配置，将配置文件中的配置信息统一存储到一个Configuration的实例中
     * 然后返回一个默认的SqlSessionFactory
     *
     * @param inputStream xml配置文件的流
     * @return
     */
    public SqlSessionFactory build(InputStream inputStream) {

        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element root = document.getRootElement();

            // xml解析成Configuration对象
            Configuration configuration = new Configuration();

            List elements = root.elements();
            for (Iterator<?> it = elements.iterator(); it.hasNext();) {
                Element e = (Element) it.next();
                if ("property".equals(e.getName())) {
                    if ("driverClassName".equals(e.attribute("name").getValue())) {
                        configuration.setDriverClassName((String) e.getData());
                    }
                    else if ("driverClassName".equals(e.attribute("name").getValue())) {
                        configuration.setDriverClassName((String) e.getData());
                    }
                    else if ("url".equals(e.attribute("name").getValue())) {
                        configuration.setUrl((String) e.getData());
                    }
                    else if ("username".equals(e.attribute("name").getValue())) {
                        configuration.setUsername((String) e.getData());
                    }
                    else if ("password".equals(e.attribute("name").getValue())) {
                        configuration.setPassword((String) e.getData());
                    }
                } else if ("mappers".equals(e.getName())) {
                    List mappers = e.elements();
                    List<String> mapperList = new ArrayList<>();
                    for (Iterator<?> mapperIterator = mappers.iterator(); mapperIterator.hasNext();) {
                        Element mapper = (Element) mapperIterator.next();
                        mapperList.add((String) mapper.getData());
                    }
                    configuration.setMappers(mapperList);
                }
            }

            return build(configuration);
        } catch (DocumentException e) {
            throw new XmlParseException("mini-mybatis config file parse cause exception: " + e.getMessage(), e);
        }
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}
