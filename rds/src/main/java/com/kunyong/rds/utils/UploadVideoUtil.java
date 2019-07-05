package com.kunyong.rds.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.kunyong.rds.sample.PutObjectProgressListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author 贺
 * @Description: TODO
 */
public class UploadVideoUtil {
    //上传路径
    private static String endpoint_out = "oss-cn-zhangjiakou.aliyuncs.com";
    //获取路径
    private static String endpoint_in = "oss-cn-zhangjiakou-internal.aliyuncs.com";
    //账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAIbRTfnLaI3sXU";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "NoPQx5AkcBzGc96FTadCVDnJi0W2vN";

    public static String bucketNameStable = "bluefire-stable";

    /**
     * 流式上传接口
     *
     * @param fileName
     * @param inputStream
     */
    public static String UploadStream(String fileName, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, bucketNameStable, fileName, inputStream);
        /* 开启默认上传进度回调 */
         request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
         request.setProgressListener(new PutObjectProgressListener ());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {

            System.out.print("VideoId=" + response.getVideoId() + "\n");
            return response.getVideoId();
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            return response.getVideoId();
        }
    }

    public static void main(String[] args) {
        //一、视频文件上传
        //视频标题(必选)
        String title = "测试标题";
        String fileName = "C:\\Users\\贺\\Desktop\\测试\\hd - 女团玩水比基尼篇.mp4";
        //4.流式上传，如文件流和网络流
        InputStream inputStream = null;
        //4.1 文件流
        try {
            inputStream = new FileInputStream (fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = UploadStream (fileName, inputStream);
        System.out.println (s);

    }

}
