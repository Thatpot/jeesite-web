/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 站点Entity
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Table(name="${_prefix}mc_site", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="name", attrName="name", label="站点名称", queryType=QueryType.LIKE),
		@Column(name="title", attrName="title", label="站点标题"),
		@Column(name="logo", attrName="logo", label="站点Logo"),
		@Column(name="domain", attrName="domain", label="站点域名"),
		@Column(name="description", attrName="description", label="描述"),
		@Column(name="keywords", attrName="keywords", label="关键字"),
		@Column(name="theme", attrName="theme", label="主题"),
		@Column(name="copyright", attrName="copyright", label="版权信息"),
		@Column(name="custom_index_view", attrName="customIndexView", label="自定义站点首页视图"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Site extends DataEntity<Site> {
	 /**
   	 * 模板路径
   	 */
   	public static final String TPL_BASE = "/resources/views/modules/mc/front/themes";
   	
	private static final long serialVersionUID = 1L;
	private String name;		// 站点名称
	private String title;		// 站点标题
	private String logo;		// 站点Logo
	private String domain;		// 站点域名
	private String description;		// 描述
	private String keywords;		// 关键字
	private String theme;		// 主题
	private String copyright;		// 版权信息
	private String customIndexView;		// 自定义站点首页视图
	
	public Site() {
		this(null);
	}

	public Site(String id){
		super(id);
	}
	
	@NotBlank(message="站点名称不能为空")
	@Length(min=0, max=100, message="站点名称长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="站点标题不能为空")
	@Length(min=0, max=100, message="站点标题长度不能超过 100 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="站点Logo长度不能超过 255 个字符")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Length(min=0, max=255, message="站点域名长度不能超过 255 个字符")
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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
	
	@Length(min=0, max=255, message="主题长度不能超过 255 个字符")
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	
	@Length(min=0, max=255, message="自定义站点首页视图长度不能超过 255 个字符")
	public String getCustomIndexView() {
		return customIndexView;
	}

	public void setCustomIndexView(String customIndexView) {
		this.customIndexView = customIndexView;
	}
	
	
	/**
	 * 获取默认站点ID
	 */
	public static String defaultSiteId(){
		return "1";
	}
	
	/**
	 * 判断是否为默认（主站）站点
	 */
	public static boolean isDefault(String id){
		return id != null && id.equals(defaultSiteId());
	}
	
	/**
	 * 获取当前编辑的站点编号
	 */
	public static String getCurrentSiteId(){
		String siteId = (String)UserUtils.getCache("siteId");
		return StringUtils.isNotBlank(siteId)?siteId:defaultSiteId();
	}

    /**
   	 * 获得模板方案路径。如：/WEB-INF/views/modules/cms/front/themes/jeesite
   	 *
   	 * @return
   	 */
   	public String getSolutionPath() {
   		return TPL_BASE + "/" + getTheme();
   	}
}