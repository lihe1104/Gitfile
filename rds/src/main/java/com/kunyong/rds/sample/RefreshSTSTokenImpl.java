package com.kunyong.rds.sample;

import com.aliyun.vod.upload.dto.STSTokenDTO;
import com.aliyun.vod.upload.impl.VoDRefreshSTSTokenListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vod
 * 生成STS信息实现类
 * @date 2019/6/5
 */
public class RefreshSTSTokenImpl implements VoDRefreshSTSTokenListener {
    private static Logger LOGGER = LoggerFactory.getLogger(UploadVideo.class);
    //账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAIbRTfnLaI3sXU";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "NoPQx5AkcBzGc96FTadCVDnJi0W2vN";
    public STSTokenDTO onRefreshSTSToken() {
        STSTokenDTO stsTokenDTO = new STSTokenDTO();
        stsTokenDTO.setAccessKeyId(accessKeyId);
        stsTokenDTO.setAccessKeySecret(accessKeySecret);
        stsTokenDTO.setSecurityToken("<your sts SecurityToken>");
        return stsTokenDTO;
    }

}
