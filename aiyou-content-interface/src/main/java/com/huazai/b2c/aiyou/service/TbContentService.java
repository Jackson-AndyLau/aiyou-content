package com.huazai.b2c.aiyou.service;

import com.huazai.b2c.aiyou.common.EasyUIDataGrid;
import com.huazai.b2c.aiyou.pojo.TbContent;
import com.huazai.b2c.aiyou.repo.AiyouResultData;

/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description TbContent内容管理接口
 *              </ul>
 * @className TbContentService
 * @package com.huazai.b2c.aiyou.service
 * @createdTime 2017年06月12日
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
	 * @createdTime 2017年06月12日
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param tbContent
	 *            查询条件
	 * @return EasyUIDataGrid 数据载体
	 *
	 * @version : V1.0.0
	 */
	public EasyUIDataGrid getTbContentList(Integer pageNum, Integer pageSize, TbContent tbContent);

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title saveTbContent
	 *        <ul>
	 * @description 新增网站内容
	 *              </ul>
	 * @createdTime 2017年06月12日
	 * @param tbContent
	 *            内容实体
	 * @return AiyouResultData 数据载体
	 *
	 * @version : V1.0.0
	 */
	public AiyouResultData addTbContent(TbContent tbContent);

}
