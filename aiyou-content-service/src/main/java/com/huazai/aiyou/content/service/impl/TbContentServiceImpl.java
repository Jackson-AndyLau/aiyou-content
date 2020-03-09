package com.huazai.aiyou.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huazai.aiyou.common.module.EasyUIDataGrid;
import com.huazai.aiyou.common.response.AiyouResultData;
import com.huazai.aiyou.common.utils.JsonUtils;
import com.huazai.aiyou.content.mapper.TbContentMapper;
import com.huazai.aiyou.content.pojo.TbContent;
import com.huazai.aiyou.content.pojo.TbContentExample;
import com.huazai.aiyou.content.pojo.TbContentExample.Criteria;
import com.huazai.aiyou.content.service.TbContentService;
import com.huazai.aiyou.content.service.TbJedisClientService;

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

	@Autowired
	private TbJedisClientService tbJedisClientService;

	@Value("${AIYOU_TB_CONTENT_KEY}")
	private String AIYOU_TB_CONTENT_KEY;

	@Override
	public EasyUIDataGrid getTbContentList(Integer pageNum, Integer pageSize, TbContent tbContent)
	{
		// 初始化内容数据载体
		EasyUIDataGrid resultDataGrid = new EasyUIDataGrid();
		try
		{
			// 通过PageHelper实现分页
			PageHelper.startPage(pageNum, pageSize);
			// 查询TbContent分页数据
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			// 这个ID是必须的，否则需要进行对象null的判断
			criteria.andCategoryIdEqualTo(tbContent.getCategoryId());
			// 获取数据集合
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			// 获取分页信息
			PageInfo<TbContent> pageInfo = new PageInfo<>(list);
			// 封装数据
			resultDataGrid.setTotal(pageInfo.getTotal());
			resultDataGrid.setPageSize(pageInfo.getPageSize());
			resultDataGrid.setPageNum(pageInfo.getPageNum());
			resultDataGrid.setPages(pageInfo.getPages());
			resultDataGrid.setRows(list);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultDataGrid;
	}

	@Override
	public AiyouResultData addTbContent(TbContent tbContent)
	{
		try
		{
			// 补全内容信息
			Date date = new Date();
			tbContent.setCreated(date);
			tbContent.setUpdated(date);
			// 新增数据
			tbContentMapper.insert(tbContent);
		} catch (Exception e)
		{
			e.printStackTrace();
			return AiyouResultData.build(-1, "网站内容添加异常");
		}
		return AiyouResultData.ok();
	}

	@Override
	public AiyouResultData deleteTbContent(String ids)
	{
		try
		{
			String[] dsStrings = ids.split(",");
			for (int i = 0; i < dsStrings.length; i++)
			{
				// 同步缓存
				TbContent tbContent = tbContentMapper.selectByPrimaryKey(Long.valueOf(dsStrings[i]));
				tbJedisClientService.hdel(AIYOU_TB_CONTENT_KEY, String.valueOf(tbContent.getCategoryId()));
				// 根据主建ID删除网站内容
				tbContentMapper.deleteByPrimaryKey(Long.valueOf(dsStrings[i]));
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return AiyouResultData.build(-1, "网站内容删除异常");
		}
		return AiyouResultData.ok();
	}

	@Override
	public AiyouResultData updateTbContent(TbContent tbContent)
	{
		try
		{
			TbContent content = tbContentMapper.selectByPrimaryKey(tbContent.getId());
			// 补全信息
			Date date = new Date();
			content.setCategoryId(tbContent.getCategoryId());
			content.setTitle(tbContent.getTitle());
			content.setSubTitle(tbContent.getSubTitle());
			content.setTitleDesc(tbContent.getTitleDesc());
			content.setUrl(tbContent.getUrl());
			content.setPic(tbContent.getPic());
			content.setPic2(tbContent.getPic2());
			content.setUpdated(date);
			content.setContent(tbContent.getContent());
			// 根据主建ID修改网站内容
			tbContentMapper.updateByPrimaryKeyWithBLOBs(content);

			// 首先同步缓存
			tbJedisClientService.hdel(AIYOU_TB_CONTENT_KEY, String.valueOf(tbContent.getCategoryId()));
		} catch (Exception e)
		{
			e.printStackTrace();
			return AiyouResultData.build(-1, "网站内容修改异常");
		}
		return AiyouResultData.ok();
	}

	@Override
	public List<TbContent> geTbContentListByCid(Long cid)
	{
		try
		{
			// 先从缓存中获取数据
			String jsonData = tbJedisClientService.hget(AIYOU_TB_CONTENT_KEY, String.valueOf(cid));
			// 将json反序列化为TbContent集合
			if (StringUtils.isNoneBlank(jsonData))
			{
				List<TbContent> list = JsonUtils.jsonToList(jsonData, TbContent.class);
				return list;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// 根据分类ID查询网站内容
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		// 执行查询并返回
		List<TbContent> list = tbContentMapper.selectByExample(example);

		// 将数据写入缓存
		tbJedisClientService.hset(AIYOU_TB_CONTENT_KEY, String.valueOf(cid), JsonUtils.objectToJson(list));

		return list;
	}

}
