/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.mc.entity.Site;
import com.jeesite.modules.mc.dao.SiteDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 站点Service
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Service
@Transactional(readOnly=true)
public class SiteService extends CrudService<SiteDao, Site> {
	
	/**
	 * 获取单条数据
	 * @param site
	 * @return
	 */
	@Override
	public Site get(Site site) {
		return super.get(site);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param site
	 * @return
	 */
	@Override
	public Page<Site> findPage(Page<Site> page, Site site) {
		return super.findPage(page, site);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param site
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Site site) {
		super.save(site);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(site.getId(), "site_image");
	}
	
	/**
	 * 更新状态
	 * @param site
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Site site) {
		super.updateStatus(site);
	}
	
	/**
	 * 删除数据
	 * @param site
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Site site) {
		super.delete(site);
	}
	
}