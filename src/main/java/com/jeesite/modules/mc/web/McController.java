/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.mc.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeesite.common.web.BaseController;

/**
 * MCController
 * @author xuyuxiang
 * @version 2018-07-29
 */
@Controller
@RequestMapping(value = "${adminPath}/mc")
public class McController extends BaseController {
	//mc首页
	@RequiresPermissions("mc:view")
	@RequestMapping(value = "index")
	public String index() {
		return "modules/mc/mcIndex";
	}
	
	@RequiresPermissions("mc:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/mc/mcNone";
	}
}
