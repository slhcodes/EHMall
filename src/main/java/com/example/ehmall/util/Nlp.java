package com.example.ehmall.util;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alinlp.model.v20200629.*;
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
     * 商品名获取接口
     */
    public static List<String> getNlpWords(String text) throws ClientException, JSONException {
        //下方第二项和第三项需要替换为您的accessKeyId和accessKeySecret，获取或创建方式详见文档《快速入门》
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",

                "LTAI5tPkCWG6cDG9WF2U5D7z",

                "X2VFC0oNf07yN4X2abVc9wHJr5N4zR");

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
if(i.getWeight()==1) {
    category.add(i.getWord());
}
            System.out.println(i.getWeight());
        }
        return category;
    }
    /**
     * 商品分类获取接口
     */
    public static List<String> getNlpCategory(String text) throws ClientException, JSONException {
        //下方第二项和第三项需要替换为您的accessKeyId和accessKeySecret，获取或创建方式详见文档《快速入门》
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",

                        "LTAI5tPkCWG6cDG9WF2U5D7z",

                        "X2VFC0oNf07yN4X2abVc9wHJr5N4zR");
        IAcsClient client = new DefaultAcsClient(defaultProfile);
        //构造请求参数，其中GetPosChEcom是算法的actionName, 请查找对应的《API基础信息参考》文档并替换为您需要的算法的ActionName，示例详见下方文档中的：更换API请求
        GetCateChEcomRequest request = new GetCateChEcomRequest();
        //固定值，无需更改
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        //固定值，无需更改
        request.setServiceCode("alinlp");
        //固定值，无需更改
        request.setServiceCode("alinlp");
        //请求参数, 具体请参考《API基础信息文档》进行替换与填写
        request.setText(text);
        long start = System.currentTimeMillis();
        //获取请求结果，注意这里的GetPosChEcom也需要替换
        GetCateChEcomResponse response = client.getAcsResponse(request);
        System.out.println(response.hashCode());
        System.out.println(response.getRequestId() + "\n" + response.getData() + "\n" + "cost:" + (System.currentTimeMillis()- start));

        JSONObject obj=new JSONObject(response.getData());
        List<Category> result= JSONArray.parseArray( new JSONObject(obj.get("data").toString()).get("cate_merge_model_result").toString(), Category.class);
        List<String> category=new ArrayList<>();
        //遍历类别列表，返回分数大于0.2的类别
        for(Category i:result)
        {

            category.add(i.getCate_name());
        }
        return category;
    }
    /**
     * 商品品牌获取接口
     */
    public static List<String> getNlpBrand(String text) throws ClientException, JSONException {
        //下方第二项和第三项需要替换为您的accessKeyId和accessKeySecret，获取或创建方式详见文档《快速入门》
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",

                "LTAI5tPkCWG6cDG9WF2U5D7z",

                "X2VFC0oNf07yN4X2abVc9wHJr5N4zR");
        IAcsClient client = new DefaultAcsClient(defaultProfile);
        //构造请求参数，其中GetPosChEcom是算法的actionName, 请查找对应的《API基础信息参考》文档并替换为您需要的算法的ActionName，示例详见下方文档中的：更换API请求
        GetBrandChEcomRequest request = new GetBrandChEcomRequest();
        //固定值，无需更改
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        //固定值，无需更改
        request.setServiceCode("alinlp");
        //固定值，无需更改
        request.setServiceCode("alinlp");
        //请求参数, 具体请参考《API基础信息文档》进行替换与填写
        request.setText(text);
        long start = System.currentTimeMillis();
        //获取请求结果，注意这里的GetPosChEcom也需要替换
        GetBrandChEcomResponse response = client.getAcsResponse(request);
        System.out.println(response.hashCode());
        System.out.println(response.getRequestId() + "\n" + response.getData() + "\n" + "cost:" + (System.currentTimeMillis()- start));

        JSONObject obj=new JSONObject(response.getData());
        List<Brand> result= JSONArray.parseArray( new JSONObject(obj.get("data").toString()).get("brand_merge_model_result").toString(), Brand.class);
        List<String> category=new ArrayList<>();
        //遍历类别列表，返回分数大于0.2的类别
        for(Brand i:result)
        {
                category.add(i.getBrand_name());
        }
        return category;
    }
}
