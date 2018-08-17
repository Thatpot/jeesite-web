/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.mc.entity.Site;

/**
 * 站点DAO接口
 * @author xuyuxiang
 * @version 2018-07-29
 */
@MyBatisDao
public interface SiteDao extends CrudDao<Site> {
	
}