package com.taotao.my.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.my.content.service.ContentCategoryService;
import com.taotao.my.manager.domain.TbContentCategory;
import com.taotao.my.manager.domain.TbContentCategoryExample;
import com.taotao.my.manager.domain.TbContentCategoryExample.Criteria;
import com.taotao.my.manager.mapper.TbContentCategoryMapper;
import com.taotao.my.manager.utils.EasyUiTreeNode;
import com.taotao.my.manager.utils.TaotaoResult;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	/**
	 * 需求:根据分类id查询分类属性菜单
	 * 参数Long parentId
	 * 请求:/content/category/list
	 * 返回值:List<EasyUiTreeNode>
	 */
	@Override
	public List<EasyUiTreeNode> findContentCategoryList(Long parentId) {
		//设置查询条件
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//查询
		List<TbContentCategory> contentCatrgoryList = contentCategoryMapper.selectByExample(example);
		List<EasyUiTreeNode> list=new ArrayList<EasyUiTreeNode>();
		for(TbContentCategory contentCategory:contentCatrgoryList){
			EasyUiTreeNode treeNode = new EasyUiTreeNode();
			treeNode.setId(contentCategory.getId());
			treeNode.setText(contentCategory.getName());
			treeNode.setState(contentCategory.getIsParent()?"closed":"open");
			list.add(treeNode);
		}
		return list;
	}
	/**
	 * 需求:添加分类子节点
	 * 参数:Long parentId,String name
	 * 请求:/content/category/create
	 * 返回值:封装被添加子节点id的TaotaoResult
	 */
	@Override
	public TaotaoResult insertContentCategoryNode(Long parentId, String name) {
		//保存内容分类表，补全属性
		TbContentCategory category = new TbContentCategory();
		//设置属性参数
		//该类目是否为父类目，1为true，0为false
		category.setIsParent(false);
		category.setName(name);
		//补全属性
		category.setParentId(parentId);
		//取值范围:大于零的整数
		category.setSortOrder(1);
		//状态。可选值:1(正常),2(删除)
		category.setStatus(1);
		Date date = new Date();
		category.setCreated(date);
		category.setUpdated(date);
		//保存:返回主键
		contentCategoryMapper.insert(category);
		//如果创建节点父节点是子节点？修改节点状态
		//根据Id查询父节点状态
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!contentCategory.getIsParent()){
			contentCategory.setIsParent(true);
		}
		contentCategory.setUpdated(date);
		contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		return TaotaoResult.ok(category);
	}

}
