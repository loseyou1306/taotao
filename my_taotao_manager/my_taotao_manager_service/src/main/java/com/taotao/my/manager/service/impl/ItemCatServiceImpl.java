package com.taotao.my.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.my.manager.domain.TbItemCat;
import com.taotao.my.manager.domain.TbItemCatExample;
import com.taotao.my.manager.domain.TbItemCatExample.Criteria;
import com.taotao.my.manager.mapper.TbItemCatMapper;
import com.taotao.my.manager.service.ItemCatService;
import com.taotao.my.manager.utils.EasyUiTreeNode;
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Override
	public List<EasyUiTreeNode> findItemCatList(Long parent_id) {
		//创建集合,封装属性列表
		List<EasyUiTreeNode> list=new ArrayList<EasyUiTreeNode>();
		//根据父id查询商品类别
		//创建example对象,类似hibernate的qbc查询
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//设置查询参数
		criteria.andParentIdEqualTo(parent_id);
		//查询
		List<TbItemCat> itemCatList = itemCatMapper.selectByExample(example);
		//循环遍历查询列表,封装节点信息
		for(TbItemCat itemCat:itemCatList){
			//创建easyUitreenode封装节点信息
			EasyUiTreeNode treeNode = new EasyUiTreeNode();
			//设置节点id
			treeNode.setId(itemCat.getId());
			//设置节点名称
			treeNode.setText(itemCat.getName());
			//设置节点状态,判断节点是否有子标题
			treeNode.setState(itemCat.getIsParent()?"closed":"open");
			list.add(treeNode);
		}
		return list;
	}

}
