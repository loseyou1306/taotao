package com.taotao.my.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.my.manager.domain.TbItem;
import com.taotao.my.manager.service.ItemService;

@Controller
public class PageController {
	/**
	 * 需求：页面跳转
	 * 请求：http://localhost:8081/index（首页）
	 * 	   http://localhost:8081/item-add(商品添加页面)
	 * 	   http://localhost:8081/item-list(商品分页列表)
	 */
	@RequestMapping("{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
	
}
