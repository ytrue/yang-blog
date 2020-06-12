package com.yang.blog.controller.backend;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yang.blog.util.ResponseData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * 七牛云图片上传
 */
@Controller
public class UploadController {

    @Value("${qiniu-cloud.access-key}")
    String accessKey;

    @Value("${qiniu-cloud.secret-key}")
    private String secretKey;

    @Value("${qiniu-cloud.bucket}")
    private String bucket;

    @Value("${qiniu-cloud.domain}")
    private String domain;



    /**
     * 普通图片七牛云上传
     *
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("upload")
    public ResponseData<Object> upload(@RequestParam("file") MultipartFile file) {

        /**
         * 构造一个带指定Zone对象的配置类
         * Configuration cfg = new Configuration(Zone.zone0());//Zone.zone0() 指华东
         * Configuration cfg = new Configuration(Zone.zone1());//华北
         * Configuration cfg = new Configuration(Zone.zone2());//华南
         */
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = UUID.randomUUID() + file.getOriginalFilename();
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(file.getInputStream(), key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return ResponseData.success(domain + "/" + putRet.key);
        } catch (QiniuException ex) {
            Response r = ex.response;
            return ResponseData.fail(r.error);
        } catch (IOException e) {
            return ResponseData.success(e.getMessage());
        }
    }


    /**
     * 编辑器图片上传
     */
    @ResponseBody
    @PostMapping("ckeditor_upload_image")
    public Object ckeditorUpload(@RequestParam("upload") MultipartFile file) {
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = UUID.randomUUID() + file.getOriginalFilename();
        HashMap<String, Object> resultMap = new HashMap<>();
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(file.getInputStream(), key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            resultMap.put("uploaded", "1");
            resultMap.put("fileName", putRet.key);
            resultMap.put("url", domain + "/" + putRet.key);
            return resultMap;
        } catch (QiniuException ex) {
            Response r = ex.response;
            HashMap<String, String> errMap = new HashMap<>();
            errMap.put("message", r.error);
            resultMap.put("uploaded", "0");
            resultMap.put("error", errMap);
            return resultMap;
        } catch (IOException e) {
            HashMap<String, String> errMap = new HashMap<>();
            errMap.put("message", e.getMessage());
            resultMap.put("uploaded", "0");
            resultMap.put("error", errMap);
            return resultMap;
        }
    }
}
