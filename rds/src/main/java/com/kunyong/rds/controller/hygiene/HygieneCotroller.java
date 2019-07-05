package com.kunyong.rds.controller.hygiene;

import com.kunyong.rds.entity.hygiene.Hygiene;
import com.kunyong.rds.service.HygieneService;
import com.kunyong.rds.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/6
 */
@Controller
@RequestMapping("/hygiene")
public class HygieneCotroller {

    @Autowired
    private HygieneService hygieneService;


    @RequestMapping("/getHygiene")
    @ResponseBody
    public ResponseUtil<Hygiene> getHygiene(int id ){
        return new ResponseUtil<Hygiene>(hygieneService.getHygiene(id));
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseUtil save(Hygiene hygiene){
        return new ResponseUtil(hygieneService.save(hygiene));
    }

    @RequestMapping("/queryByPID")
    @ResponseBody
    public List<Hygiene> queryByPID(Hygiene hygiene){
        return  hygieneService.queryByPID(hygiene.getId());
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseUtil delete(String ids){
        return new ResponseUtil(hygieneService.delete(ids));
    }
}
