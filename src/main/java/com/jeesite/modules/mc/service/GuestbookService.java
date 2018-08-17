/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.mc.entity.Guestbook;
import com.jeesite.modules.mc.dao.GuestbookDao;

/**
 * 留言板Service
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Service
@Transactional(readOnly=true)
public class GuestbookService extends CrudService<GuestbookDao, Guestbook> {
	
	/**
	 * 获取单条数据
	 * @param guestbook
	 * @return
	 */
	@Override
	public Guestbook get(Guestbook guestbook) {
		return super.get(guestbook);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param guestbook
	 * @return
	 */
	@Override
	public Page<Guestbook> findPage(Page<Guestbook> page, Guestbook guestbook) {
		return super.findPage(page, guestbook);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param guestbook
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Guestbook guestbook) {
		super.save(guestbook);
	}
	
	/**
	 * 更新状态
	 * @param guestbook
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Guestbook guestbook) {
		super.updateStatus(guestbook);
	}
	
	/**
	 * 删除数据
	 * @param guestbook
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Guestbook guestbook) {
		super.delete(guestbook);
	}

	public void createIndex() {
		// TODO Auto-generated method stub
		
	}

	public Page<Guestbook> search(Page<Guestbook> page, String qStr, String bd, String ed) {
		// TODO Auto-generated method stub
		return page;
	}
	
}