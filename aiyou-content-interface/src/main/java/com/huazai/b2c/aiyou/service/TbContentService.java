package com.huazai.b2c.aiyou.service;

import com.huazai.b2c.aiyou.common.EasyUIDataGrid;
import com.huazai.b2c.aiyou.pojo.TbContent;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description TbContent内容管理接口
 *              </ul>
 * @className TbContentService
 * @package com.huazai.b2c.aiyou.service
 * @createdTime 2017年06月11日
 *
 * @version V1.0.0
 */
public interface TbContentService
{

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getContentList
	 *        <ul>
	 * @description 查询TbContent分页信息
	 *              </ul>
	 * @createdTime 2017年06月11日
	 * @param pageNum 当前页
	 * @param pageSize 页大小
	 * @param tbContent 查询条件
	 * @return
	 * @return EasyUIDataGrid
	 *
	 * @version : V1.0.0
	 */
	public EasyUIDataGrid getTbContentList(Integer pageNum, Integer pageSize, TbContent tbContent);
}
