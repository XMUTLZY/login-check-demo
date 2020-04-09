package sch.jake.logincheckdemo.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import sch.jake.logincheckdemo.vo.User;
import sch.jake.logincheckdemo.web.annotation.LoginRequired;

/**
 * Created by lin on 2020/04/10
 */
@Controller
public class TestController {

    //登录校验（成功时在缓存中存放用户信息）
    @PostMapping("/verify")
    @ResponseBody
    public String verify(@RequestBody User user) {
        if ("Jake.lin".equals(user.getUserName()) && "test123".equals(user.getPassword())) {
            Jedis jedis = new Jedis("127.0.0.1", 6379);
            String userJsonStr = JSONObject.toJSONString(user);
            jedis.set("USER_INFO", userJsonStr);
            return "success";
        }
        return "failure";
    }

    //进入主页
    @GetMapping("/index")
    @LoginRequired
    public String index() {
        return "index.html";
    }
}
