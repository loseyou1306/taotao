package com.taotao.my.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.my.content.jedis.dao.JedisDao;
import com.taotao.my.content.jedis.impl.JedisClusterDaoImpl;
import com.taotao.my.content.service.ContentService;
import com.taotao.my.manager.domain.TbContent;
import com.taotao.my.manager.domain.TbContentExample;
import com.taotao.my.manager.domain.TbContentExample.Criteria;
import com.taotao.my.manager.mapper.TbContentCategoryMapper;
import com.taotao.my.manager.mapper.TbContentMapper;
import com.taotao.my.manager.utils.AdItem;
import com.taotao.my.manager.utils.JsonUtils;
import com.taotao.my.manager.utils.ResultList;
import com.taotao.my.manager.utils.TaotaoResult;
@Service
public class ContentServiceImpl implements ContentService {
	
	//注入大广告处宽和高
	@Value("${HEIGHT}")
	private Integer HEIGHT;
	@Value("${HEIGHTB}")
	private Integer HEIGHTB;
	@Value("${WIDTH}")
	private Integer WIDTH;
	@Value("${WIDTHB}")
	private Integer WIDTHB;
	@Value("${AD_CACHE}")
	private String AD_CACHE;
	
	@Resource(name="jedisClusterDao")
	private JedisDao jedisDao;
	
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
		//删除缓存,实现缓存和数据库同步
		jedisDao.hdel(AD_CACHE, content.getCategoryId()+"");
		return TaotaoResult.ok();
	}
	/**
	 * 查询大广告位置处的信息
	 * 添加缓存
	 * 查询参数:Long categoryId
	 * 返回值:List<AdItem>
	 */
	@Override
	public List<AdItem> findContentListByCategoryId(Long categoryId) {
		try {
			String hget = jedisDao.hget(AD_CACHE,categoryId+"");
			if(StringUtils.isNotBlank(hget)){
				return JsonUtils.jsonToList(hget, AdItem.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> contentList = contentMapper.selectByExample(example);
		List<AdItem> list=new ArrayList<AdItem>();
		//循环,封装首页分类数据
		for(TbContent content:contentList){
			//创建AdItem对象
			AdItem adItem = new AdItem();
			//设置商品提示
			adItem.setAlt(content.getTitle());
			//设置商品购买地址
			adItem.setHref(content.getUrl());
			//设置首页图片地址
			adItem.setSrc(content.getPic());
			adItem.setSrcB(content.getPic2());
			//设置内容分类处的宽和高以及备份的宽和高
			adItem.setHeight(HEIGHT);
			adItem.setHeightB(HEIGHTB);
			adItem.setWidth(WIDTH);
			adItem.setWidthB(WIDTHB);
			list.add(adItem);
		}
		//将查询到的数据添加到redis数据库中
		//将list集合转换为json字符串,存入redis中
		jedisDao.hset(AD_CACHE, categoryId+"", JsonUtils.objectToJson(list));
		return list;
	}

}
