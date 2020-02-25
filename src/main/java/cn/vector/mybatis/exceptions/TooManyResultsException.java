package cn.vector.mybatis.exceptions;

/**
 * @author 勤恳的小码农
 * @date 2020/2/25 11:09
 */
public class TooManyResultsException extends RuntimeException {
    private static final long serialVersionUID = 3880206998166270511L;

    public TooManyResultsException() {
        super();
    }

    public TooManyResultsException(String message) {
        super(message);
    }

    public TooManyResultsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyResultsException(Throwable cause) {
        super(cause);
    }
}
