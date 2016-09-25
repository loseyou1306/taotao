package com.taotao.my.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.my.content.service.ContentService;
import com.taotao.my.manager.domain.TbContent;
import com.taotao.my.manager.domain.TbContentExample;
import com.taotao.my.manager.domain.TbContentExample.Criteria;
import com.taotao.my.manager.mapper.TbContentCategoryMapper;
import com.taotao.my.manager.mapper.TbContentMapper;
import com.taotao.my.manager.utils.ResultList;
import com.taotao.my.manager.utils.TaotaoResult;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;
	/**
	 * 需求:根据父类的id分页显示分类内容:
	 * 请求路径:/content/category/list
	 * 请求参数:Integer page,Integer rows,Long categoryId
	 * 返回参数:ResultList
	 */
	@Override
	public ResultList findContentList(Integer page, Integer rows, Long categoryId) {
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> contentList = contentMapper.selectByExample(example);
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(contentList);
		ResultList resultList = new ResultList();
		Long total = pageInfo.getTotal();
		resultList.setTotal(total.intValue());
		resultList.setRows(contentList);
		return resultList;
	}
	/**
	 * 需求:根据分类创建分类内容
	 * 请求路径:/content/save
	 * 请求参数:TbContent
	 * 返回: TaotaoResult
	 */
	@Override
	public TaotaoResult insertContent(TbContent content) {
		Date date=new Date();
		content.setCreated(date);
		content.setUpdated(date);
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}

}
