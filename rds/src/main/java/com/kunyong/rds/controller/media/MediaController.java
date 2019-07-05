package com.kunyong.rds.controller.media;

import com.kunyong.rds.entity.media.MediaInfo;
import com.kunyong.rds.service.MediaService;
import com.kunyong.rds.utils.Result;
import com.kunyong.rds.utils.UploadVideoUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
@Controller
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService service;


    @GetMapping
    public String init(){
        return "media/manager";
    }

    @GetMapping("/mediaList")
    @ResponseBody
    public Page mediaList(MediaInfo media, Pageable pageable){
        Page page = service.mediaList (media, pageable);
        return page;
    }
    @PostMapping(value = "/all")
    @ResponseBody
    public List<MediaInfo> findAll() {
        List<MediaInfo> list=service.findAllList();
        return list;
    }
    @PostMapping(value = "/findByMType")
    @ResponseBody
    public List<MediaInfo> findByMType(@RequestBody MediaInfo mediaInfo) {
        List<MediaInfo> list=service.findAllList(mediaInfo);
        return list;
    }
    @RequestMapping("/edit")
    public String roleEdit(Integer id ,HttpServletRequest request){
        request.setAttribute ("id",id);
        return "media/addMedia";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveRole(@RequestBody MediaInfo media) {

        media = service.save (media);
        Result result;
        if ( media.getId ()>0 ){
            result = new Result (true,200,"成功",media);
        }else {
            result = new Result (false,999,"操作有误！请联系管理员！");
        }
        return result;
    }
    @GetMapping("/delete")
    @ApiOperation(value = "删除")
    @ResponseBody
    public Result deleteRole(@RequestParam("ids")String ids) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] sidList = ids.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Integer> LString = new ArrayList<Integer> ();
        for(String str : sidList){
            LString.add(new Integer(str));
        }

        Integer b = service.deleteByIds (LString);
        Result result;
        if ( b >0 ){
            result = new Result (true,200,"成功");
        }else {
            result = new Result (true,999,"请重新操作");
        }
        return result;
    }
    @GetMapping("get/{id}")
    @ApiOperation(value = "根据id获取")
    @ResponseBody
    public MediaInfo get(@PathVariable Integer id) {
        return service.getById(id);
    }

    @RequestMapping(value = "/home/bluefire/web/mediaTmp", method = RequestMethod.POST)
    @ResponseBody
    private Result upload(@RequestParam("file")MultipartFile file) {
        String url = "";
        try {
            if(null != file){
                url = UploadVideoUtil.UploadStream (file.getOriginalFilename (), file.getInputStream ());
                if(null == url){
                 return   new Result (false,103,"上传文件错误！");
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
