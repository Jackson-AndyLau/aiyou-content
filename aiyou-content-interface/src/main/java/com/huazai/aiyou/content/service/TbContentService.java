package com.huazai.aiyou.content.service;

import java.util.List;

import com.huazai.aiyou.common.module.EasyUIDataGrid;
import com.huazai.aiyou.common.response.AiyouResultData;
import com.huazai.aiyou.content.pojo.TbContent;





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

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title deleteTbContent
	 *        <ul>
	 * @description 根据内容ID删除网站内容
	 *              </ul>
	 * @createdTime 2017年06月12日
	 * @param ids
	 *            内容ID集合
	 * @return AiyouResultData 数据载体
	 *
	 * @version : V1.0.0
	 */
	public AiyouResultData deleteTbContent(String ids);

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title updateTbContent
	 *        <ul>
	 * @description 修改网站内容
	 *              </ul>
	 * @createdTime 2017年06月12日
	 * @param tbContent
	 *            网站内容实体
	 * @return AiyouResultData 数据载体
	 *
	 * @version : V1.0.0
	 */
	public AiyouResultData updateTbContent(TbContent tbContent);

	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title geTbContentListByCid
	 *        <ul>
	 * @description 根据分类ID获取网站内容
	 *              </ul>
	 * @createdTime 2017年06月13日
	 * @param cid
	 * @return
	 * @return List<TbContent>
	 *
	 * @version : V1.0.0
	 */
	public List<TbContent> geTbContentListByCid(Long cid);

}
