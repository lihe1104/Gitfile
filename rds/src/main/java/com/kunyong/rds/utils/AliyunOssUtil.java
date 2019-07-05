package com.kunyong.rds.utils;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;

public class AliyunOssUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(AliyunOssUtil.class);


    private static String endpoint_out = "oss-cn-zhangjiakou.aliyuncs.com";
    private static String endpoint_in = "oss-cn-zhangjiakou-internal.aliyuncs.com";


    private static String accessKeyId = "LTAIbRTfnLaI3sXU";
    private static String accessKeySecret = "NoPQx5AkcBzGc96FTadCVDnJi0W2vN";

    public static String bucketNameStable = "bluefire-stable";


    public static  String[] upload(List<MultipartFile> files,String _backet) throws Exception{

        String[] pic = {"",""};
        if(files == null) return pic;
        OSS client = null;
        try {
            client = new OSSClient(endpoint_out, accessKeyId, accessKeySecret);
            for (int i = 0; i < files.size(); ++i) {
                MultipartFile file = files.get(i);
                if (!file.isEmpty()) {
                    String uuid = UUID.randomUUID().toString().replaceAll("-","");
                    byte[] bytes = file.getBytes();
                    client.putObject(_backet, uuid, new ByteArrayInputStream(bytes));
                    pic[i] = uuid;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            throw new  Exception("文件上传失败");
        }finally {
            if(client!= null)
                client.shutdown();
        }
        return pic;
    }

    public static String getURL(String key,String _buck)  throws Exception{

        OSS client = null;
        String url = "";

        if(key == null || "".equals(key)) {
            return url;
        }
        try {
            client = new OSSClient(endpoint_in, accessKeyId, accessKeySecret);
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            URL url_tmp = client.generatePresignedUrl(_buck, key, expiration);
            url = "http://"+_buck+"."+endpoint_out+"/"+url_tmp.getFile();
//            System.out.println(url);
        }catch(Exception e) {
            e.printStackTrace();
            throw new  Exception("文件调阅失败");
        }finally {
            if(client!= null)
                client.shutdown();
        }
        return url;
    }

    public static void main(String []args) {
        try {

            String aa = AliyunOssUtil.getURL("1.jpg",AliyunOssUtil.bucketNameStable);
            System.out.println(aa);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}