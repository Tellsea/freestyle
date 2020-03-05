package cn.tellsea.freestyle.common.exception;


import lombok.NoArgsConstructor;

/**
 * 未授权异常
 *
 * @author: Tellsea
 * @date : 2020/3/4
 */
@NoArgsConstructor
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}
