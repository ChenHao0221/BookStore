package com.chen.bean;

import java.util.List;

public class Page<T> {

    private int pageNo;

    private int totalPage;

    private int totalCount;

    private int pageSize = 4;

    private int index;

    private boolean hasNext;

    private boolean hasPrev;

    private List<T> pageData;

    private String url;

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", index=" + index +
                ", hasNext=" + hasNext +
                ", hasPrev=" + hasPrev +
                ", pageData=" + pageData +
                ", url='" + url + '\'' +
                '}';
    }

    public Page(int pageNo, int totalPage, int totalCount, int pageSize, int index, boolean hasNext, boolean hasPrev, List<T> pageData, String url) {
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.index = index;
        this.hasNext = hasNext;
        this.hasPrev = hasPrev;
        this.pageData = pageData;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public Page(int pageNo, int totalPage, int totalCount, int pageSize, int index, boolean hasNext, boolean hasPrev, List<T> pageData) {
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.index = index;
        this.hasNext = hasNext;
        this.hasPrev = hasPrev;
        this.pageData = pageData;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        //要获取到真正的TotalPage，必须要有TotalCount和PageSize
        //所有应该在service中先设置好这两个值
        pageNo = pageNo > getTotalPage() ? getTotalPage(): pageNo;
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        int t = getTotalCount() / getPageSize();
        if (!(getTotalCount() % getPageSize() == 0)){
            t = t + 1;
        }
        return t;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIndex() {
        int i = ((getPageNo() - 1) * getPageSize());
        if (i < 0) i = 0;
        return i;
    }

    public boolean isHasNext() {
        return getPageNo() < getTotalPage();
    }

    public boolean isHasPrev() {
        return getPageNo() > 1;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

}
