package com.kunyong.rds.controller.evaluate;

import com.kunyong.rds.entity.evaluate.Evaluate;
import com.kunyong.rds.service.EvaluateService;
import com.kunyong.rds.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/8
 */
@Controller
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService service;

    @RequestMapping
    public String init() {
        return "evaluate/evaluateList";
    }

    @RequestMapping("/evaluateList")
    @ResponseBody
    public Page<Evaluate> evaluateList(Pageable pageable) {
        return service.evaluateList(pageable);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseUtil save(Evaluate evaluate){
        return new ResponseUtil(service.save(evaluate));
    }

    @PostMapping("/removeValid")
    @ResponseBody
    public ResponseUtil removeValid(String ids) {
        return new ResponseUtil(service.removeValid(ids));
    }
    @PostMapping("/delete")
    @ResponseBody
    public ResponseUtil delete(String ids){
        return new ResponseUtil(service.delete(ids));
    }

    @GetMapping("/evaluateConfig")
    public String evaluateConfig(int id, Model model){
        model.addAttribute("id",id);
        return "evaluate/config";
    }

    @GetMapping("/getConfigTree")
    @ResponseBody
    public ResponseUtil getConfigTree(int id){
        return  new ResponseUtil(service.getConfigTree(id));
    }

    @GetMapping("/getI")
    @ResponseBody
    public ResponseUtil getI(int subType){
        return new ResponseUtil(service.getI(subType));
    }
}
