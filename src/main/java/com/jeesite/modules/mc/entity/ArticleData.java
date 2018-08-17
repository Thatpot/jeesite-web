/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 文章Entity
 * @author xuyuxiang
 * @version 2018-07-30
 */
@Table(name="${_prefix}mc_article_data", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="content", attrName="content", label="文章内容"),
		@Column(name="copyfrom", attrName="copyfrom", label="文章来源"),
		@Column(name="relation", attrName="relation", label="相关文章"),
		@Column(name="allow_comment", attrName="allowComment", label="是否允许评论"),
		@Column(name="article_id", attrName="article.id", label="编号"),
	}, orderBy="a.id ASC"
)
public class ArticleData extends DataEntity<ArticleData> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 文章内容
	private String copyfrom;		// 文章来源
	private String relation;		// 相关文章
	private String allowComment;		// 是否允许评论
	private Article article;		// 编号 父类
	
	public ArticleData() {
		this(null);
	}


	public ArticleData(Article article){
		this.article = article;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="文章来源长度不能超过 255 个字符")
	public String getCopyfrom() {
		return copyfrom;
	}

	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}
	
	@Length(min=0, max=255, message="相关文章长度不能超过 255 个字符")
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	@Length(min=0, max=1, message="是否允许评论长度不能超过 1 个字符")
	public String getAllowComment() {
		return allowComment;
	}

	public void setAllowComment(String allowComment) {
		this.allowComment = allowComment;
	}
	
	@NotBlank(message="编号不能为空")
	@Length(min=0, max=64, message="编号长度不能超过 64 个字符")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}