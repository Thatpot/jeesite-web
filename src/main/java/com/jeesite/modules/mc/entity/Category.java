/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.sys.entity.Office;

/**
 * 栏目Entity
 * @author xuyuxiang
 * @version 2018-07-28
 */
@Table(name="${_prefix}mc_category", alias="a", columns={
		@Column(name="category_code", attrName="categoryCode", label="栏目编码", isPK=true),
		@Column(name="category_name", attrName="categoryName", label="栏目名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="office_code", attrName="office.officeCode", label="归属机构"),
		@Column(name="module", attrName="module", label="栏目模型"),
		@Column(name="image", attrName="image", label="栏目图片"),
		@Column(name="href", attrName="href", label="链接"),
		@Column(name="target", attrName="target", label="目标"),
		@Column(name="description", attrName="description", label="描述"),
		@Column(name="keywords", attrName="keywords", label="关键字"),
		@Column(name="in_menu", attrName="inMenu", label="在导航中显示"),
		@Column(name="in_list", attrName="inList", label="在分类页中显示列表"),
		@Column(name="show_modes", attrName="showModes", label="展现方式"),
		@Column(name="allow_comment", attrName="allowComment", label="是否允许评论"),
		@Column(name="is_audit", attrName="isAudit", label="是否需要审核"),
		@Column(name="custom_list_view", attrName="customListView", label="自定义列表视图"),
		@Column(name="custom_content_view", attrName="customContentView", label="自定义内容视图"),
		@Column(name="view_config", attrName="viewConfig", label="视图配置"),
		@Column(includeEntity=TreeEntity.class),
		@Column(includeEntity=DataEntity.class),
		@Column(name="site_id", attrName="site.id", label="所属站点"),
	}, joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity=Office.class, attrName="office", alias="u3",
			on="u3.office_code = a.office_code", columns={
				@Column(name="office_code", label="机构编码", isPK=true),
				@Column(name="office_name", label="机构名称", isQuery=false),
		})
	}, orderBy="a.tree_sorts, a.category_code"
)
public class Category extends TreeEntity<Category> {
	public static final String ARTICLE_MODULE = "article";
	public static final String PARETNT_CODE = "0";
	
	private static final long serialVersionUID = 1L;
	private String categoryCode;		// 栏目编码
	private String categoryName;		// 栏目名称
	private Office office;		// 归属机构
	private String module;		// 栏目模型
	private String image;		// 栏目图片
	private String href;		// 链接
	private String target;		// 目标
	private String description;		// 描述
	private String keywords;		// 关键字
	private String inMenu;		// 在导航中显示
	private String inList;		// 在分类页中显示列表
	private String showModes;		// 展现方式
	private String allowComment;		// 是否允许评论
	private String isAudit;		// 是否需要审核
	private String customListView;		// 自定义列表视图
	private String customContentView;		// 自定义内容视图
	private String viewConfig;		// 视图配置
	private Site site;		// 所属站点
	
	public Category() {
		super(null);
	}

	public Category(String id){
		super(id);
	}
	
	public Category(String id, Site site){
		this();
		this.id = id;
		this.setSite(site);
	}
	
	@Override
	public Category getParent() {
		return parent;
	}

	@Override
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	@NotBlank(message="栏目名称不能为空")
	@Length(min=0, max=200, message="栏目名称长度不能超过 200 个字符")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@NotNull(message="归属机构不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=20, message="栏目模型长度不能超过 20 个字符")
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	@Length(min=0, max=255, message="栏目图片长度不能超过 255 个字符")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=255, message="链接长度不能超过 255 个字符")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	@Length(min=0, max=20, message="目标长度不能超过 20 个字符")
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	@Length(min=0, max=255, message="描述长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="关键字长度不能超过 255 个字符")
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Length(min=0, max=1, message="在导航中显示长度不能超过 1 个字符")
	public String getInMenu() {
		return inMenu;
	}

	public void setInMenu(String inMenu) {
		this.inMenu = inMenu;
	}
	
	@Length(min=0, max=1, message="在分类页中显示列表长度不能超过 1 个字符")
	public String getInList() {
		return inList;
	}

	public void setInList(String inList) {
		this.inList = inList;
	}
	
	@Length(min=0, max=1, message="展现方式长度不能超过 1 个字符")
	public String getShowModes() {
		return showModes;
	}

	public void setShowModes(String showModes) {
		this.showModes = showModes;
	}
	
	@Length(min=0, max=1, message="是否允许评论长度不能超过 1 个字符")
	public String getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}
	
	@Length(min=0, max=1, message="是否需要审核长度不能超过 1 个字符")
	public String getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	
	@Length(min=0, max=255, message="自定义列表视图长度不能超过 255 个字符")
	public String getCustomListView() {
		return customListView;
	}

	public void setCustomListView(String customListView) {
		this.customListView = customListView;
	}
	
	@Length(min=0, max=255, message="自定义内容视图长度不能超过 255 个字符")
	public String getCustomContentView() {
		return customContentView;
	}

	public void setCustomContentView(String customContentView) {
		this.customContentView = customContentView;
	}
	
	public String getViewConfig() {
		return viewConfig;
	}

	public void setViewConfig(String viewConfig) {
		this.viewConfig = viewConfig;
	}
	
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
}