package com.huazai.b2c.aiyou.service;

import java.util.List;

import com.huazai.b2c.aiyou.common.EasyUITreeNode;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description TbContentCategory 接口类
 *              </ul>
 * @className TbContentCategoryService
 * @package com.huazai.b2c.service
 * @createdTime 2017年06月10日
 *
 * @version V1.0.0
 */
public interface TbContentCategoryService
{
	
	public List<EasyUITreeNode> getTbContentCategoryList(Long parentId); 
}
