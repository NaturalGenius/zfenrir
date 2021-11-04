package com.zfenrir.common.common.entity;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;

public class PageParameter {

    /**
     * 页码
     */
    private int pageNum = 1;
    /**
     * 每页展示数量
     */
    private int pageSize = 20;
    /**
     * 排序字段
     */
    private String sortField;
    
    /**
     * 是否升序
     */
    private boolean asc;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }
    
    public<T> Page<T> toPage(){
        Page<T> page = new Page<>(pageNum, pageSize);
        if (StringUtils.isNoneBlank(sortField)) {
            page.setOrders(Lists.newArrayList(new OrderItem(sortField, asc)));
        }
        return page;
    }
}
