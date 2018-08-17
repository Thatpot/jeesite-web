/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.mc.entity.Category;
import com.jeesite.modules.mc.entity.Link;
import com.jeesite.modules.mc.service.CategoryService;
import com.jeesite.modules.mc.service.LinkService;
import com.jeesite.modules.mc.utils.McUtils;

/**
 * 友情链接Controller
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Controller
@RequestMapping(value = "${adminPath}/mc/link")
public class LinkController extends BaseController {

	@Autowired
	private LinkService linkService;
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Link get(String id, boolean isNewRecord) {
		return linkService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("mc:link:view")
	@RequestMapping(value = {"list", ""})
	public String list(Link link, Model model) {
		model.addAttribute("link", link);
		return "modules/mc/linkList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("mc:link:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Link> listData(Link link, HttpServletRequest request, HttpServletResponse response) {
		Page<Link> page = linkService.findPage(new Page<Link>(request, response), link); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("mc:link:view")
	@RequestMapping(value = "form")
	public String form(Link link, Model model) {
		// 如果当前传参有子节点，则选择取消传参选择
		if(link.getCategory()!=null) {
			Category category = categoryService.get(link.getCategory());
			if (category != null){
				//是父级栏目
				if (!category.getIsTreeLeaf()){
					link.setCategory(null);
				}else {
					link.setCategory(category);
					McUtils.addViewConfigAttribute(model, category);
				}
			}
		}
		model.addAttribute("link", link);
		return "modules/mc/linkForm";
	}

	/**
	 * 保存友情链接
	 */
	@RequiresPermissions("mc:link:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Link link) {
		linkService.save(link);
		return renderResult(Global.TRUE, text("保存友情链接成功！"));
	}
	
	/**
	 * 停用友情链接
	 */
	@RequiresPermissions("mc:link:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Link link) {
		link.setStatus(Link.STATUS_DISABLE);
		linkService.updateStatus(link);
		return renderResult(Global.TRUE, text("停用友情链接成功"));
	}
	
	/**
	 * 启用友情链接
	 */
	@RequiresPermissions("mc:link:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Link link) {
		link.setStatus(Link.STATUS_NORMAL);
		linkService.updateStatus(link);
		return renderResult(Global.TRUE, text("启用友情链接成功"));
	}
	
	/**
	 * 删除友情链接
	 */
	@RequiresPermissions("mc:link:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Link link) {
		linkService.delete(link);
		return renderResult(Global.TRUE, text("删除友情链接成功！"));
	}
	
}