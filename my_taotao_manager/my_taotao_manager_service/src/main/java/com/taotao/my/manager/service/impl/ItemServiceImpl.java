package com.taotao.my.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.my.manager.domain.TbItem;
import com.taotao.my.manager.domain.TbItemExample;
import com.taotao.my.manager.domain.TbItemExample.Criteria;
import com.taotao.my.manager.mapper.TbItemMapper;
import com.taotao.my.manager.service.ItemService;
import com.taotao.my.manager.utils.ResultList;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public TbItem findIbitemById(Long id) {
		TbItemExample example=new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		TbItem item=null;
		if(null !=list && list.size()>0){
			item=list.get(0);
		}
		return item;
	}
	@Override
	public ResultList findIbItemByPage(Integer page, Integer rows) {
		TbItemExample example=new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> itemList = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(itemList);
		Long total = pageInfo.getTotal();
		ResultList resultList = new ResultList();
		resultList.setTotal(total.intValue());
		resultList.setRows(itemList);
		return resultList;
	}

}
