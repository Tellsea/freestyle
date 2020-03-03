package cn.tellsea.vuefreestyle.common.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 公共返回结果集
 *
 * @author Tellsea
 * @date 2020/3/3
 */
@Data
public class ResponseResult {

    private int code;

    private String message;

    private Object data;

    public ResponseResult(HttpStatus status) {
        this.code = status.value();
        this.message = null;
        this.data = null;
    }

    public ResponseResult(HttpStatus status, String message) {
        this.code = status.value();
        this.message = message;
        this.data = null;
    }

    public ResponseResult(HttpStatus status, String message, Object data) {
        this.code = status.value();
        this.message = message;
        this.data = data;
    }
}
