package com.taotao.my.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.my.manager.service.ItemCatService;
import com.taotao.my.manager.utils.EasyUiTreeNode;

@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/item/cat/list")
	public @ResponseBody List<EasyUiTreeNode> findItemCatList(
			@RequestParam(defaultValue = "0", value = "id") Long parent_id) {
		List<EasyUiTreeNode> list = itemCatService.findItemCatList(parent_id);
		return list;
	}
}
