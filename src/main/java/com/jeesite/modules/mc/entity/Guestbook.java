/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 留言板Entity
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Table(name="${_prefix}mc_guestbook", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="type", attrName="type", label="留言分类"),
		@Column(name="content", attrName="content", label="留言内容"),
		@Column(name="name", attrName="name", label="姓名"),
		@Column(name="email", attrName="email", label="邮箱"),
		@Column(name="phone", attrName="phone", label="电话"),
		@Column(name="workunit", attrName="workunit", label="单位"),
		@Column(name="ip", attrName="ip", label="IP"),
		@Column(name="re_user_id", attrName="reUserId", label="回复人"),
		@Column(name="re_date", attrName="reDate", label="回复时间"),
		@Column(name="re_content", attrName="reContent", label="回复内容"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class Guestbook extends DataEntity<Guestbook> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 留言分类
	private String content;		// 留言内容
	private String name;		// 姓名
	private String email;		// 邮箱
	private String phone;		// 电话
	private String workunit;		// 单位
	private String ip;		// IP
	private String reUserId;		// 回复人
	private Date reDate;		// 回复时间
	private String reContent;		// 回复内容
	
	public Guestbook() {
		this(null);
	}

	public Guestbook(String id){
		super(id);
	}
	
	@NotBlank(message="留言分类不能为空")
	@Length(min=0, max=1, message="留言分类长度不能超过 1 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@NotBlank(message="留言内容不能为空")
	@Length(min=0, max=255, message="留言内容长度不能超过 255 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@NotBlank(message="姓名不能为空")
	@Length(min=0, max=100, message="姓名长度不能超过 100 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="邮箱不能为空")
	@Length(min=0, max=100, message="邮箱长度不能超过 100 个字符")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank(message="电话不能为空")
	@Length(min=0, max=100, message="电话长度不能超过 100 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@NotBlank(message="单位不能为空")
	@Length(min=0, max=100, message="单位长度不能超过 100 个字符")
	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}
	
	@NotBlank(message="IP不能为空")
	@Length(min=0, max=100, message="IP长度不能超过 100 个字符")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=64, message="回复人长度不能超过 64 个字符")
	public String getReUserId() {
		return reUserId;
	}

	public void setReUserId(String reUserId) {
		this.reUserId = reUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReDate() {
		return reDate;
	}

	public void setReDate(Date reDate) {
		this.reDate = reDate;
	}
	
	@Length(min=0, max=100, message="回复内容长度不能超过 100 个字符")
	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	
}