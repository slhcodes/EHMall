package com.example.ehmall.util;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alinlp.model.v20200629.GetNerChEcomRequest;
import com.aliyuncs.alinlp.model.v20200629.GetNerChEcomResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

/**
 * nlp api
 * @author 施立豪 ,zhumeng
 * @time 2023/4/11
 */
public class Nlp {
    /**
     * 商品分类获取接口
     */
    public static List<String> getNlpWords(String text) throws ClientException, JSONException {
        //下方第二项和第三项需要替换为您的accessKeyId和accessKeySecret，获取或创建方式详见文档《快速入门》
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",
                "LTAI5tR331r37uq9taNJWs1y",
                "qawPZddKsUEz87kqrrb1ejpm6yQAi6");
        IAcsClient client = new DefaultAcsClient(defaultProfile);
        //构造请求参数，其中GetPosChEcom是算法的actionName, 请查找对应的《API基础信息参考》文档并替换为您需要的算法的ActionName，示例详见下方文档中的：更换API请求
        GetNerChEcomRequest request = new GetNerChEcomRequest();
        //固定值，无需更改
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        //固定值，无需更改
        request.setServiceCode("alinlp");
        //请求参数, 具体请参考《API基础信息文档》进行替换与填写
        request.setText(text);
        long start = System.currentTimeMillis();
        //获取请求结果，注意这里的GetPosChEcom也需要替换
        GetNerChEcomResponse response = client.getAcsResponse(request);
        System.out.println(response.hashCode());
        System.out.println(response.getRequestId() + "\n" + response.getData() + "\n" + "cost:" + (System.currentTimeMillis()- start));

        JSONObject obj=new JSONObject(response.getData());
        List<Word> result= JSONArray.parseArray( obj.get("result").toString(), Word.class);
        List<String> category=new ArrayList<>();

        for(Word i:result)
        {
            if(i.getWeight()==1)
            {
                category.add(i.getWord());
            }
            System.out.println(i.getWeight());
        }
        return category;
    }
}
