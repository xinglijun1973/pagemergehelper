# pagemerghelper

#### Description
now there's 2 api data which both with paged data, we need to merge them together, the first data will be showed page by page, and then the second

#### Software Architecture
Software architecture description

#### Installation

1.  xxxx
2.  xxxx
3.  xxxx

#### Instructions
see com.github.pagemergehelper.DemoPageMerge.java.
		
		int pageNum = 1;
		// query the 1st data in xx page
		current = pager.getPaged(pageNum, pagesize);
		// query the 2nd data in xx page and merge into current
		PageUtil.endPage(demo.ses, pageNum, current, pager2);
		// current is the mixed result
		System.out.println(current);
1.  xxxx
2.  xxxx
3.  xxxx

#### Contribution

1.  Fork the repository
2.  Create Feat_xxx branch
3.  Commit your code
4.  Create Pull Request


#### Gitee Feature


