package com.kunyong.rds.controller.media;

import com.kunyong.rds.utils.Result;
import com.kunyong.rds.utils.UploadVideoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/17
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/home/bluefire/web/mediaTmp", method = RequestMethod.POST)
    @ResponseBody
    private Result upload(@RequestParam("file") MultipartFile file) {
        String url = "";
        try {
            if(null != file){
                url = UploadVideoUtil.UploadStream (file.getOriginalFilename (), file.getInputStream ());
                if(null == url){
                    new Result (false,103,"上传文件错误！");
                }
            }else {
                return new Result (false,103,"未上传文件！");
            }

        } catch (Exception e) {
            e.printStackTrace ();
            return new Result (false,103,"连接超时！");
        }
        return new Result (true,200,url);
    }
}
