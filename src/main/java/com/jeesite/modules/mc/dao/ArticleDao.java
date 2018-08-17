/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.mc.entity.Article;
import com.jeesite.modules.mc.entity.Category;

/**
 * 文章DAO接口
 * @author xuyuxiang
 * @version 2018-07-30
 */
@MyBatisDao
public interface ArticleDao extends CrudDao<Article> {
	List<Article> findPageByCategoryParent(Category category);
}