// This file is auto-generated, don't edit it. Thanks.
package com.example.ehmall.service.impl;

import com.aliyun.tea.*;

/**
 * 使用token获取电话号码服务
 * @author 施立豪
 * @time 2023/3/13
 */
public class GetPhoneNumberServiceImpl {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dypnsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dypnsapi.aliyuncs.com";
        return new com.aliyun.dypnsapi20170525.Client(config);
    }

    /**
     * 使用token和配置文件获取电话号码
     * @param token    通过阿里服务获取的token，每个token只能获取一次电话号码
     * @return  电话号码，无效token返回空
     * @throws Exception
     */
    public String GetPhoneNumber(String token) throws Exception {
        /**
         *         工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
         */
        com.aliyun.dypnsapi20170525.Client client = GetPhoneNumberServiceImpl.createClient("LTAI5tR331r37uq9taNJWs1y", "qawPZddKsUEz87kqrrb1ejpm6yQAi6");
        com.aliyun.dypnsapi20170525.models.GetMobileRequest getMobileRequest = new com.aliyun.dypnsapi20170525.models.GetMobileRequest();
        getMobileRequest.setAccessToken(token);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        com.aliyun.dypnsapi20170525.models.GetMobileResponse resp = client.getMobileWithOptions(getMobileRequest, runtime);
        /**
         * 判断token是否无效
         */
        if(resp.body.getMobileResultDTO!=null)
            return resp.body.getMobileResultDTO.mobile;
        return null;

    }
}
