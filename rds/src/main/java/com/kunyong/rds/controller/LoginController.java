package com.kunyong.rds.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.user.SysUser;
import com.kunyong.rds.respository.user.UserRepository;
import com.kunyong.rds.respository.user.UserRoleLinkRepository;
import com.kunyong.rds.service.LoginService;
import com.kunyong.rds.utils.ResponseUtil;
import com.kunyong.rds.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 *
 * @author 贺
 * @create 2019/6/1
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleLinkRepository roleLinkRepository;


    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/login-error")
    public String loginError(String message, Model model){
        model.addAttribute("msg",message);
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/initMenu")
    @ResponseBody
    public List<Resource> initMenu(){
       return loginService.findAll();
    }

    @GetMapping("/login/sysUser")
    @ResponseBody
    public SysUser sysUser(){
        User user = UserUtil.getLoginUser();
        SysUser sy = userRepository.findByTel(user.getUsername());
        return sy;
    }

    @RequestMapping("/session/invalid")
    public String sessionInvalid(Model model, HttpSession session){
        model.addAttribute("msg","登陆失效，请重新登陆");
        return  "login-error";
    }

    @GetMapping("/user/all")
    @ApiOperation(value = "所有菜单")
    @ResponseBody
    public ResponseUtil resourceAll() {
        return new ResponseUtil(userRepository.findAll());
    }

    @GetMapping("/user/findRoleId")
    @ResponseBody
    public ResponseUtil findRoleId(int roleId){
        return  new ResponseUtil(roleLinkRepository.findByRoleId(roleId));
    }

    @PostMapping("/user/roleUserConfig")
    @ResponseBody
    public ResponseUtil roleUserConfig(int id,String userIds){

    return new ResponseUtil(loginService.roleUserConfig(id,userIds));
    }

    @PostMapping("/user/queryByGroupId")
    @ResponseBody
    public List<SysUser> queryByGroupId(int id,int  roleId){
        return loginService.queryByGroupId(id,roleId);
    }

}
