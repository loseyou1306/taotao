package com.taotao.my.manager.service;

import com.taotao.my.manager.domain.TbItem;
import com.taotao.my.manager.utils.ResultList;

public interface ItemService {

	TbItem findIbitemById(Long id);
	ResultList findIbItemByPage(Integer page,Integer rows);
}
