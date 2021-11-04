package com.zfenrir.common.common.entity;
/**
 * 反面前端的分页对象
 * @author zhuliang
 * @Date  2021-11-3
 *
 */

import java.util.List;

public class ZfenrirPage<T> {

    /**
     * 页码
     */
    private long pageNum;
    /**
     * 每页展示数量
     */
    private long pageSize;
    /**
     * 总数量
     */
    private long total;
    
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 数据集
     */
    private List<T> list;
    public long getPageNum() {
        return pageNum;
    }
    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }
    public long getPageSize() {
        return pageSize;
    }
    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public long getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
}
