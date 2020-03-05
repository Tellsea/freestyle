package cn.tellsea.freestyle.common.exception;

import lombok.NoArgsConstructor;

/**
 * 系统异常
 *
 * @author: Tellsea
 * @date : 2020/3/4
 */
@NoArgsConstructor
public class FreestyleException extends Exception {

    public FreestyleException(String message) {
        super(message);
    }
}
