package com.huazai.aiyou.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huazai.aiyou.common.constant.TbConstant;
import com.huazai.aiyou.common.module.EasyUITreeNode;
import com.huazai.aiyou.common.response.AiyouResultData;
import com.huazai.aiyou.content.mapper.TbContentCategoryMapper;
import com.huazai.aiyou.content.pojo.TbContentCategory;
import com.huazai.aiyou.content.pojo.TbContentCategoryExample;
import com.huazai.aiyou.content.pojo.TbContentCategoryExample.Criteria;
import com.huazai.aiyou.content.service.TbContentCategoryService;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description TbContentCategory 服务层实现类
 *              </ul>
 * @className TbContentCategoryServiceImpl
 * @package com.huazai.b2c.aiyou.service
 * @createdTime 2017年06月10日
 *
 * @version V1.0.0
 */
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService
{
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getTbContentCategoryList(Long parentId)
	{
		// 1、取查询参数id，parentId
		// 2、根据parentId查询tb_content_category，查询子节点列表。
		TbContentCategoryExample categoryExample = new TbContentCategoryExample();
		// 3、设置查询条件
		Criteria createCriteria = categoryExample.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		// 在实际的项目开发中，需要通过status来控制
		// createCriteria.andStatusEqualTo(1);
		// 4、获得数据集合
		List<TbContentCategory> contentCategories = tbContentCategoryMapper.selectByExample(categoryExample);
		// 5、封装数据
		List<EasyUITreeNode> treeNodes = new ArrayList<>();
		for (TbContentCategory tbContentCategory : contentCategories)
		{
			EasyUITreeNode treeNode = new EasyUITreeNode();
			treeNode.setId(tbContentCategory.getId());
			treeNode.setText(tbContentCategory.getName());
			treeNode.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}

	@Transactional
	@Override
	public AiyouResultData addTbContentCategory(Long parentId, String name)
	{
		// 1、初始化TbContentCategory对象，并补全参数
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setParentId(parentId);
		contentCategory.setName(name);
		contentCategory.setStatus(TbConstant.CONTENT_CATEGORY_DEFAULT_STATUS);
		contentCategory.setSortOrder(1); // 排列序号，表示同级目录展示的次序，必须大于零的整数即可
		contentCategory.setIsParent(false); // 默认为叶子节点
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		// 2、新增TbContentCategory
		tbContentCategoryMapper.insert(contentCategory);
		// 3、判断父节点的isparent是否为ture,否则需要修改为true
		TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if (!tbContentCategory.getIsParent())
		{
			tbContentCategory.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
		}
		// 4、返回实体TbContentCategory
		return AiyouResultData.ok(contentCategory);
	}

	@Override
	public AiyouResultData updateTbContentCategory(Long id, String name)
	{
		// 1、根据内容ID获取TbContentCategory对象，并设置修改参数
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		contentCategory.setUpdated(new Date());
		// 2、根据ID修改TbContentCategory对象
		tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
		return AiyouResultData.ok();
	}

	@Transactional
	@Override
	public AiyouResultData deleteTbContentCategory(Long id)
	{
		// 1、根据ID获取TbContentCategory对象信息
		TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		// 2、判断是否是父级节点，否则无法删除
		if (tbContentCategory.getIsParent())
		{
			return AiyouResultData.build(0, "不能删除父级节点");
		}
		// 3、正常删除(这儿直接做删除，但是在实际的项目开发中，需要通过status来控制)
		tbContentCategoryMapper.deleteByPrimaryKey(id);
		// 4、查看其父级节点是否还存在叶子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(tbContentCategory.getParentId());
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		// 5、不存在，则将该父级节点的isParent修改false
		if (list.size() == 0)
		{
			TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
			category.setIsParent(false);
			tbContentCategoryMapper.updateByPrimaryKey(category);
		}
		return AiyouResultData.ok();
	}

}
