package com.kunyong.rds.controller.media;

import com.kunyong.rds.entity.TbSysTitleConfig.TbSysTitleConfig;
import com.kunyong.rds.entity.media.MediaConfig;
import com.kunyong.rds.entity.media.MediaInfo;
import com.kunyong.rds.entity.org.Org;
import com.kunyong.rds.service.MediaConfigService;
import com.kunyong.rds.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 贺
 * @Description: TODO
 */
@Controller
@RequestMapping("/mediaConfig")
public class MediaConfigController {
    @Autowired
    private MediaConfigService mediaConfigService;

    @GetMapping
    public String init() {
        return "media/mediaConfigList";
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public Page findAll(MediaInfo mediaInfo, MediaConfig mediaConfig, TbSysTitleConfig tbSysTitleConfig, Org org, Pageable pageable) {
        if ( StringUtils.isEmpty (org.getOrgFullName ()) ) {
            org.setOrgFullName ("");
        }
        if ( StringUtils.isEmpty (tbSysTitleConfig.getLevel_desc ()) ) {
            tbSysTitleConfig.setLevel_desc ("");
        }
        mediaConfig.setOrg (org);
        mediaConfig.setTbSysTitleConfig (tbSysTitleConfig);
        mediaConfig.setMediaInfo (mediaInfo);
        Page<MediaConfig> page = mediaConfigService.findAll (mediaConfig, pageable);
        return page;
    }

    @RequestMapping("/edit")
    public String roleEdit(Integer id, HttpServletRequest request) {
        request.setAttribute ("id", id);
        return "media/mediaConfigEdit";
    }

    @PostMapping("/save")
    @ResponseBody
    public Result saveRole(MediaConfig mediaConfig, String title_ids) {
        System.out.println (title_ids);
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] sidList = title_ids.split (",");
        // 将字符串数组转为List<Intger> 类型
        List<MediaConfig> LString = new ArrayList<MediaConfig> ();
        mediaConfigService.deleteByStoreId (mediaConfig.getStore_id (), mediaConfig.getInfo_id ());
        for (String str : sidList) {
            MediaConfig mediaConfigNew = new MediaConfig ();
            mediaConfigNew.setInfo_id (mediaConfig.getInfo_id ());
            mediaConfigNew.setStore_id (mediaConfig.getStore_id ());
            mediaConfigNew.setTitle_id (Integer.parseInt (str));
            LString.add (mediaConfigNew);
        }
//        mediaConfig = mediaConfigService.save (mediaConfig);
        List<MediaConfig> list = mediaConfigService.save (LString);
        Result result;
        if ( list.get (0).getId () != null && list.get (0).getId () > 0 ) {
            result = new Result (true, 200, "成功", list);
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

        Integer b = mediaConfigService.deleteByIds (LString);
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
    public MediaConfig get(@PathVariable Integer id) {
        return mediaConfigService.getById (id);
    }

    @GetMapping("getStoreId/{store_id}/{info_id}")
    @ApiOperation(value = "根据store_id获取")
    @ResponseBody
    public List<MediaConfig> getStoreId(@PathVariable Integer store_id, @PathVariable Integer info_id) {
        return mediaConfigService.getByStoreId (store_id, info_id);
    }
}
