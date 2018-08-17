/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.mc.dao.LinkDao;
import com.jeesite.modules.mc.entity.Link;

/**
 * 友情链接Service
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Service
@Transactional(readOnly=true)
public class LinkService extends CrudService<LinkDao, Link> {
	
	/**
	 * 获取单条数据
	 * @param link
	 * @return
	 */
	@Override
	public Link get(Link link) {
		return super.get(link);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param link
	 * @return
	 */
	@Override
	public Page<Link> findPage(Page<Link> page, Link link) {
		return super.findPage(page, link);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param link
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Link link) {
		super.save(link);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(link.getId(), "link_image");
	}
	
	/**
	 * 更新状态
	 * @param link
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Link link) {
		super.updateStatus(link);
	}
	
	/**
	 * 删除数据
	 * @param link
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Link link) {
		super.delete(link);
	}
	
}