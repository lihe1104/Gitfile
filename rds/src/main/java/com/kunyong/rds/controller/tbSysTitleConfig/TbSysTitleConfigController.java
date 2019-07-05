package com.kunyong.rds.controller.tbSysTitleConfig;

import com.kunyong.rds.entity.TbSysTitleConfig.TbSysTitleConfig;
import com.kunyong.rds.entity.role.Role;
import com.kunyong.rds.service.TbSysTitleConfigService;
import com.kunyong.rds.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 贺
 *
 */
@Api(tags = "身份信息维护")
@Controller
@RequestMapping("/sysTitleConfig")
public class TbSysTitleConfigController {
    @Autowired
    private TbSysTitleConfigService tbSysTitleConfigService;

    @ApiOperation(value = "列表页面")
    @RequestMapping
    public String sysTitleConfigList(){
        return "sysTitleConfig/sysTileConfigList";
    }
    @GetMapping(value = "/list")
    @ResponseBody
    public Page<TbSysTitleConfig> findAll(TbSysTitleConfig tbSysTitleConfig,Pageable pageable) {
        return tbSysTitleConfigService.findAll(tbSysTitleConfig,pageable);
    }
//    @GetMapping(value = "/list")
//    @ResponseBody
//    public Page<TbSysTitleConfig> findAllByInfoId(Integer info_id,Pageable pageable) {
//        return tbSysTitleConfigService.findAll(tbSysTitleConfig,pageable);
//    }
//    @GetMapping(value = "/list")
//    @ResponseBody
//    public Page<TbSysTitleConfig> findAllByInfoId(Integer info_id,Pageable pageable) {
//        return tbSysTitleConfigService.findAll(tbSysTitleConfig,pageable);
//    }
    @RequestMapping("/edit")
    public String roleEdit(Integer id ,HttpServletRequest request){
        request.setAttribute ("id",id);
        return "sysTitleConfig/addSysTileConfig";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public Result saveRole(@RequestBody TbSysTitleConfig sysTitleConfig) {
        sysTitleConfig = tbSysTitleConfigService.saveSysTitleConfig (sysTitleConfig);
        Result result;
        if ( sysTitleConfig.getId ()>0 ){
            result = new Result (true,200,"成功",sysTitleConfig);
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

        Integer b = tbSysTitleConfigService.deleteByIds (LString);
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
    public TbSysTitleConfig get(@PathVariable Integer id) {
        return tbSysTitleConfigService.getById(id);
    }
    @GetMapping("getLevelKey")
    @ApiOperation(value = "根据用户级别获取")
    @ResponseBody
    public Page<TbSysTitleConfig> get(String level, Pageable pageable) {
        String[] sidList = level.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Integer> LString = new ArrayList<Integer> ();
        for(String str : sidList){
            LString.add(new Integer(str));
        }
        Page<TbSysTitleConfig> byTitleKeys = tbSysTitleConfigService.getLevelKeys (LString, pageable);
        return byTitleKeys;
    }
}
