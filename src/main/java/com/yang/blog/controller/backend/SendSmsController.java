package com.yang.blog.controller.backend;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author yangyi
 * @date 2020/6/15 16:27
 * @description：阿里云发送短信
 */
@Controller
public class SendSmsController {

    @Value("${aliyun-dysmsapi.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun-dysmsapi.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun-dysmsapi.sign-name}")
    private String signName;


    @GetMapping("send_sms")
    @ResponseBody
    public ResponseData<Object> send() {
        try {

            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);

            DefaultProfile.addEndpoint(
                    "cn-hangzhou",
                    "cn-hangzhou",
                    "Dysmsapi",
                    "dysmsapi.aliyuncs.com"
            );

            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers("17687410790");
            request.setSignName(signName);
            request.setTemplateCode("SMS_161040044");
            request.setTemplateParam("{\"code\":\"123123\"}");

            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                return ResponseData.success();
            } else {
                return ResponseData.fail(sendSmsResponse.getMessage());
            }
        } catch (ClientException e) {
            return ResponseData.fail(e.getMessage());
        }
    }
}
