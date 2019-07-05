package com.kunyong.rds.controller.evaluate.config;

import com.kunyong.rds.entity.evaluate.evaluateConfig.EvaluateConfig;
import com.kunyong.rds.service.EvaluateConfigService;
import com.kunyong.rds.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
@Controller
@RequestMapping("/evaluateConfig")
public class EvaluateConfigController {

    @Autowired
    private EvaluateConfigService service;

    @GetMapping
    public String init(){
        return "evaluate/evaluate-config";
    }

    @GetMapping("/configList")
    @ResponseBody
    public Page configList(Pageable pageable){
        return service.configList(pageable);
    }

    @PostMapping("/init")
    @ResponseBody
    public ResponseUtil init(@RequestBody List<EvaluateConfig> list){
        return new ResponseUtil(service.init(list));
    }


    @GetMapping("/evaluateConfig")
    public String evaluateConfig(int id,String subType, Model model){
        model.addAttribute("id",id  );
        model.addAttribute("subType",subType);
        return "evaluate/config-subject";
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseUtil save(EvaluateConfig evaluateConfig){
        return new ResponseUtil(service.save(evaluateConfig));
    }
}
