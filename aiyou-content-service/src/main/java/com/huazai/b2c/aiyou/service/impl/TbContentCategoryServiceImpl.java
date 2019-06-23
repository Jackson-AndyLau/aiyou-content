package com.huazai.b2c.aiyou.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huazai.b2c.aiyou.common.EasyUITreeNode;
import com.huazai.b2c.aiyou.mapper.TbContentCategoryMapper;
import com.huazai.b2c.aiyou.pojo.TbContentCategory;
import com.huazai.b2c.aiyou.pojo.TbContentCategoryExample;
import com.huazai.b2c.aiyou.pojo.TbContentCategoryExample.Criteria;
import com.huazai.b2c.aiyou.service.TbContentCategoryService;

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

}
