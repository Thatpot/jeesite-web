/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.mc.entity.Comment;
import com.jeesite.modules.mc.dao.CommentDao;

/**
 * 评论Service
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Service
@Transactional(readOnly=true)
public class CommentService extends CrudService<CommentDao, Comment> {
	
	/**
	 * 获取单条数据
	 * @param comment
	 * @return
	 */
	@Override
	public Comment get(Comment comment) {
		return super.get(comment);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param comment
	 * @return
	 */
	@Override
	public Page<Comment> findPage(Page<Comment> page, Comment comment) {
		return super.findPage(page, comment);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param comment
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Comment comment) {
		super.save(comment);
	}
	
	/**
	 * 更新状态
	 * @param comment
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Comment comment) {
		super.updateStatus(comment);
	}
	
	/**
	 * 删除数据
	 * @param comment
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Comment comment) {
		super.delete(comment);
	}
	
}