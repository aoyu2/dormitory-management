package com.nuo.msgService.service;

public interface MsgService {


    // 发送短信验证码
    Boolean sendCode(String phone, String code);
}
