package com.nuo.msgService.controller;

import com.nuo.msgService.service.MsgService;
import com.nuo.msgService.utils.RandomUtil;
import commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


// 阿里短信服务
@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 发送短信验证码，并存储到redis中
    @GetMapping("send")
    public R sendCode(String phone) {
        // 从redis中取出，避免重复发送验证码
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        // 生成验证码
        code = RandomUtil.getFourBitRandom();
        Boolean isSend  = msgService.sendCode(phone,code);
        if (isSend){
            // 发送成功将验证码存储到redis中
            redisTemplate.opsForValue().set(phone,code,3, TimeUnit.MINUTES);
            return R.ok();
        }else {
            return R.error().message("发送验证码失败");
        }
    }

    @GetMapping("mate")
    public R mateCode(String phone,String code){
        // 从redis中取出验证码
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String mateCode = opsForValue.get(phone);
        System.out.println(mateCode);
        if (!StringUtils.isEmpty(code) && code.equals(mateCode)){
            // 验证码匹配
            return R.ok().data("item",true);
        }
        return R.ok().data("item",false);
    }
}
