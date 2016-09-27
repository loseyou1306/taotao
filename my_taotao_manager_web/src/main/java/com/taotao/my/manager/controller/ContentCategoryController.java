package com.taotao.my.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.my.content.service.ContentCategoryService;
import com.taotao.my.manager.utils.EasyUiTreeNode;
import com.taotao.my.manager.utils.TaotaoResult;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 需求:根据分类id查询分类属性菜单 参数Long parentId 请求:/content/category/list 返回值:List
	 * <EasyUiTreeNode>
	 */
	@RequestMapping("/content/category/list")
	public @ResponseBody List<EasyUiTreeNode> findContentCategoryList(
			@RequestParam(defaultValue = "0", value = "id") Long parentId) {
		List<EasyUiTreeNode> list = contentCategoryService.findContentCategoryList(parentId);
		return list;
	}
	/**
	 * 需求:添加分类子节点
	 * 参数:Long parentId,String name
	 * 请求:/content/category/create
	 * 返回值:封装被添加子节点id的TaotaoResult
	 */
	@RequestMapping("/content/category/create")
	public @ResponseBody TaotaoResult insertContentCategoryNode(Long parentId,String name){
		TaotaoResult taotaoResult = contentCategoryService.insertContentCategoryNode(parentId, name);
		return taotaoResult;
	}
}
