package com.github.pagemergehelper;

/**
 * �洢�ڶ�����ÿҳƫ�����������������ں���ҳ����ʾ�ĵڶ������ݵ�������
 * 
 * @author xinglj
 *
 */
public interface Session {
	public void setAttribute(String k, Object v);

	public Object getAttribute(String k);
}