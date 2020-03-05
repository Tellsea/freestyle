package cn.tellsea.freestyle.common.exception;


/**
 * 未授权异常
 *
 * @author: Tellsea
 * @date : 2020/3/4
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }

}
