package cn.tellsea.vuefreestyle.common.entity;

import cn.tellsea.vuefreestyle.common.enums.BaseEnums;
import cn.tellsea.vuefreestyle.common.enums.StatusEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 公共返回结果集
 *
 * @author Tellsea
 * @date 2020/3/3
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult implements Serializable {

    private int code;

    private String message;

    private Object data;

    public static ResponseResult success() {
        return new ResponseResult().setCode(StatusEnums.OK.getCode())
                .setMessage(StatusEnums.OK.getInfo());
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult().setCode(StatusEnums.OK.getCode())
                .setMessage(StatusEnums.OK.getInfo())
                .setData(data);
    }

    public static ResponseResult error() {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(StatusEnums.SERVER_ERROR.getInfo());
    }

    public static ResponseResult error(String message) {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(message);
    }

    public static ResponseResult error(Object data) {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(StatusEnums.SERVER_ERROR.getInfo())
                .setData(data);
    }

    public static ResponseResult build(BaseEnums baseEnums) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo());
    }

    public static ResponseResult build(BaseEnums baseEnums, Object data) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo())
                .setData(data);
    }
}
