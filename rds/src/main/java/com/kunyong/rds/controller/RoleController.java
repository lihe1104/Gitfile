package com.kunyong.rds.controller;

import com.kunyong.rds.entity.role.Role;
import com.kunyong.rds.service.ResRoleLinkService;
import com.kunyong.rds.service.RoleService;
import com.kunyong.rds.utils.ResponseUtil;
import com.kunyong.rds.utils.Result;
import com.sun.org.apache.xpath.internal.operations.Mod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 角色我知道这里应该写注释,可是我奏似不想写，你说气人不气人
 * 
 * @author 贺
 *
 */
@Api(tags = "角色")
@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResRoleLinkService resRoleLinkService;

	@RequestMapping("/roleList")
	public String roleList(){
		return "role/roleList";
	}
	@GetMapping(value = "/list")
	@ResponseBody
	public Page<Role> findAll(Pageable pageable) {
		//检查pageNumber, pageSize非空
//		CheckUtils.checkPageNumberPageSize(pageNumber, pageSize);
		//获取数据
//		if ( StringUtils.isEmpty (role_name_Zh) )
//			role_name_Zh = "";
//		List<Role> list=roleService.findAllList(role_name_Zh,(pageNumber-1)*pageSize, pageSize);
//		//获取计数
//		Integer countNum=roleService.countAllList(role_name_Zh);
//		ApiReturnObject apiReturnObject = ApiReturnUtil.pageManual (pageNumber, pageSize, countNum, list);
		//封装返回
		return roleService.findRoleList(pageable);
	}
	@PostMapping(value = "/all")
	@ResponseBody
	public List<Role> findAll() {
		List<Role> list=roleService.findAllList();
		return list;
	}

	@RequestMapping("/edit")
	public String roleEdit(Integer id ,HttpServletRequest request){
		request.setAttribute ("id",id);
		return "role/addRole";
	}


	@RequestMapping(value = "saveRole", method = RequestMethod.POST)
	@ResponseBody
	public Result saveRole(@RequestBody Role role) {
		role = roleService.saveRole (role);
		Result result;
		if ( role.getId ()>0 ){
			result = new Result (true,200,"成功",role);
		}else {
			result = new Result (false,999,"操作有误！请联系管理员！");
		}
		return result;
	}

	@GetMapping("get/{id}")
	@ApiOperation(value = "根据id获取角色")
	@ResponseBody
	public Role get(@PathVariable Integer id) {
		return roleService.getById(id);
	}
	@GetMapping("/deleteRole")
	@ApiOperation(value = "删除角色")
	@ResponseBody
	public Result deleteRole(@RequestParam("ids")String ids) {
		// 接收包含stuId的字符串，并将它分割成字符串数组
		String[] sidList = ids.split(",");
		// 将字符串数组转为List<Intger> 类型
		List<Integer> LString = new ArrayList<Integer> ();
		for(String str : sidList){
			LString.add(new Integer(str));
		}

		Integer b = roleService.deleteRole (LString);
		Result result;
		if ( b >0 ){
			Integer a =resRoleLinkService.deleteRoleLinks(LString);
			result = new Result (true,200,"成功");
		}else {
			result = new Result (true,999,"请重新操作");
		}
		return result;
	}
	@ApiOperation(value = "角色配置页面")
	@RequestMapping("/roleResList")
	public String roleResList(){
		return "resource/roleResList";
	}


	@RequestMapping("/roleConfig")
	public String roleConfig(int id, Model model){
		model.addAttribute("id",id);
		return "role/roleConfig";
	}

	@PostMapping("/roleResConfig")
	@ResponseBody
	public ResponseUtil roleResConfig(int id,String resIds){

		return new ResponseUtil(roleService.roleResConfig(id,resIds));
	}
}
