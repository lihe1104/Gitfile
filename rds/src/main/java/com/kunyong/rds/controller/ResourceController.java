package com.kunyong.rds.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kunyong.rds.entity.res.Resource;
import com.kunyong.rds.entity.role.RoleDto;
import com.kunyong.rds.service.ResRoleLinkService;
import com.kunyong.rds.service.ResourceService;
import com.kunyong.rds.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 权限相关接口
 * 
 * @author 贺
 *
 */
@Api(tags = "权限")
@Controller
@RequestMapping("resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResRoleLinkService resRoleLinkService;


	@GetMapping("/all")
	@ApiOperation(value = "所有菜单")
	@ResponseBody
	public JSONArray resourceAll() {
		List<Resource> resourceAll = resourceService.listAll();
		JSONArray array = new JSONArray();
		setResourceTree (0, resourceAll, array);
		return array;
	}
	/**
	 * 菜单树
	 *
	 * @param pId
	 * @param resourceList
	 * @param array
	 */
	private void setResourceTree(Integer pId, List<Resource> resourceList, JSONArray array) {
		for (Resource per : resourceList) {
			if (per.getpId ()==pId) {
				String string = JSONObject.toJSONString(per);
				JSONObject parent = (JSONObject) JSONObject.parse(string);
				array.add(parent);

				if (resourceList.stream().filter(p -> p.getpId()==per.getId()).findAny() != null) {
					JSONArray child = new JSONArray();
					parent.put("child", child);
					setResourceTree (per.getId(), resourceList, child);
				}
			}
		}
	}
	@ApiOperation(value = "根据角色id获取权限")
	@GetMapping("/findRoleId")
	@ResponseBody
	public List<Resource> listByRoleId(long roleId) {

		return resourceService.listByRoleId(roleId);
	}
	@PostMapping("/saveResRoleLink")
	@ApiOperation(value = "保存角色权限")
	@ResponseBody
	public Result saveResRoleLink(@RequestBody RoleDto roleDto) {
		Result result;
		if ( resRoleLinkService.saveResRoleLink(roleDto)){
			result = new Result (true,200,"成功");
		}else {
			result = new Result (false,999,"操作有误！请联系管理员！");
		}
		return result;
	}
////	@Autowired
////	private PermissionDao permissionDao;
////	@Autowired
////	private PermissionService permissionService;
//
//	@ApiOperation(value = "当前登录用户拥有的权限")
//	@GetMapping("/current")
//	public List<Resource> permissionsCurrent() {
//		LoginUser loginUser = UserUtil.getLoginUser();
//		List<Permission> list = loginUser.getPermissions();
//		final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1))
//				.collect(Collectors.toList());
//
////		setChild(permissions);
////
////		return permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
//		// 2018.06.09 支持多级菜单
//        List<Permission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
//        firstLevel.parallelStream().forEach(p -> {
//            setChild(p, permissions);
//        });
//
//        return firstLevel;
//	}
//
//	/**
//	 * 设置子元素
//	 * 2018.06.09
//	 *
//	 * @param p
//	 * @param permissions
//	 */
//	private void setChild(Permission p, List<Permission> permissions) {
//		List<Permission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
//		p.setChild(child);
//		if (!CollectionUtils.isEmpty(child)) {
//			child.parallelStream().forEach(c -> {
//				//递归设置子元素，多级菜单支持
//				setChild(c, permissions);
//			});
//		}
//	}
//
////	private void setChild(List<Permission> permissions) {
////		permissions.parallelStream().forEach(per -> {
////			List<Permission> child = permissions.stream().filter(p -> p.getParentId().equals(per.getId()))
////					.collect(Collectors.toList());
////			per.setChild(child);
////		});
////	}
//
//	/**
//	 * 菜单列表
//	 *
//	 * @param pId
//	 * @param permissionsAll
//	 * @param list
//	 */
//	private void setPermissionsList(Long pId, List<Resource> permissionsAll, List<Resource> list) {
//		for (Resource per : permissionsAll) {
//			if (per.getParentId().equals(pId)) {
//				list.add(per);
//				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
//					setPermissionsList(per.getId(), permissionsAll, list);
//				}
//			}
//		}
//	}
//
//	@GetMapping
//	@ApiOperation(value = "菜单列表")
//	@PreAuthorize("hasAuthority('sys:menu:query')")
//	public List<Permission> permissionsList() {
//		List<Permission> permissionsAll = permissionDao.listAll();
//
//		List<Permission> list = Lists.newArrayList();
//		setPermissionsList(0L, permissionsAll, list);
//
//		return list;
//	}
//

//
//	@GetMapping("/parents")
//	@ApiOperation(value = "一级菜单")
//	@PreAuthorize("hasAuthority('sys:menu:query')")
//	public List<Permission> parentMenu() {
//		List<Permission> parents = permissionDao.listParents();
//
//		return parents;
//	}
//

//
//	@GetMapping(params = "roleId")
//	@ApiOperation(value = "根据角色id获取权限")
//	@PreAuthorize("hasAnyAuthority('sys:menu:query','sys:role:query')")
//	public List<Permission> listByRoleId(Long roleId) {
//		return permissionDao.listByRoleId(roleId);
//	}
//
//	@LogAnnotation
//	@PostMapping
//	@ApiOperation(value = "保存菜单")
//	@PreAuthorize("hasAuthority('sys:menu:add')")
//	public void save(@RequestBody Permission permission) {
//		permissionDao.save(permission);
//	}
//
//	@GetMapping("/{id}")
//	@ApiOperation(value = "根据菜单id获取菜单")
//	@PreAuthorize("hasAuthority('sys:menu:query')")
//	public Permission get(@PathVariable Long id) {
//		return permissionDao.getById(id);
//	}
//
//	@LogAnnotation
//	@PutMapping
//	@ApiOperation(value = "修改菜单")
//	@PreAuthorize("hasAuthority('sys:menu:add')")
//	public void update(@RequestBody Permission permission) {
//		permissionService.update(permission);
//	}
//
//	/**
//	 * 校验权限
//	 *
//	 * @return
//	 */
//	@GetMapping("/owns")
//	@ApiOperation(value = "校验当前用户的权限")
//	public Set<String> ownsPermission() {
//		List<Permission> permissions = UserUtil.getLoginUser().getPermissions();
//		if (CollectionUtils.isEmpty(permissions)) {
//			return Collections.emptySet();
//		}
//
//		return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
//				.map(Permission::getPermission).collect(Collectors.toSet());
//	}
//
//	@LogAnnotation
//	@DeleteMapping("/{id}")
//	@ApiOperation(value = "删除菜单")
//	@PreAuthorize("hasAuthority('sys:menu:del')")
//	public void delete(@PathVariable Long id) {
//		permissionService.delete(id);
//	}
}
