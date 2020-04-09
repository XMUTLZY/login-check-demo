package sch.jake.logincheckdemo.web.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;
import sch.jake.logincheckdemo.web.annotation.LoginRequired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by lin on 2020/04/10
 * @Tip: 登录校验拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {  //判断处理请求的handler类型
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();  //获取处理请求的方法
        if (method.getAnnotation(LoginRequired.class) != null) {    //判断该方法上是否有自定义的注解
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            String userJsonStr = jedis.get("USER_INFO");
            if (!StringUtils.hasText(userJsonStr)) {    //判断缓存中是否有用户数据
                return false;
            }
        }
        return true;
    }
}
