package sch.jake.logincheckdemo.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lin on 2020/04/10
 */
@Target(ElementType.METHOD) //标记在方法上
@Retention(RetentionPolicy.RUNTIME) //生命周期（运行时有效）
public @interface LoginRequired {
}
