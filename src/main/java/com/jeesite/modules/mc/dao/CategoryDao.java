/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.dao;

import java.util.List;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.mc.entity.Article;
import com.jeesite.modules.mc.entity.Category;

/**
 * 栏目DAO接口
 * @author xuyuxiang
 * @version 2018-07-28
 */
@MyBatisDao
public interface CategoryDao extends TreeDao<Category> {

	List<Category> findByParentIdAndSiteId(Category entity);

	List<Article> findPageByCategoryParent(Category category);
	
}