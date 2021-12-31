package com.github.pagemergehelper;

import com.github.pagehelper.Page;

/**
 * ��λ�ȡ��ҳ����
 * 
 * @author xinglj
 *
 * @param <T> ����ʵ��
 */
public interface GetPaged<T> {
	/**
	 * ��ȡһҳ���ݡ�ͬʱ����total���ݡ�
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<T> getPaged(int pageNum, int pageSize);

	/**
	 * ��ȡ������
	 * 
	 * @return
	 */
	public long getTotal();
}