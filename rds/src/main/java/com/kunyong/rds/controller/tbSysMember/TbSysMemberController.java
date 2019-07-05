package com.kunyong.rds.controller.tbSysMember;

import com.kunyong.rds.entity.TbSysMember.TbSysMember;
import com.kunyong.rds.service.TbSysMemberService;
import com.kunyong.rds.utils.AliyunOssUtil;
import com.kunyong.rds.utils.Result;
import io.swagger.annotations.Api;
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
 * @author 贺
 */
@Api(tags = "合作伙伴维护")
@Controller
@RequestMapping("/sysMember")
public class TbSysMemberController {
    @Autowired
    private TbSysMemberService tbSysMemberService;

    @ApiOperation(value = "列表页面")
    @RequestMapping
    public String roleResList() {
        return "sysMember/sysMemberList";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public Page<TbSysMember> findAll(Pageable pageable) {
        return tbSysMemberService.findAll (pageable);
    }

    @RequestMapping("/edit")
    public String roleEdit(Integer id, HttpServletRequest request) {
        request.setAttribute ("id", id);
        return "sysMember/addSysMember";
    }

    @RequestMapping("/showExport")
    public String roleEdit() {
        return "sysMember/showExport";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveRole(@RequestBody TbSysMember tbSysMember) {
        tbSysMember = tbSysMemberService.saveTbSysMember (tbSysMember);
        Result result;
        if ( tbSysMember.getId () > 0 ) {
            result = new Result (true, 200, "成功", tbSysMember);
        } else {
            result = new Result (false, 999, "操作有误！请联系管理员！");
        }
        return result;
    }

    @GetMapping("/delete")
    @ApiOperation(value = "删除")
    @ResponseBody
    public Result deleteRole(@RequestParam("ids") String ids) {
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] sidList = ids.split (",");
        // 将字符串数组转为List<Intger> 类型
        List<Integer> LString = new ArrayList<Integer> ();
        for (String str : sidList) {
            LString.add (new Integer (str));
        }

        Integer b = tbSysMemberService.deleteByIds (LString);
        Result result;
        if ( b > 0 ) {
            result = new Result (true, 200, "成功");
        } else {
            result = new Result (true, 999, "请重新操作");
        }
        return result;
    }

    @GetMapping("get/{id}")
    @ApiOperation(value = "根据id获取")
    @ResponseBody
    public TbSysMember get(@PathVariable Integer id) {
        return tbSysMemberService.getById (id);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    private Result upload(@RequestParam("file")MultipartFile file) {
        List<MultipartFile> files = new ArrayList<> ();
        String[] strings = new String[1];
        try {
            files.add (file);
            strings = AliyunOssUtil.upload (files, AliyunOssUtil.bucketNameStable);
        } catch (Exception e) {
            e.printStackTrace ();
            return new Result (false,103,"连接超时！");
        }
        return new Result (true,200,strings[0]);
    }
    @PostMapping ("/getUrl")
    @ResponseBody
    public Result getUrl(String  name) {
        String webPath ="";
        if(name == null){
            return new Result (true,200,webPath);
        }
        try {
            webPath = AliyunOssUtil.getURL (name, AliyunOssUtil.bucketNameStable);
        } catch (Exception e) {
            System.err.println ("获取失败");
        }
        return new Result (true,200,webPath);
    }
}
