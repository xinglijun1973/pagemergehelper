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

1.  You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2.  Gitee blog [blog.gitee.com](https://blog.gitee.com)
3.  Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4.  The most valuable open source project [GVP](https://gitee.com/gvp)
5.  The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6.  The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
