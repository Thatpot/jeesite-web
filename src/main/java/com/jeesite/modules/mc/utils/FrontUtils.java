package com.jeesite.modules.mc.utils;

import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import com.jeesite.common.beetl.BeetlUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.io.PropertiesUtils;
@Configuration
public class FrontUtils implements InitializingBean {
	
	private GroupTemplate groupTemplate;
	@Override
	public void afterPropertiesSet() throws Exception {
		// 设置Beetl GroupTemplate，如果不设置，取上下文中唯一的GroupTemplate对象
		this.groupTemplate = BeetlUtils.getResourceGroupTemplate();
		// 设置Beetl全局变量
		Map<String, Object> sharedVars = this.groupTemplate.getSharedVars();
		if (sharedVars == null){
		    sharedVars = MapUtils.newHashMap(); 
		}
        String contentPath =  PropertiesUtils.getInstance().getProperty("server.context-path");
        String frontPath = PropertiesUtils.getInstance().getProperty("frontPath");
        String ctxf = contentPath + frontPath;
		sharedVars.put("ctxf", ctxf);
		this.groupTemplate.setSharedVars(sharedVars);
	}

}
