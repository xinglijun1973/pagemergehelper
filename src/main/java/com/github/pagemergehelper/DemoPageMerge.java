package com.github.pagemergehelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

public class DemoPageMerge {
	
	//web项目里可以使用httpsession，这里仅仅demo。demo only, httpsession may be used in web project
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
	 * 使用list的分页器.真实环境下一般是pagehelper。a demo pager.
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
		DemoPageMerge demo = new DemoPageMerge();
		List<String> list = new ArrayList<String>();
		for(int i=0;i<19;i++)
			list.add("a"+(i+1));
		ListPager pager =new ListPager(list);
		
		List<String> list2 = new ArrayList<String>();
		for(int i=0;i<19;i++)
			list2.add("b"+(i+1));
		ListPager pager2 =new ListPager(list2);
 
		int pagesize=10;
		Page<String> current;
		demo = new DemoPageMerge();
		//第一页
		int pageNum = 1;
		//获取某页中的第一份数据 query the 1st data in xx page
		current = pager.getPaged(pageNum, pagesize);
		//获取某页中的第二份数据，合并到current中 query the 2nd data in xx page and merge into current
		PageUtil.endPage(demo.ses, pageNum, current, pager2);
		//current 为某页的全部数据  current is the mixed result
		System.out.println(current);

		//the page with mixed  data
		pageNum = 2;
		current = pager.getPaged(pageNum, pagesize);
		PageUtil.endPage(demo.ses, pageNum, current, pager2);
		System.out.println(current);

		//末页
		pageNum = 4;
		current = pager.getPaged(pageNum, pagesize);
		PageUtil.endPage(demo.ses, pageNum, current, pager2);
		System.out.println(current);
		 
		
	}

}
