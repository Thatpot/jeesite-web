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
import com.jeesite.modules.mc.entity.Comment;
import com.jeesite.modules.mc.service.CommentService;

/**
 * 评论Controller
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Controller
@RequestMapping(value = "${adminPath}/mc/comment")
public class CommentController extends BaseController {

	@Autowired
	private CommentService commentService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Comment get(String id, boolean isNewRecord) {
		return commentService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("mc:comment:view")
	@RequestMapping(value = {"list", ""})
	public String list(Comment comment, Model model) {
		model.addAttribute("comment", comment);
		return "modules/mc/commentList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("mc:comment:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Comment> listData(Comment comment, HttpServletRequest request, HttpServletResponse response) {
		Page<Comment> page = commentService.findPage(new Page<Comment>(request, response), comment); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("mc:comment:view")
	@RequestMapping(value = "form")
	public String form(Comment comment, Model model) {
		model.addAttribute("comment", comment);
		return "modules/mc/commentForm";
	}

	/**
	 * 保存评论
	 */
	@RequiresPermissions("mc:comment:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Comment comment) {
		commentService.save(comment);
		return renderResult(Global.TRUE, text("保存评论成功！"));
	}
	
	/**
	 * 停用评论
	 */
	@RequiresPermissions("mc:comment:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Comment comment) {
		comment.setStatus(Comment.STATUS_DISABLE);
		commentService.updateStatus(comment);
		return renderResult(Global.TRUE, text("停用评论成功"));
	}
	
	/**
	 * 启用评论
	 */
	@RequiresPermissions("mc:comment:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Comment comment) {
		comment.setStatus(Comment.STATUS_NORMAL);
		commentService.updateStatus(comment);
		return renderResult(Global.TRUE, text("启用评论成功"));
	}
	
	/**
	 * 删除评论
	 */
	@RequiresPermissions("mc:comment:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Comment comment) {
		commentService.delete(comment);
		return renderResult(Global.TRUE, text("删除评论成功！"));
	}
	
}