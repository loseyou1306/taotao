package com.taotao.my.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.my.manager.domain.TbItem;
import com.taotao.my.manager.domain.TbItemDesc;
import com.taotao.my.manager.domain.TbItemExample;
import com.taotao.my.manager.domain.TbItemExample.Criteria;
import com.taotao.my.manager.mapper.TbItemDescMapper;
import com.taotao.my.manager.mapper.TbItemMapper;
import com.taotao.my.manager.service.ItemService;
import com.taotao.my.manager.utils.IDUtils;
import com.taotao.my.manager.utils.ResultList;
import com.taotao.my.manager.utils.TaotaoResult;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
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
	
	
	@Override
	public TaotaoResult saveItemAndDesc(TbItem item, TbItemDesc desc) {
		long itemId = IDUtils.genItemId();
		item.setId(itemId);;
		Date date = new Date();
		item.setStatus((byte)1);
		item.setCreated(date);
		item.setUpdated(date);
		//保存item商品信息
		tbItemMapper.insert(item);
		desc.setItemId(itemId);
		desc.setCreated(date);
		desc.setUpdated(date);
		tbItemDescMapper.insert(desc);
		return TaotaoResult.ok();
	}

}
