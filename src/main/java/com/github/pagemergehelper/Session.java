package com.github.pagemergehelper;

/**
 * 存储第二数据每页偏移量。在两个数据融合那页，显示的第二份数据的条数。
 * 
 * @author xinglj
 *
 */
public interface Session {
	public void setAttribute(String k, Object v);

	public Object getAttribute(String k);
}