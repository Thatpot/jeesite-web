/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.entity;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelField.Align;
import com.jeesite.common.utils.excel.annotation.ExcelFields;

/**
 * 文章Entity
 * @author xuyuxiang
 * @version 2018-07-30
 */
@Table(name="${_prefix}mc_article", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="category_code", attrName="category.categoryCode", label="归属栏目"),
		@Column(name="title", attrName="title", label="标题", queryType=QueryType.LIKE),
		@Column(name="link", attrName="link", label="文章链接"),
		@Column(name="color", attrName="color", label="标题颜色"),
		@Column(name="image", attrName="image", label="文章图片"),
		@Column(name="keywords", attrName="keywords", label="关键字"),
		@Column(name="description", attrName="description", label="描述、摘要"),
		@Column(name="weight", attrName="weight", label="权重"),
		@Column(name="weight_date", attrName="weightDate", label="权重期限"),
		@Column(name="hits", attrName="hits", label="点击数"),
		@Column(name="posid", attrName="posid", label="推荐位"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity=Category.class, attrName="category", alias="u2",
			on="u2.category_code = a.category_code", columns={
				@Column(name="category_code", label="栏目编码", isPK=true),
				@Column(name="category_name", label="栏目名称", isQuery=false),
		}),
	}, orderBy="a.update_date DESC"
)

public class Article extends DataEntity<Article> {
	@ExcelFields({
			@ExcelField(title="文章标题", attrName="title", align=Align.CENTER, sort=30),
			@ExcelField(title="关键词", attrName="keywords", align=Align.CENTER, sort=40),
			@ExcelField(title="权重", attrName="weight", align=Align.LEFT, sort=50),
			@ExcelField(title="点击数", attrName="hits", align=Align.CENTER, sort=60),
			@ExcelField(title="状态", attrName="status", align=Align.CENTER, sort=70),
			@ExcelField(title="发布者", attrName="createBy", align=Align.CENTER, sort=80),
			@ExcelField(title="发布时间", attrName="create_date", align=Align.CENTER, sort=900, type=ExcelField.Type.EXPORT, dataFormat="yyyy-MM-dd HH:mm"),
	})
	
	private static final long serialVersionUID = 1L;
	private Category category;		// 归属栏目
	private String title;		// 标题
	private String link;		// 文章链接
	private String color;		// 标题颜色
	private String image;		// 文章图片
	private String keywords;		// 关键字
	private String description;		// 描述、摘要
	private Long weight;		// 权重
	private Date weightDate;		// 权重期限
	private Long hits;		// 点击数
	private String posid;		// 推荐位
	private ArticleData articleData;		// 子表列表
	
	
	public Article() {
		super(null);
	}

	public Article(String id){
		super(id);
	}
	
	public Article(Category category){
		this();
		this.category = category;
	}
	
	@NotNull(message="归属栏目不能为空")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@NotBlank(message="标题不能为空")
	@Length(min=0, max=255, message="标题长度不能超过 255 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="文章链接长度不能超过 255 个字符")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Length(min=0, max=50, message="标题颜色长度不能超过 50 个字符")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Length(min=0, max=255, message="文章图片长度不能超过 255 个字符")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=255, message="关键字长度不能超过 255 个字符")
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@Length(min=0, max=255, message="描述、摘要长度不能超过 255 个字符")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWeightDate() {
		return weightDate;
	}

	public void setWeightDate(Date weightDate) {
		this.weightDate = weightDate;
	}
	
	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}
	
	@Length(min=0, max=10, message="推荐位长度不能超过 10 个字符")
	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}
	
	public ArticleData getArticleData() {
		return articleData;
	}

	public void setArticleData(ArticleData articleData) {
		this.articleData = articleData;
	}
	
}