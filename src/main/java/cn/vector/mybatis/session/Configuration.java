package cn.vector.mybatis.session;

import cn.vector.mybatis.binding.MapperRegistry;
import cn.vector.mybatis.exceptions.XmlParseException;
import cn.vector.mybatis.mapping.MappedStatement;
import cn.vector.mybatis.mapping.ResultMap;
import cn.vector.mybatis.mapping.ResultMapping;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * @author 勤恳的小码农
 * @date 2020/2/22 22:36
 */
public class Configuration {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    /**
     * 用户自定义的Mapper接口对应的xml路径
     */
    private List<String> mappers;

    private static boolean STATEMENTS_IS_BUILD = false;
    /**
     * 以methodName（packageName.className.methodName）作为key，MappedStatement作为value
     * mybatis源码也是类似的思想，mybatis的MappedStatement存储的内容更多，我们模仿时进行简化
     */
    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 获取数据库连接
     * mybatis中，获取连接是通过Environment的DataSource来获取的，我们这里进行简化
     * 数据库连接池就可以在这里做文章了
     *
     * @return 数据库连接
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        // 加载驱动
        Class.forName(driverClassName);
        // 创建连接
        return DriverManager.getConnection(url, username, password);
    }

    public MappedStatement getMappedStatement(String statement) {
        if (!STATEMENTS_IS_BUILD) {
            // 解析所有的mappers，结果存储到mappedStatements中
            buildAllStatements();
        }
        return mappedStatements.get(statement);
    }

    private void buildAllStatements() {
        for (String mapper : mappers) {
            try {
                InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(mapper);
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(inputStream);
                Element root = document.getRootElement();
                String namespace = root.attribute("namespace").getValue();

                // 我们这里做的是简易版的mybatis，所以我们这里假设只有一个resultMap
                // 这里的目的在于：执行SQL结束后，用于对查询结果转换为对象
                String xpath = "/mapper/resultMap";
                List resultMapNodes = document.selectNodes(xpath);
                ResultMap resultMap = new ResultMap();
                for (Object resultMapNode : resultMapNodes) {
                    Element element = (Element) resultMapNode;

                    resultMap.setId(namespace + "." + element.attributeValue("id"));
                    resultMap.setType(element.attributeValue("type"));

                    List<ResultMapping> resultMappings = new ArrayList<>();
                    List childNodes = element.elements();
                    for (Iterator<?> it = childNodes.iterator(); it.hasNext(); ) {
                        Element childNode = (Element) it.next();
                        ResultMapping resultMapping = new ResultMapping();
                        resultMapping.setColumn(childNode.attributeValue("column"));
                        resultMapping.setJdbcType(childNode.attributeValue("jdbcType"));
                        resultMapping.setProperty(childNode.attributeValue("property"));
                        resultMappings.add(resultMapping);
                    }
                    resultMap.setColumns(resultMappings);
                }

                // 我们只通过select来学习mybatis，insert和update这里不考虑
                final Class<?> clazz = Class.forName(namespace);
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    // 查找select节点，并且属性id=methodName
                    String xpathStr = "/mapper/select[@id='" + method.getName() + "']";
                    String key = namespace + "." + method.getName();
                    List nodes = document.selectNodes(xpathStr);

                    for (Iterator<?> it = nodes.iterator(); it.hasNext(); ) {
                        Element element = (Element) it.next();
                        MappedStatement mappedStatement = new MappedStatement();
                        mappedStatement.setSql(element.getData().toString());
                        // 我们这里假设所有的返回都是这一个resultMap
                        mappedStatement.setResultMap(resultMap);
                        mappedStatements.put(key, mappedStatement);
                    }
                }
            } catch (DocumentException | ClassNotFoundException e) {
                throw new XmlParseException("xml file [" + mapper + "] parse cause exception: " + e.getMessage(), e);
            }
        }

        STATEMENTS_IS_BUILD = true;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getMappers() {
        return mappers;
    }

    public void setMappers(List<String> mappers) {
        this.mappers = mappers;
    }
}
