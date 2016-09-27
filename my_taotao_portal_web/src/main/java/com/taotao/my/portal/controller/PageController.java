package com.taotao.my.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.my.content.service.ContentService;
import com.taotao.my.manager.utils.AdItem;
import com.taotao.my.manager.utils.JsonUtils;

@Controller
public class PageController {
	@Value("${AD_CATEGORYID}")
	private Long AD_CATEGORYID;
	@Autowired
	private ContentService contentService;
	/**
	 * 查询大广告位置处的信息
	 * 查询参数:Long categoryId
	 * 返回值(json数据):List<AdItem>
	 */
	@RequestMapping("/index")
	public String toIndex(Model model){
		List<AdItem> list = contentService.findContentListByCategoryId(AD_CATEGORYID);
		model.addAttribute("ad1", JsonUtils.objectToJson(list));
		return "index";
	}
}
