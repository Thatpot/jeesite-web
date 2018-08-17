/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.mc.dao.ArticleDao;
import com.jeesite.modules.mc.dao.ArticleDataDao;
import com.jeesite.modules.mc.dao.CategoryDao;
import com.jeesite.modules.mc.entity.Article;
import com.jeesite.modules.mc.entity.ArticleData;
import com.jeesite.modules.mc.entity.Category;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 文章Service
 * @author xuyuxiang
 * @version 2018-07-30
 */
@Service
@Transactional(readOnly=true)
public class ArticleService extends CrudService<ArticleDao, Article> {
	
	@Autowired
	private ArticleDataDao articleDataDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	/**
	 * 获取单条数据
	 * @param article
	 * @return
	 */
	@Override
	public Article get(Article article) {
		Article entity = dao.getByEntity(article);
		if (entity != null){
			ArticleData articleData = new ArticleData(entity);
			articleData = articleDataDao.getByEntity(articleData);
			entity.setArticleData(articleData);
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param article
	 * @return
	 */
	@Override
	public Page<Article> findPage(Page<Article> page, Article article) {
		return super.findPage(page, article);
	}
	
	/**
	 * 	根据父栏目id查询下属的所有文章
	 * @param page
	 * @param article
	 * @return
	 */
	public List<List<Article>> findPageByCategoryParent(List<Category> categoryList) {
		List<List<Article>> articleList = new ArrayList<List<Article>>();
		for(int i=0;i<categoryList.size();i++) {
			Category category = (Category) categoryList.get(i);
			Category entity = new Category();
			entity.setParentCode(category.getCategoryCode());
			articleList.add(dao.findPageByCategoryParent(entity));
		}
		return articleList;
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param article
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Article article) {
		
		// 保存上传图片
		FileUploadUtils.saveFileUpload(article.getId(), "article_image");
		if (article.getArticleData().getContent()!=null){
			article.getArticleData().setContent(StringEscapeUtils.unescapeHtml4(
					article.getArticleData().getContent()));
		}
		// 如果没有审核权限，则将当前内容改为待审核状态
		if (!UserUtils.getSubject().isPermitted("mc:article:audit")){
			article.setStatus(Article.STATUS_AUDIT);
		}
		// 如果栏目不需要审核，则将该内容设为发布状态
		if (article.getCategory()!=null && StringUtils.isNotBlank(article.getCategory().getId())){
			Category category = categoryDao.get(article.getCategory());
			if (Global.NO.equals(category.getIsAudit())){
				article.setStatus(Article.STATUS_NORMAL);
			}
		}
		if (StringUtils.isNotBlank(article.getViewConfig())){
            article.setViewConfig(StringEscapeUtils.unescapeHtml4(article.getViewConfig()));
        }
		//新增的文章
		if(article.getIsNewRecord()) {
			ArticleData articleData = article.getArticleData();
			articleData.setArticle(article);
			article.preInsert();
			articleData.preInsert();
			dao.insert(article);
			articleDataDao.insert(articleData);
			
		}else{
			ArticleData articleData = article.getArticleData();
			article.preUpdate();
			dao.updateStatus(article);
			dao.update(article);
			articleDataDao.update(articleData);
		}
	}
	
	/**
	 * 更新状态
	 * @param article
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Article article) {
		super.updateStatus(article);
	}
	
	/**
	 * 删除数据
	 * @param article
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Article article) {
		super.delete(article);
	}


	/**
	 * 通过编号获取内容标题
	 * @return new Object[]{栏目Id,文章Id,文章标题}
	 */
	public List<Object[]> findByIds(String ids) {
		if(ids == null){
			return new ArrayList<Object[]>();
		}
		List<Object[]> list = ListUtils.newArrayList();
		String[] idss = StringUtils.split(ids,",");
		Article e = null;
		for(int i=0;(idss.length-i)>0;i++){
			e = dao.getByEntity(new Article(idss[i]));
			list.add(new Object[]{e.getCategory().getId(),e.getId(),StringUtils.abbr(e.getTitle(),50)});
		}
		return list;
	}

	public void updateHitsAddOne(String contentId) {
		Article article = new Article(contentId);
		article = dao.get(article);
		article.setHits(article.getHits()+1);
		super.update(article);
		
	}

	public void createIndex() {
		// TODO Auto-generated method stub
		
	}

	public Page<Article> search(Page<Article> page, String qStr, String cid, String bd, String ed) {
		// TODO Auto-generated method stub
		return page;
	}

	public Object getArticleList(List<Category> categoryList) {
		// TODO Auto-generated method stub
		return null;
	}
}