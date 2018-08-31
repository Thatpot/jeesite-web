/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.web;

import java.util.List;

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
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.mapper.JsonMapper;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.mc.entity.Article;
import com.jeesite.modules.mc.entity.Category;
import com.jeesite.modules.mc.service.ArticleService;
import com.jeesite.modules.mc.service.CategoryService;

/**
 * 文章Controller
 * @author xuyuxiang
 * @version 2018-07-30
 */
@Controller
@RequestMapping(value = "${adminPath}/mc/article")
public class ArticleController extends BaseController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Article get(String id, boolean isNewRecord) {
		return articleService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("mc:article:view")
	@RequestMapping(value = {"list", ""})
	public String list(Article article, Model model) {
		model.addAttribute("article", article);
		return "modules/mc/articleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("mc:article:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Article> listData(Article article, HttpServletRequest request, HttpServletResponse response) {
		Page<Article> page = articleService.findPage(new Page<Article>(request, response), article); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("mc:article:view")
	@RequestMapping(value = "form")
	public String form(Article article, Model model) {
		// 如果当前传参有子节点，则选择取消传参选择
		if(article.getCategory()!=null) {
			Category category = categoryService.get(article.getCategory());
			if (category != null){
				//是父级栏目
				if (!category.getIsTreeLeaf()){
					article.setCategory(null);
				}else {
					article.setCategory(category);
					//该栏目是否需要审核
					model.addAttribute("isAudit", (category.getIsAudit().equals(Global.YES))?Global.TRUE:Global.FALSE);
				}
			}
		}
		model.addAttribute("article", article);
		/*model.addAttribute("isAudit", Category);*/
		return "modules/mc/articleForm";
	}

	/**
	 * 保存文章
	 */
	@RequiresPermissions("mc:article:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Article article) {
		articleService.save(article);
		return renderResult(Global.TRUE, text("保存文章成功！"));
	}
	
	
	/**
	 * 发布文章
	 */
	@RequiresPermissions("mc:article:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Article article) {
		article.setStatus(Article.STATUS_NORMAL);
		articleService.updateStatus(article);
		return renderResult(Global.TRUE, text("启用文章成功"));
	}
	
	/**
	 * 删除文章
	 */
	@RequiresPermissions("mc:article:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Article article) {
		articleService.delete(article);
		return renderResult(Global.TRUE, text("删除文章成功！"));
	}
	
	/**
	 * @Description: 文章选择器
	 * @param article, data, checkbox, model
	 * @returnType java.lang.String
	 * @throws
	 * @author xuyuxiang
	 * @date 2018/8/31 14:57
	 */
	@RequiresPermissions("mc:article:view")
	@RequestMapping(value = "articleSelect")
	public String articleSelect(Article article, String data, String checkbox, Model model) {
		model.addAttribute("selectData", data); // 指定默认选中的ID
		model.addAttribute("checkbox", checkbox); // 是否显示复选框，支持多选
		model.addAttribute("article", article); // ModelAttribute
		return "modules/mc/articleSelect";
	}
	
	/**
	 * 通过编号获取文章标题
	 */
	@RequiresPermissions("mc:article:view")
	@ResponseBody
	@RequestMapping(value = "findByIds")
	public String findByIds(String ids) {
		List<Object[]> list = articleService.findByIds(ids);
		return JsonMapper.toJson(list);
	}
	
	@RequiresPermissions({"mc:article:view"})
	@RequestMapping({"exportData"})
	public void exportData(Article article, Boolean isAll, HttpServletResponse response) {
		List<Article> list = articleService.findList(article);
		String fileName = "文章数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
		ExcelExport ee = new ExcelExport("文章数据", Article.class);
		Throwable var7 = null;

		try {
			ee.setDataList(list).write(response, fileName);
		} catch (Throwable var16) {
			var7 = var16;
			throw var16;
		} finally {
			if (ee != null) {
				if (var7 != null) {
					try {
						ee.close();
					} catch (Throwable var15) {
						var7.addSuppressed(var15);
					}
				} else {
					ee.close();
				}
			}

		}

	}
}