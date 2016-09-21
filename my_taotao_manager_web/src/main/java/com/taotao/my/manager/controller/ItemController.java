package com.taotao.my.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.support.Parameter;
import com.taotao.my.manager.domain.TbItem;
import com.taotao.my.manager.service.ItemService;
import com.taotao.my.manager.utils.ResultList;

@Controller
public class ItemController {
	//注入Service接口代理对象
	@Autowired
	private ItemService itemService;
	/**
	 * 需求：根据Id查询商品
	 * 请求：/http://localhost:8082/item/1235343
	 * 参数：Long itemId
	 * 返回值：Json格式tbItem对象
	 */
	@RequestMapping("item/{id}")
	public @ResponseBody TbItem findIbitemById(@PathVariable Long id){
		//查询
		TbItem item=itemService.findIbitemById(id);
		//System.out.println("12121212121212121212121212121212121212121212121212");
		return item;
	}
	/**
	 * 需求：查询商品进行分页列表展示
	 * 请求：http://localhost:8081/item/list?page=1&rows=30
	 * 参数：Integer page,Integer rows
	 * 返回值：Json格式ResutlModel
	 */
	@RequestMapping("item/list")
	public @ResponseBody ResultList findIbItemByPage(@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="50")Integer rows){
		//执行查询
		ResultList resultList = itemService.findIbItemByPage(page, rows);
		return resultList;
	}
	
	
}
