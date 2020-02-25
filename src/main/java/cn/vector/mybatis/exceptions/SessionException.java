package cn.vector.mybatis.exceptions;

/**
 * @author 勤恳的小码农
 * @date 2020/2/25 11:09
 */
public class SessionException extends RuntimeException {
    private static final long serialVersionUID = 3880206998166270511L;

    public SessionException() {
        super();
    }

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionException(Throwable cause) {
        super(cause);
    }
}
