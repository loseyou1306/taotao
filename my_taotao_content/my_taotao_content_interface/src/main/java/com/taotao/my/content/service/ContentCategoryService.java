package com.taotao.my.content.service;

import java.util.List;

import com.taotao.my.manager.utils.EasyUiTreeNode;
import com.taotao.my.manager.utils.TaotaoResult;

public interface ContentCategoryService {
/**
 * 需求:根据分类id查询分类属性菜单
 * 参数Long parentId
 * 请求:/content/category/list
 * 返回值:List<EasyUiTreeNode>
 */
	List<EasyUiTreeNode> findContentCategoryList(Long parentId);
	/**
	 * 需求:添加分类子节点
	 * 参数:Long parentId,String name
	 * 请求:/content/category/create
	 * 返回值:封装被添加子节点id的TaotaoResult
	 */
	TaotaoResult insertContentCategoryNode(Long parentId,String name);
}
