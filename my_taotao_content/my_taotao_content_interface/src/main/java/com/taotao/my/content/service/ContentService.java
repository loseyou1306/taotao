package com.taotao.my.content.service;

import com.taotao.my.manager.domain.TbContent;
import com.taotao.my.manager.utils.ResultList;
import com.taotao.my.manager.utils.TaotaoResult;

public interface ContentService {
/**
 * 需求:根据父类的id分页显示分类内容:
 * 请求路径:/content/category/list
 * 请求参数:Integer page,Integer rows,Long categoryId
 * 返回参数:ResultList
 */
	ResultList findContentList(Integer page,Integer rows,Long categoryId);
	/**
	 * 需求:根据分类创建分类内容
	 * 请求路径:/content/save
	 * 请求参数:TbContent
	 * 返回: TaotaoResult
	 */
	TaotaoResult insertContent(TbContent content);
}
