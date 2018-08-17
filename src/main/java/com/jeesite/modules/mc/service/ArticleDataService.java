/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.mc.dao.ArticleDataDao;
import com.jeesite.modules.mc.entity.ArticleData;

/**
 * 文章子表Service
 * @author xuyuxiang
 * @version 2018-07-30
 */
@Service
@Transactional(readOnly=true)
public class ArticleDataService extends CrudService<ArticleDataDao, ArticleData> {
	/**
	 * 获取单条数据
	 * @param articleData
	 * @return
	 */
	@Override
	public ArticleData get(ArticleData articleData) {
		return super.get(articleData);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param articleData
	 * @return
	 */
	@Override
	public Page<ArticleData> findPage(Page<ArticleData> page, ArticleData articleData) {
		return super.findPage(page, articleData);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param articleData
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ArticleData articleData) {
		super.save(articleData);
	}
	
	/**
	 * 更新状态
	 * @param articleData
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ArticleData articleData) {
		super.updateStatus(articleData);
	}
	
	/**
	 * 删除数据
	 * @param articleData
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ArticleData articleData) {
		super.delete(articleData);
	}
}