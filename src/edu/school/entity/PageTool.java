package edu.school.entity;

public class PageTool {

    private int totalCount;//总数据量
    private int currentPage;//当前页面
    private int pageCount;//总页数
    private int lastPage;//上一页页数
    private int nextPage;//下一页页数
    private int pageSize;//每一页数据量
    private int startIndex;//起始下标

    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getLastPage() {
        return lastPage;
    }
    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
    public int getNextPage() {
        return nextPage;
    }
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    public PageTool() {
    }
    /*
         为什么只传递总数据量及当前页码呢？
             每页数据量给定为死值，不需要传
             我们只需要再知道当前页码及总数据量，其他的值都可以推算出来！
             所以只传递当前页码及总数据量
     */
    public PageTool(int totalCount, String currentPage) {
        super();
        this.totalCount = totalCount;
        //将每页数据量给定为死值
        pageSize = 5;
        initialCurrentPage(currentPage);
        initialPageCount();
        initialLastPage();
        initialNextPage();
        initialStartIndex();
    }

    //初始化当前页码
    private void initialStartIndex() {
        startIndex = (currentPage - 1) * pageSize;
    }

    //初始化下一页页码
    private void initialNextPage() {
        if (currentPage == pageCount) {
            nextPage = pageCount;
        } else {
            nextPage = currentPage + 1;
        }
    }

    //初始化上一页页码
    private void initialLastPage() {
        if (currentPage == 1) {
            lastPage = 1;
        } else {
            lastPage = currentPage - 1;
        }
    }

    //初始化总页数的方法
    private void initialPageCount() {
        pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
    }

    //初始化当前页码的方法
    private void initialCurrentPage(String currentPage) {
		/*
		 	从页面获取到的当前页码有两种情况：
		 		1、当前页码为null，代表第一次进行分页展示页面，那么当前页码就是第一页
		 		2、当前页码不为null，就代表点击了首页、尾页、上一页、下一页的按钮，传递的值
		 */
        if (currentPage == null) {
            this.currentPage = 1;
        } else {
            this.currentPage = Integer.valueOf(currentPage);
        }
    }

    @Override
    public String toString() {
        return "PageTool [totalCount=" + totalCount + ", currentPage=" + currentPage + ", pageCount=" + pageCount
                + ", lastPage=" + lastPage + ", nextPage=" + nextPage + ", pageSize=" + pageSize + ", startIndex="
                + startIndex + "]";
    }

}
