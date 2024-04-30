package com.izpan.infrastructure.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.izpan.common.domain.LoginUser;
import com.izpan.infrastructure.holder.GlobalUserHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatisPlus 字段自动注入填充器
 * <a href="https://baomidou.com/pages/4c6bcf/">https://baomidou.com/pages/4c6bcf/</a>
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.handler.MybatisPlusMetaObjectHandler
 * @CreateTime 2023/7/21 - 21:50
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    public static final String CREATE_USER = "createUser";
    public static final String CREATE_USER_ID = "createUserId";
    public static final String CREATE_TIME = "createTime";

    public static final String UPDATE_USER = "updateUser";
    public static final String UPDATE_USER_ID = "updateUserId";
    public static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        LoginUser loginUser = GlobalUserHolder.getUser();
        if (metaObject.hasGetter(CREATE_USER)) {
            setFieldValByName(CREATE_USER, loginUser.getRealName(), metaObject);
        }
        if (metaObject.hasGetter(CREATE_USER_ID)) {
            setFieldValByName(CREATE_USER_ID, loginUser.getId(), metaObject);
        }
        if (metaObject.hasGetter(CREATE_TIME)) {
            setFieldValByName(CREATE_TIME, LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LoginUser loginUser = GlobalUserHolder.getUser();
        if (metaObject.hasGetter(UPDATE_USER)) {
            this.setFieldValByName(UPDATE_USER, loginUser.getRealName(), metaObject);
        }
        if (metaObject.hasGetter(UPDATE_USER_ID)) {
            this.setFieldValByName(UPDATE_USER_ID, loginUser.getId(), metaObject);
        }
        if (metaObject.hasGetter(UPDATE_TIME)) {
            this.setFieldValByName(UPDATE_TIME, LocalDateTime.now(), metaObject);
        }
    }
}
