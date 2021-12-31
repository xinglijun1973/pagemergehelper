package com.github.pagemergehelper;

import java.util.List;

import com.github.pagehelper.Page;


/**
 * 假定每个api都可以返回分页数据，现在要合并2个api数据，一起显示。要求先分页显示第一个api数据，显示完毕后再分页显示第二个api数据。总页数和翻页要功能正常。<br>
 * assume there's 2 api/webservice/restful/ data which both can be accessed with paged data, now we need to merge them tother, the first data will be showed page by page, and then the second data page by page, and the merged data's  "total" and “pages" must be correct.  
 * @author xinglj
 *
 */
public class PageUtil {

	private static final String KEY_OFF_ROW = "offRow";

	/**
	 * 合并两份数据以及分页信息到current中。<br>
	 * merge the second data into the first data if necessary, because pagehelper has the "startPage", so name it "endPage"。
	 * 
	 * @param <T>
	 * @param session
	 * @param pageNumTotal 页面传递的总页号
	 * @param first      当前分页数据
	 * @param nextGetter   下份分页数据getter
	 * TODO: 每次都要获取总数，效率问题；
	 */
	public static <T> void endPage(Session session, int pageNumTotal, Page<T> first, GetPaged<T> nextGetter) {

		// 获取下份分页数据
		Page<T> next = new Page();
		int pageNumInNext = pageNumTotal - first.getPages();//第二份数据页号
		if (pageNumInNext < 0) {
			// 全部来自第一份数据 all from the first
			next.setTotal(nextGetter.getTotal());
		} else if (pageNumInNext == 0) {
			// 第一份末页 the last page in the first data
			Integer offRow  = first.getPageSize() - first.size(); // 第一份离满页差
			if (offRow == 0) {
				// 全部为第一份数据 all from the frist data
				next.setTotal(nextGetter.getTotal());
			} else {
				// 混合了第二份数据 mix with the second data
				next = nextGetter.getPaged(1, offRow);
			}
		} else if (pageNumInNext > 0) {
			// 全部来自第二份数据 all from the second data
			first.clear();
			Integer offRow = (Integer) session.getAttribute(KEY_OFF_ROW);
			// 查询2页，去掉第一页头部一些，再加上第二页头部一些 query 2 pages, remove offRow from the 1st page, and add offRow from the 2nd page
			next.close();
			next = nextGetter.getPaged(pageNumInNext, first.getPageSize());
			if(offRow==null) {
				//没有经过第一份末页 haven't visit the 1st data's last page, we need to resolve offRow
				offRow  = (int) (first.getPageSize() - first.getTotal() % first.getPageSize()); // 第一份末页离满页差
				session.setAttribute(KEY_OFF_ROW, offRow);

			}
			if ( offRow > 0) {
				for (int i = 0; i < offRow; i++)
					next.remove(0);
				List<T> page2 = nextGetter.getPaged(pageNumInNext + 1, first.getPageSize());
				for (int i = 0; i < offRow && i < page2.size(); i++)
					next.add(page2.get(i));
			}
		}

		
		// 重新计算总页数、行数 re-compute total/pages
		first.addAll(next);
		long total = first.getTotal() + next.getTotal();
		//System.out.println("current and next total :" + current.getTotal() + " " + next.getTotal());
		long pages = total % first.getPageSize() == 0 ? total / first.getPageSize()
				: total / first.getPageSize() + 1;
		first.setTotal(total);
		first.setPages((int) pages);
		next.close();
		
		
	}

}
