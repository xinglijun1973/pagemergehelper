package com.github.pagemergehelper;

import com.github.pagehelper.Page;

/**
 * 如何获取分页数据
 * 
 * @author xinglj
 *
 * @param <T> 数据实体
 */
public interface GetPaged<T> {
	/**
	 * 获取一页数据。同时带有total数据。
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<T> getPaged(int pageNum, int pageSize);

	/**
	 * 获取总条数
	 * 
	 * @return
	 */
	public long getTotal();
}