package com.github.pagemergehelper;

import java.util.List;

import com.github.pagehelper.Page;


/**
 * �ٶ�ÿ��api�����Է��ط�ҳ���ݣ�����Ҫ�ϲ�2��api���ݣ�һ����ʾ��Ҫ���ȷ�ҳ��ʾ��һ��api���ݣ���ʾ��Ϻ��ٷ�ҳ��ʾ�ڶ���api���ݡ���ҳ���ͷ�ҳҪ����������<br>
 * assume there's 2 api/webservice/restful/ data which both can be accessed with paged data, now we need to merge them tother, the first data will be showed page by page, and then the second data page by page, and the merged data's  "total" and ��pages" must be correct.  
 * @author xinglj
 *
 */
public class PageUtil {

	private static final String KEY_OFF_ROW = "offRow";

	/**
	 * �ϲ����������Լ���ҳ��Ϣ��current�С�<br>
	 * merge the second data into the first data if necessary, because pagehelper has the "startPage", so name it "endPage"��
	 * 
	 * @param <T>
	 * @param session
	 * @param pageNumTotal ҳ�洫�ݵ���ҳ��
	 * @param first      ��ǰ��ҳ����
	 * @param nextGetter   �·ݷ�ҳ����getter
	 * TODO: ÿ�ζ�Ҫ��ȡ������Ч�����⣻
	 */
	public static <T> void endPage(Session session, int pageNumTotal, Page<T> first, GetPaged<T> nextGetter) {

		// ��ȡ�·ݷ�ҳ����
		Page<T> next = new Page();
		int pageNumInNext = pageNumTotal - first.getPages();//�ڶ�������ҳ��
		if (pageNumInNext < 0) {
			// ȫ�����Ե�һ������ all from the first
			next.setTotal(nextGetter.getTotal());
		} else if (pageNumInNext == 0) {
			// ��һ��ĩҳ the last page in the first data
			Integer offRow  = first.getPageSize() - first.size(); // ��һ������ҳ��
			if (offRow == 0) {
				// ȫ��Ϊ��һ������ all from the frist data
				next.setTotal(nextGetter.getTotal());
			} else {
				// ����˵ڶ������� mix with the second data
				next = nextGetter.getPaged(1, offRow);
			}
		} else if (pageNumInNext > 0) {
			// ȫ�����Եڶ������� all from the second data
			first.clear();
			Integer offRow = (Integer) session.getAttribute(KEY_OFF_ROW);
			// ��ѯ2ҳ��ȥ����һҳͷ��һЩ���ټ��ϵڶ�ҳͷ��һЩ query 2 pages, remove offRow from the 1st page, and add offRow from the 2nd page
			next.close();
			next = nextGetter.getPaged(pageNumInNext, first.getPageSize());
			if(offRow==null) {
				//û�о�����һ��ĩҳ haven't visit the 1st data's last page, we need to resolve offRow
				offRow  = (int) (first.getPageSize() - first.getTotal() % first.getPageSize()); // ��һ��ĩҳ����ҳ��
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

		
		// ���¼�����ҳ�������� re-compute total/pages
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
