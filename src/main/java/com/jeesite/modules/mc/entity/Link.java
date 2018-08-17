/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.entity;

import java.util.Date;

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

/**
 * 友情链接Entity
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Table(name="${_prefix}mc_link", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="category_code", attrName="category.categoryCode", label="归属栏目"),
		@Column(name="title", attrName="title", label="链接名称", queryType=QueryType.LIKE),
		@Column(name="color", attrName="color", label="标题颜色"),
		@Column(name="image", attrName="image", label="链接图片"),
		@Column(name="href", attrName="href", label="链接地址"),
		@Column(name="weight", attrName="weight", label="权重"),
		@Column(name="weight_date", attrName="weightDate", label="权重期限"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity=Category.class, attrName="category", alias="u2",
			on="u2.category_code = a.category_code", columns={
				@Column(name="category_code", label="机构编码", isPK=true),
				@Column(name="category_name", label="机构名称", isQuery=false),
		}),
	}, orderBy="a.update_date DESC"
)
public class Link extends DataEntity<Link> {
	
	private static final long serialVersionUID = 1L;
	private Category category;		// 归属栏目
	private String title;		// 链接名称
	private String color;		// 标题颜色
	private String image;		// 链接图片
	private String href;		// 链接地址
	private Long weight;		// 权重
	private Date weightDate;		// 权重期限
	
	public Link() {
		super(null);
	}

	public Link(String id){
		super(id);
	}
	
	public Link(Category category){
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
	
	@NotBlank(message="链接名称不能为空")
	@Length(min=0, max=255, message="链接名称长度不能超过 255 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=50, message="标题颜色长度不能超过 50 个字符")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	@Length(min=0, max=255, message="链接图片长度不能超过 255 个字符")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=255, message="链接地址长度不能超过 255 个字符")
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
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
	
}