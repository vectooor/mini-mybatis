package cn.vector.mybatis.exceptions;

/**
 * @author 勤恳的小码农
 * @date 2020/2/25 11:09
 */
public class XmlParseException extends RuntimeException {
    private static final long serialVersionUID = 3880206998166270511L;

    public XmlParseException() {
        super();
    }

    public XmlParseException(String message) {
        super(message);
    }

    public XmlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParseException(Throwable cause) {
        super(cause);
    }
}
