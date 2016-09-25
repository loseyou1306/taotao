package com.taotao.my.manager.service;

import com.taotao.my.manager.domain.TbItem;
import com.taotao.my.manager.domain.TbItemDesc;
import com.taotao.my.manager.utils.ResultList;
import com.taotao.my.manager.utils.TaotaoResult;

public interface ItemService {

	TbItem findIbitemById(Long id);
	ResultList findIbItemByPage(Integer page,Integer rows);
	TaotaoResult saveItemAndDesc(TbItem item,TbItemDesc desc);
	
}
