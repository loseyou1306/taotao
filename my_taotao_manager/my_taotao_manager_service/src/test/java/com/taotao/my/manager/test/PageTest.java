package com.taotao.my.manager.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.my.manager.domain.TbItem;
import com.taotao.my.manager.domain.TbItemExample;
import com.taotao.my.manager.mapper.TbItemMapper;
import com.taotao.my.manager.service.ItemService;

public class PageTest {
	
	@Test
	public void testPage(){
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath*:applicationContext-dao.xml");
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		TbItemExample example=new TbItemExample();
		PageHelper.startPage(1, 30);
		List<TbItem> itemList = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(itemList);
		System.out.println("总记录数为:"+pageInfo.getTotal());
		System.out.println("总页数为:"+pageInfo.getPages());
	}
}
