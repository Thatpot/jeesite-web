/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.mc.entity.Link;

/**
 * 友情链接DAO接口
 * @author xuyuxiang
 * @version 2018-07-29
 */
@MyBatisDao
public interface LinkDao extends CrudDao<Link> {
	
}