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
import com.jeesite.modules.mc.entity.Site;
import com.jeesite.modules.mc.service.SiteService;

/**
 * 站点Controller
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Controller
@RequestMapping(value = "${adminPath}/mc/site")
public class SiteController extends BaseController {

	@Autowired
	private SiteService siteService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Site get(String id, boolean isNewRecord) {
		return siteService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("mc:site:view")
	@RequestMapping(value = {"list", ""})
	public String list(Site site, Model model) {
		model.addAttribute("site", site);
		return "modules/mc/siteList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("mc:site:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Site> listData(Site site, HttpServletRequest request, HttpServletResponse response) {
		Page<Site> page = siteService.findPage(new Page<Site>(request, response), site); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("mc:site:view")
	@RequestMapping(value = "form")
	public String form(Site site, Model model) {
		model.addAttribute("site", site);
		return "modules/mc/siteForm";
	}

	/**
	 * 保存站点
	 */
	@RequiresPermissions("mc:site:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Site site) {
		siteService.save(site);
		return renderResult(Global.TRUE, text("保存站点成功！"));
	}
	
	/**
	 * 停用站点
	 */
	@RequiresPermissions("mc:site:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Site site) {
		site.setStatus(Site.STATUS_DISABLE);
		siteService.updateStatus(site);
		return renderResult(Global.TRUE, text("停用站点成功"));
	}
	
	/**
	 * 启用站点
	 */
	@RequiresPermissions("mc:site:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Site site) {
		site.setStatus(Site.STATUS_NORMAL);
		siteService.updateStatus(site);
		return renderResult(Global.TRUE, text("启用站点成功"));
	}
	
	/**
	 * 删除站点
	 */
	@RequiresPermissions("mc:site:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Site site) {
		siteService.delete(site);
		return renderResult(Global.TRUE, text("删除站点成功！"));
	}
	
}