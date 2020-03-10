package cn.tellsea.freestyle.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis plus 自动填充
 *
 * @author Tellsea
 * @date 2020/3/3
 */
@Slf4j
@Component
public class FillMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // boolean hasSetter = metaObject.hasSetter("createTime");
        // if (hasSetter) {
        log.info("FillMetaObjectHandler 开始自动插入新增 ...");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        // }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            log.info("FillMetaObjectHandler 开始自动插入更新 ...");
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        }
    }
}
