package com.zfenrir.util.comparable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.zfenrir.util.comparable.ComparableResult.ComparableFiledDetail;

public class BeanFieldComparableUtilTest {

    @Test
    void compareTest1() {
        ComparableFiledDetail source = new ComparableFiledDetail("test01", 1, 1);
        ComparableFiledDetail target = new ComparableFiledDetail("test01", 1, 1);
        ComparableResult compare = BeanFieldComparableUtil.compare(source, target);
        assertTrue(compare.isEquals());
    }
    
    @Test
    void compareTest2() {
        ComparableFiledDetail source = new ComparableFiledDetail("test01", 1, 1);
        ComparableFiledDetail target = new ComparableFiledDetail("test02", 2, 1);
        ComparableResult compare = BeanFieldComparableUtil.compare(source, target);
        System.out.println(compare.toString());
        assertFalse(compare.isEquals());
    }
}
