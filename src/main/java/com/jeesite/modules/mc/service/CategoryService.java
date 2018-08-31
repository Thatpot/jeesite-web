/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.service;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.mc.dao.CategoryDao;
import com.jeesite.modules.mc.entity.Category;
import com.jeesite.modules.mc.entity.Site;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 栏目Service
 * @author xuyuxiang
 * @version 2018-07-28
 */
@Service
@Transactional(readOnly=true)
public class CategoryService extends TreeService<CategoryDao, Category> {

	/**
	 * 获取单条数据
	 * @param category
	 * @return
	 */
	@Override
	public Category get(Category category) {
		return super.get(category);
	}
	
	/**
	 * 查询列表数据
	 * @param category
	 * @return
	 */
	@Override
	public List<Category> findList(Category category) {
		return super.findList(category);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param category
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Category category) {
		category.setSite(new Site(Site.getCurrentSiteId()));
		if (StringUtils.isNotBlank(category.getViewConfig())){
            category.setViewConfig(StringEscapeUtils.unescapeHtml4(category.getViewConfig()));
        }
		super.save(category);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(category.getId(), "category_image");
	}
	
	/**
	 * 更新状态
	 * @param category
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Category category) {
		super.updateStatus(category);
	}
	
	/**
	 * 删除数据
	 * @param category
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Category category) {
		super.delete(category);
	}
	
	/**
	 * 通过编号获取栏目列表
	 */
	public List<Category> findByIds(String ids) {
		List<Category> list = ListUtils.newArrayList();
		String[] idss = StringUtils.split(ids,",");
		if (idss.length>0){
			for(String id : idss){
				Category e = this.get(id);
				if(null != e){
					list.add(e);
				}
			}
		}
		return list;
	}

	public List<Category> findByParentId(String parentId, String siteId) {
		Category entity = new Category();
		entity.getSqlMap().getWhere().and("category_code", QueryType.EQ, parentId).and("site_id", QueryType.EQ, siteId);
		return dao.findList(entity);
	}

	public List<Category> findSecondCategoryList(Category category) {
		// TODO Auto-generated method stub
		category.getSqlMap().getWhere().and("module", QueryType.EQ, Category.ARTICLE_MODULE).and("parent_code", QueryType.EQ, Category.PARETNT_CODE);
		return super.findList(category);
	}
}