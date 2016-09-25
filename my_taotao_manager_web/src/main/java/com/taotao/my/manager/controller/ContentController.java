package com.taotao.my.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.my.content.service.ContentService;
import com.taotao.my.manager.domain.TbContent;
import com.taotao.my.manager.utils.ResultList;
import com.taotao.my.manager.utils.TaotaoResult;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	/**
	 * 需求:根据父类的id分页显示分类内容:
	 * 请求路径:/content/query/list
	 * 请求参数:Integer page,Integer rows,Long categoryId
	 * 返回参数:ResultList
	 */
	@RequestMapping("/content/query/list")
	public @ResponseBody ResultList findContentList(Integer page,Integer rows,Long categoryId){
		ResultList resultList = contentService.findContentList(page, rows, categoryId);
		return resultList;
	}
	/**
	 * 需求:根据分类创建分类内容
	 * 请求路径:/content/save
	 * 请求参数:TbContent
	 * 返回: TaotaoResult
	 */
	@RequestMapping("/content/save")
	public @ResponseBody TaotaoResult insertContent(TbContent content){
		TaotaoResult taotaoResult = contentService.insertContent(content);
		return taotaoResult;
	}
}
