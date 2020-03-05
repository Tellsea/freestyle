package cn.tellsea.freestyle.common.enums;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * 异常枚举类
 *
 * @author Tellsea
 * @date 2019/7/13
 */
@AllArgsConstructor
public enum StatusEnums implements BaseEnums {

    OK(200, "OK"),
    SAVE_SUCCESS(200, "新增成功"),
    DELETE_SUCCESS(200, "删除成功"),
    UPDATE_SUCCESS(200, "更新成功"),
    SELECT_SUCCESS(200, "查询成功"),
    SAVE_ERROR(500, "新增失败！"),
    DELETE_ERROR(500, "删除失败！"),
    UPDATE_ERROR(500, "更新失败！"),
    SELECT_ERROR(500, "查询失败！"),
    SERVER_ERROR(500, "服务器错误，请稍后重试！"),
    ;

    @Setter
    private int code;

    @Setter
    private String info;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getInfo() {
        return info;
    }
}
