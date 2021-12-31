package com.github.pagemergehelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

public class TestPageMerge {
	
	
	Session ses = new Session() {
		Map map = new HashMap();
		public void setAttribute(String k, Object v) {
			map.put(k, v);
		}
		public Object getAttribute(String k) {
			return map.get(k);
		}
	};
	
	/**
	 * Ê¹ÓÃlistµÄ·ÖÒ³Æ÷
	 * @author xinglj
	 *
	 */
	static class ListPager implements GetPaged<String>{
		List<String> list ;
		private ListPager(List<String> list) {
			super();
			this.list = list;
		}
		
		public Page<String> getPaged(int pageNum, int pageSize) {
			int to = pageNum*pageSize;
			int size = list.size();
			if(to>size)
				to = size;
			int from = pageSize*(pageNum-1);
			List<String> subList ;
			if(from>size-1)
				subList = new ArrayList<String>();
			else
				subList = list.subList(from,  to);
			Page<String> page = new Page<String>(pageNum, pageSize);
			page.addAll(subList);
			page.setTotal(size);
			return  page;
		}
		public long getTotal() {
			return list.size();
		}
	}
	
	public static void main(String[] args) {
		TestPageMerge demo = new TestPageMerge();
		List<String> list = new ArrayList<String>();
		for(int i=0;i<19;i++)
			list.add("a"+(i+1));
		ListPager pager =new ListPager(list);
		
		List<String> list2 = new ArrayList<String>();
		for(int i=0;i<19;i++)
			list2.add("b"+(i+1));
		ListPager pager2 =new ListPager(list2);

		//²âÊÔ 10/Ò³£¬¹²4Ò³
		//Ë³Ðò·­Ò³
//[a1, a2, a3, a4, a5, a6, a7, a8, a9, a10]
//[a11, a12, a13, a14, a15, a16, a17, a18, a19, b1]
//[b2, b3, b4, b5, b6, b7, b8, b9, b10, b11]
//[b12, b13, b14, b15, b16, b17, b18, b19]		
		int pagesize=10;
		
		Page<String> current;
		for(int i=0;i<4;i++) {
			int pageNum = i+1;
			 current = gotopage(demo, pager, pager2, pagesize, pageNum);
			if(pageNum==2)
				asserts(  current.subList(0, current.size()).toString().equals("[a11, a12, a13, a14, a15, a16, a17, a18, a19, b1]"));
			if(pageNum==4)
				asserts( current.subList(0, current.size()).toString().equals("[b12, b13, b14, b15, b16, b17, b18, b19]"));
		}

		//Ö±½Ó·­Ò³
		demo = new TestPageMerge();
		//µÚÒ»Ò³
		int pageNum = 1;
		current = gotopage(demo, pager, pager2, pagesize, pageNum);
		//Ä©Ò³
		  pageNum = 4;
		 current = gotopage(demo, pager, pager2, pagesize, pageNum);
		  asserts( current.subList(0, current.size()).toString().equals("[b12, b13, b14, b15, b16, b17, b18, b19]"));

		
	}
	static void asserts(boolean b) {
		if(!b)
			throw new RuntimeException("assert error");
	}
	private static Page<String> gotopage(TestPageMerge demo, ListPager pager, ListPager pager2, int pagesize, int pageNum) {
		Page<String> current = pager.getPaged(pageNum, pagesize);
		PageUtil.endPage(demo.ses, pageNum, current, pager2);
		System.out.println(current);
		return current;
	}
}
