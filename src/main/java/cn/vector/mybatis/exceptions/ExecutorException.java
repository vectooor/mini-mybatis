package cn.vector.mybatis.exceptions;

/**
 * @author 勤恳的小码农
 * @date 2020/2/25 11:09
 */
public class ExecutorException extends RuntimeException {
    private static final long serialVersionUID = 3880206998166270511L;

    public ExecutorException() {
        super();
    }

    public ExecutorException(String message) {
        super(message);
    }

    public ExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutorException(Throwable cause) {
        super(cause);
    }
}
