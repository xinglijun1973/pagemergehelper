# pagemerghelper

#### 介绍
假定2个api都可以返回分页数据，现在要合并2个ap数据。先分页显示第一个api数据， 完毕后再第二个。
now there's 2 api data which both with paged data, we need to merge them together, the first data will be showed page by page, and then the second

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

参考 com.github.pagemergehelper.DemoPageMerge
		//第一页
		int pageNum = 1;
		//获取某页中的第一份数据 query the 1st data in xx page
		current = pager.getPaged(pageNum, pagesize);
		//获取某页中的第二份数据，合并到current中 query the 2nd data in xx page and merge into current
		PageUtil.endPage(demo.ses, pageNum, current, pager2);
		//current 为某页的全部数据  current is the mixed result
		System.out.println(current);

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
