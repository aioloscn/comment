package com.aiolos.comment.dysms;

import com.aiolos.comment.common.CommonResponse;
import com.aiolos.comment.common.Constant;
import com.aiolos.comment.common.CustomizeException;
import com.aiolos.comment.common.EnumError;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 向手机号发送验证码
 * @author Aiolos
 * @date 2019-11-22 11:37
 */
@Slf4j
@Component
public class Dysms {

    private static RedisTemplate redisTemplate;

    @Autowired
    public Dysms(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static CommonResponse sendSms(String phone) throws CustomizeException {

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", Constant.ACCESSKEYID, Constant.ACCESSKEYSECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", Constant.PRODUCT, Constant.DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        int code = (int) ((1 + Math.random()) * 1000000);
        String codeStr = String.valueOf(code).substring(1);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName(Constant.SMSSIGNNAME);
        request.setTemplateCode(Constant.SMSTEMPLATECODE);
        request.setTemplateParam("{\"code\": \""+ codeStr +"\"}");
        request.setOutId("yourOutId");

        SendSmsResponse response = null;

        try {
            redisTemplate.opsForValue().set(phone, codeStr);
            redisTemplate.expire(phone, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("短信验证码模块redis出现异常：{}", e.getMessage());
            throw new CustomizeException(EnumError.REDIS_ERROR);
        }
        log.info("code: {}", codeStr);

//        try {
//            response = acsClient.getAcsResponse(request);
//        } catch (ClientException e) {
//            log.error("向{}发送短信验证码错误, 错误信息：{}", phone, e.getErrMsg());
//            throw new CustomizeException(EnumError.SEND_SMS_FAIL);
//        }
//
//        log.info("向{}发送短信验证码，验证码为：{}，返回状态码和消息为：{}，{}", phone, codeStr, response.getCode(), response.getMessage());
//        if (!response.getCode().equals("OK")) {
//            throw new CustomizeException(EnumError.SEND_SMS_FAIL);
//        }

        return CommonResponse.ok("验证码已发送", null);
    }
}
