/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 评论Entity
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Table(name="${_prefix}mc_comment", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="category_code", attrName="category", label="栏目编号"),
		@Column(name="content_code", attrName="article", label="文章编号"),
		@Column(name="title", attrName="title", label="文章标题", queryType=QueryType.LIKE),
		@Column(name="content", attrName="content", label="评论内容"),
		@Column(name="name", attrName="name", label="评论人"),
		@Column(name="ip", attrName="ip", label="评论IP"),
		@Column(name="audit_user_id", attrName="auditUserId", label="审核人"),
		@Column(name="audit_date", attrName="auditDate", label="审核时间"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Comment extends DataEntity<Comment> {
	
	private static final long serialVersionUID = 1L;
	private String category;		// 栏目编号
	private String article;		// 文章编号
	private String title;		// 文章标题
	private String content;		// 评论内容
	private String name;		// 评论人
	private String ip;		// 评论IP
	private String auditUserId;		// 审核人
	private Date auditDate;		// 审核时间
	
	public Comment() {
		this(null);
	}

	public Comment(String id){
		super(id);
	}
	
	@NotBlank(message="栏目编号不能为空")
	@Length(min=0, max=64, message="栏目编号长度不能超过 64 个字符")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@NotBlank(message="文章编号不能为空")
	@Length(min=0, max=64, message="文章编号长度不能超过 64 个字符")
	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}
	
	@Length(min=0, max=255, message="文章标题长度不能超过 255 个字符")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="评论内容长度不能超过 255 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=100, message="评论人长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="评论IP长度不能超过 100 个字符")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=64, message="审核人长度不能超过 64 个字符")
	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}