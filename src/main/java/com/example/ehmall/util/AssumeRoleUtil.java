package com.example.ehmall.util;
import com.aliyun.sts20150401.models.AssumeRoleResponse;
import com.aliyun.tea.*;
import com.example.ehmall.entity.Oss;

/**
 * 对象存储临时token类
 * @time 2023/4/7
 * @author 施立豪
 */
public class AssumeRoleUtil {
        private static String AccesskeyId="LTAI5t8FLfLqJDYunhnkFJdf";
    private static String AccesskeySecret="EiwprZAHpvQbHGhEX66G6h7zVvk8uA";
        /**
         * 使用AK&SK初始化账号Client
         * @param accessKeyId
         * @param accessKeySecret
         * @return Client
         * @throws Exception
         */
        public static com.aliyun.sts20150401.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
            com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                    // 必填，您的 AccessKey ID
                    .setAccessKeyId(AccesskeyId)
                    // 必填，您的 AccessKey Secret
                    .setAccessKeySecret(AccesskeySecret);
            // 访问的域名
            config.endpoint = "sts.cn-hangzhou.aliyuncs.com";
            return new com.aliyun.sts20150401.Client(config);
        }
        public static Oss getOssCredentials()throws Exception {
            // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
            com.aliyun.sts20150401.Client client = AssumeRoleUtil.createClient(AccesskeyId, AccesskeySecret);
            com.aliyun.sts20150401.models.AssumeRoleRequest assumeRoleRequest = new com.aliyun.sts20150401.models.AssumeRoleRequest()
                    .setRoleArn("acs:ram::1543013197072844:role/aliyuncssassumerole")
                    .setRoleSessionName("OSSCliention");
            com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            //List<String> result=new ArrayList<>();
            Oss myoss=null;
            try {
                // 复制代码运行请自行打印 API 的返回值
                AssumeRoleResponse response=client.assumeRoleWithOptions(assumeRoleRequest, runtime);
                myoss=new Oss(response.getBody().getCredentials().accessKeyId,response.getBody().getCredentials().accessKeySecret,response.getBody().getCredentials().securityToken);

//                result.add(response.getBody().getCredentials().accessKeyId);
//                result.add(response.getBody().getCredentials().accessKeySecret);
//                result.add(response.getBody().getCredentials().securityToken);
            } catch (TeaException error) {
                // 如有需要，请打印 error
                com.aliyun.teautil.Common.assertAsString(error.message);
            } catch (Exception exception) {
                TeaException error = new TeaException(exception.getMessage(), exception);
                // 如有需要，请打印 error
                com.aliyun.teautil.Common.assertAsString(error.message);
            }
            return myoss;
        }
}


