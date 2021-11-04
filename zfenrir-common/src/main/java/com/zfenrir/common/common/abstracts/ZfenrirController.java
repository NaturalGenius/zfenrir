package com.zfenrir.common.common.abstracts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zfenrir.common.common.entity.ZfenrirPage;
import com.zfenrir.util.convert.ZfenrirConvertUtil;

public abstract class ZfenrirController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 分页的统一处理
     * 
     * @param <T>
     * @param <E>
     * @param page
     * @param clazz
     * @return
     */
    protected <T, E> ZfenrirPage<T> resultPage(IPage<E> page, Class<T> clazz) {
        ZfenrirPage<T> zfenrirPage = new ZfenrirPage<>();
        zfenrirPage.setList(ZfenrirConvertUtil.convertList(page.getRecords(), clazz));
        zfenrirPage.setPageNum(page.getCurrent());
        zfenrirPage.setPageSize(page.getSize());
        zfenrirPage.setTotal(page.getTotal());
        zfenrirPage.setTotalPage(page.getPages());
        return zfenrirPage;
    }
}
