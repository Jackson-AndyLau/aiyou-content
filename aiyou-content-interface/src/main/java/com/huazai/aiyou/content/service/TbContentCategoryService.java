package com.huazai.aiyou.content.service;

import java.util.List;

import com.huazai.aiyou.common.module.EasyUITreeNode;
import com.huazai.aiyou.common.response.AiyouResultData;

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
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getTbContentCategoryList
	 *        <ul>
	 * @description 获取 TbContentCategory 集合
	 *              </ul>
	 * @createdTime 2017年06月10日
	 * @param parentId
	 *            父级ID
	 * @return List<EasyUITreeNode>
	 *
	 * @version : V1.0.0
	 */
	public List<EasyUITreeNode> getTbContentCategoryList(Long parentId);

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title addTbContentCategory
	 *        <ul>
	 * @description 添加 TbContentCategory
	 *              </ul>
	 * @createdTime 2017年06月10日
	 * @param parentId
	 *            父级ID
	 * @param name
	 *            添加的节点名称
	 * @return AiyouResultData
	 *
	 * @version : V1.0.0
	 */
	public AiyouResultData addTbContentCategory(Long parentId, String name);

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title updateTbContentCategory
	 *        <ul>
	 * @description 实现内容分类的修改
	 *              </ul>
	 * @createdTime 2017年06月10日
	 * @param id
	 *            内容ID
	 * @param name
	 *            需要修改的内容名称
	 * @return AiyouResultData
	 *
	 * @version : V1.0.0
	 */
	public AiyouResultData updateTbContentCategory(Long id, String name);

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title deleteTbContentCategory
	 *        <ul>
	 * @description 删除TbContentCategory对象
	 *              </ul>
	 * @createdTime 2017年06月11日
	 * @param id
	 * @return AiyouResultData
	 *
	 * @version : V1.0.0
	 */
	public AiyouResultData deleteTbContentCategory(Long id);
}
