/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.mc.entity.Article;
import com.jeesite.modules.mc.entity.Category;
import com.jeesite.modules.mc.entity.Site;
import com.jeesite.modules.mc.service.CategoryService;
import com.jeesite.modules.mc.service.FileTplService;
import com.jeesite.modules.mc.service.SiteService;
import com.jeesite.modules.mc.utils.TplUtils;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 栏目Controller
 * @author xuyuxiang
 * @version 2018-07-28
 */
@Controller
@RequestMapping(value = "${adminPath}/mc/category")
public class CategoryController extends BaseController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FileTplService fileTplService;
	@Autowired
	private SiteService siteService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Category get(String categoryCode, boolean isNewRecord) {
		return categoryService.get(categoryCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("mc:category:view")
	@RequestMapping(value = {"list", ""})
	public String list(Category category, Model model) {
		model.addAttribute("category", category);
		return "modules/mc/categoryList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("mc:category:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<Category> listData(Category category) {
		if (StringUtils.isBlank(category.getParentCode())) {
			category.setParentCode(Category.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(category.getCategoryCode())){
			category.setParentCode(null);
		}
		if (StringUtils.isNotBlank(category.getCategoryName())){
			category.setParentCode(null);
		}
		if (StringUtils.isNotBlank(category.getStatus())){
			category.setParentCode(null);
		}
		List<Category> list = categoryService.findList(category);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("mc:category:view")
	@RequestMapping(value = "form")
	public String form(Category category, Model model) {
		// 创建并初始化下一个节点信息
		category = createNextNode(category);
		model.addAttribute("category", category);
		model.addAttribute("listViewList",getTplContent(Category.DEFAULT_TEMPLATE));
        model.addAttribute("category_DEFAULT_TEMPLATE",Category.DEFAULT_TEMPLATE);
        model.addAttribute("contentViewList",getTplContent(Article.DEFAULT_TEMPLATE));
        model.addAttribute("article_DEFAULT_TEMPLATE",Article.DEFAULT_TEMPLATE);
		return "modules/mc/categoryForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("mc:category:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public Category createNextNode(Category category) {
		if (StringUtils.isNotBlank(category.getParentCode())){
			category.setParent(categoryService.get(category.getParentCode()));
		}
		if (category.getIsNewRecord()) {
			Category where = new Category();
			where.setParentCode(category.getParentCode());
			Category last = categoryService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				category.setTreeSort(last.getTreeSort() + 30);
			}
		}
		// 以下设置表单默认数据
		if (category.getTreeSort() == null){
			category.setTreeSort(Category.DEFAULT_TREE_SORT);
		}
		return category;
	}

	/**
	 * 保存栏目
	 */
	@RequiresPermissions("mc:category:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Category category) {
		categoryService.save(category);
		return renderResult(Global.TRUE, text("保存栏目成功！"));
	}
	
	/**
	 * 停用栏目
	 */
	@RequiresPermissions("mc:category:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(Category category) {
		Category where = new Category();
		where.setStatus(Category.STATUS_NORMAL);
		where.setParentCodes("," + category.getId() + ",");
		long count = categoryService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该栏目包含未停用的子栏目！"));
		}
		category.setStatus(Category.STATUS_DISABLE);
		categoryService.updateStatus(category);
		return renderResult(Global.TRUE, text("停用栏目成功"));
	}
	
	/**
	 * 启用栏目
	 */
	@RequiresPermissions("mc:category:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(Category category) {
		category.setStatus(Category.STATUS_NORMAL);
		categoryService.updateStatus(category);
		return renderResult(Global.TRUE, text("启用栏目成功"));
	}
	
	/**
	 * 删除栏目
	 */
	@RequiresPermissions("mc:category:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Category category) {
		categoryService.delete(category);
		return renderResult(Global.TRUE, text("删除栏目成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("mc:category:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<Category> list = categoryService.findList(new Category());
		for (int i=0; i<list.size(); i++){
			Category e = list.get(i);
			// 过滤非正常的数据
			if (!Category.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("title", e.getCategoryName());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getCategoryCode(), e.getCategoryName()));
			map.put("module", e.getModule());
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("mc:category:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(Category category){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		categoryService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
	private List<Category> getTplContent(String prefix) {
	   		List<String> tplList = fileTplService.getNameListByPrefix(siteService.get(Site.getCurrentSiteId()).getSolutionPath());
	   		tplList = TplUtils.tplTrim(tplList, prefix, "");
	   		List<Category> list = new ArrayList<Category>();
	   		for (String item : tplList) {
	   			Category category = new Category();
	   			category.setCategoryCode(item);
	   			list.add(category);
			}
	   		return list;
	 }
}