package com.huazai.b2c.aiyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huazai.b2c.aiyou.common.EasyUIDataGrid;
import com.huazai.b2c.aiyou.mapper.TbContentMapper;
import com.huazai.b2c.aiyou.pojo.TbContent;
import com.huazai.b2c.aiyou.pojo.TbContentExample;
import com.huazai.b2c.aiyou.pojo.TbContentExample.Criteria;
import com.huazai.b2c.aiyou.service.TbContentService;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description TbContent实现类
 *              </ul>
 * @className TbContentServiceImpl
 * @package com.huazai.b2c.aiyou.service.impl
 * @createdTime 2017年06月11日
 *
 * @version V1.0.0
 */
@Service
public class TbContentServiceImpl implements TbContentService
{

	@Autowired
	private TbContentMapper tbContentMapper;

	@Override
	public EasyUIDataGrid getTbContentList(Integer pageNum, Integer pageSize, TbContent tbContent)
	{
		// 1、通过PageHelper实现分页
		PageHelper.startPage(pageNum, pageSize);
		// 2、查询TbContent分页数据
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		// 这个ID是必须的，否则需要进行对象null的判断
		criteria.andCategoryIdEqualTo(tbContent.getCategoryId());
		// 3、获取数据集合
		List<TbContent> list = tbContentMapper.selectByExample(example);
		// 4、获取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		// 5、封装数据
		EasyUIDataGrid resultDataGrid = new EasyUIDataGrid();
		resultDataGrid.setTotal(pageInfo.getTotal());
		resultDataGrid.setPageSize(pageInfo.getPageSize());
		resultDataGrid.setPageNum(pageInfo.getPageNum());
		resultDataGrid.setPages(pageInfo.getPages());
		resultDataGrid.setRows(list);
		return resultDataGrid;
	}

}
