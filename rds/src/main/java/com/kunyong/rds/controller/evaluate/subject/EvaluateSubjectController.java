package com.kunyong.rds.controller.evaluate.subject;

import com.kunyong.rds.entity.evaluate.EvaluateSubType;
import com.kunyong.rds.entity.hygiene.Hygiene;
import com.kunyong.rds.service.EvaluateSubJectService;
import com.kunyong.rds.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/9
 */
@Controller
@RequestMapping("/subject")
public class EvaluateSubjectController {


    @Autowired
    private EvaluateSubJectService subJectService;

    @RequestMapping("/save")
    @ResponseBody
    public ResponseUtil save(EvaluateSubType sub){
        return new ResponseUtil(subJectService.save(sub));
    }

    @RequestMapping("/queryByPID")
    @ResponseBody
    public List<EvaluateSubType> queryByPID(EvaluateSubType sub){
        return  subJectService.queryByPID(sub.getId(),sub.getSubType());
    }
    @GetMapping("/delete")
    @ResponseBody
    public ResponseUtil delete(String ids){
        return new ResponseUtil(subJectService.delete(ids));
    }


}
