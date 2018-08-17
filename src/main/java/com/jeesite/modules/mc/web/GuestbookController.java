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
import com.jeesite.modules.mc.entity.Guestbook;
import com.jeesite.modules.mc.service.GuestbookService;

/**
 * 留言板Controller
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Controller
@RequestMapping(value = "${adminPath}/mc/guestbook")
public class GuestbookController extends BaseController {

	@Autowired
	private GuestbookService guestbookService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Guestbook get(String id, boolean isNewRecord) {
		return guestbookService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("mc:guestbook:view")
	@RequestMapping(value = {"list", ""})
	public String list(Guestbook guestbook, Model model) {
		model.addAttribute("guestbook", guestbook);
		return "modules/mc/guestbookList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("mc:guestbook:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Guestbook> listData(Guestbook guestbook, HttpServletRequest request, HttpServletResponse response) {
		Page<Guestbook> page = guestbookService.findPage(new Page<Guestbook>(request, response), guestbook); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("mc:guestbook:view")
	@RequestMapping(value = "form")
	public String form(Guestbook guestbook, Model model) {
		model.addAttribute("guestbook", guestbook);
		return "modules/mc/guestbookForm";
	}

	/**
	 * 保存留言板
	 */
	@RequiresPermissions("mc:guestbook:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Guestbook guestbook) {
		guestbookService.save(guestbook);
		return renderResult(Global.TRUE, text("保存留言板成功！"));
	}
	
	/**
	 * 停用留言板
	 */
	@RequiresPermissions("mc:guestbook:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Guestbook guestbook) {
		guestbook.setStatus(Guestbook.STATUS_DISABLE);
		guestbookService.updateStatus(guestbook);
		return renderResult(Global.TRUE, text("停用留言板成功"));
	}
	
	/**
	 * 启用留言板
	 */
	@RequiresPermissions("mc:guestbook:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Guestbook guestbook) {
		guestbook.setStatus(Guestbook.STATUS_NORMAL);
		guestbookService.updateStatus(guestbook);
		return renderResult(Global.TRUE, text("启用留言板成功"));
	}
	
}