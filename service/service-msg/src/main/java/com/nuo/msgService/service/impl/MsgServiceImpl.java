package com.nuo.msgService.service.impl;

import com.nuo.msgService.client.MsgClient;
import com.nuo.msgService.service.MsgService;
import com.nuo.msgService.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class MsgServiceImpl implements MsgService {

    // 发送短信验证码
    @Override
    public Boolean sendCode(String phone, String code) {
        com.aliyun.teaopenapi.Client client;
        {
            try {
                client = MsgClient.createClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
                com.aliyun.teaopenapi.models.Params params = MsgClient.createApiInfo();

                System.out.println("手机号是："+phone);
                // query params
                Map<String, Object> queries = new HashMap<>();
                queries.put("PhoneNumbers", phone);
                queries.put("SignName", "家乐的宿舍报修服务");
                queries.put("TemplateCode", "SMS_271575331");
                queries.put("TemplateParam", "{\"code\":\""+ code +"\"}");
                // runtime options
                com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
                com.aliyun.teaopenapi.models.OpenApiRequest request = new com.aliyun.teaopenapi.models.OpenApiRequest()
                        .setQuery(com.aliyun.openapiutil.Client.query(queries));

                // 复制代码运行请自行打印 API 的返回值
                // 返回值为 Map 类型，可从 Map 中获得三类数据：响应体 body、响应头 headers、HTTP 返回的状态码 statusCode
                Map<String, ?> stringMap = client.callApi(params, request, runtime);
                Set<String> keySet = stringMap.keySet();
                for (String s : keySet) {
                    System.out.println(stringMap.get(s));
                    if (s.equals("statusCode")){
                        Integer num = (Integer) stringMap.get(s);
                        if (num == 200){
                            return true;
                        }else {
                            return false;
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
