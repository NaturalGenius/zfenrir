package com.zfenrir.common.common.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zfenrir.common.common.entity.BasePageForm;
import com.zfenrir.common.common.entity.ZfenrirPage;

/**
 * Zfenrir 所有控制器的父类
 * 
 * @author zhuliang
 *
 *         2021-10-30
 */
public abstract class ZfenrirBaseController<T, E extends IService<T>> extends ZfenrirController{

    @Autowired
    protected E baseService;
    
    @GetMapping("/page")
    public ZfenrirPage<T> listPage(BasePageForm<T> form) {
        Page<T> page = baseService.page(form.toPage(), Wrappers.query(form.getForm()));
        ZfenrirPage<T> zfenrirPage = new ZfenrirPage<>();
        zfenrirPage.setList(page.getRecords());
        zfenrirPage.setPageNum(page.getCurrent());
        zfenrirPage.setPageSize(page.getSize());
        zfenrirPage.setTotal(page.getTotal());
        zfenrirPage.setTotalPage(page.getPages());
        return zfenrirPage;
    }
    
}
