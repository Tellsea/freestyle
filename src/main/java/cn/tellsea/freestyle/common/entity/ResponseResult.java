package cn.tellsea.freestyle.common.entity;

import cn.tellsea.freestyle.common.enums.BaseEnums;
import cn.tellsea.freestyle.common.enums.StatusEnums;
import lombok.Data;
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
public class ResponseResult implements Serializable {

    private int code;

    private String message;

    private int count;

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

    public static ResponseResult successMsg(String message) {
        return new ResponseResult().setCode(StatusEnums.OK.getCode())
                .setMessage(message);
    }

    public static ResponseResult success(BaseEnums baseEnums) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo());
    }

    public static ResponseResult success(BaseEnums baseEnums, Object data) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo())
                .setData(data);
    }

    // 失败

    public static ResponseResult error() {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(StatusEnums.SERVER_ERROR.getInfo());
    }

    public static ResponseResult error(Object data) {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(StatusEnums.SERVER_ERROR.getInfo())
                .setData(data);
    }

    public static ResponseResult errorMsg(String message) {
        return new ResponseResult().setCode(StatusEnums.SERVER_ERROR.getCode())
                .setMessage(message);
    }

    public static ResponseResult error(BaseEnums baseEnums) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo());
    }

    public static ResponseResult error(BaseEnums baseEnums, Object data) {
        return new ResponseResult().setCode(baseEnums.getCode())
                .setMessage(baseEnums.getInfo())
                .setData(data);
    }

    public static ResponseResult table(int count, Object data) {
        return new ResponseResult().setCode(StatusEnums.SELECT_SUCCESS.getCode())
                .setMessage(StatusEnums.SELECT_SUCCESS.getInfo())
                .setCount(count)
                .setData(data);
    }
}
