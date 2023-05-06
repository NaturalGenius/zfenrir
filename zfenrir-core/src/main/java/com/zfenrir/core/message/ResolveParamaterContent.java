package com.zfenrir.core.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析参数核心内
 * 
 * @author zhuliang
 * @Date 2023-3-14
 *
 */
@Service
public class ResolveParamaterContent {


    @Autowired
    private List<ParamaterResolve> paramaterResolves;

    public Map<String, String> resolveParamaters(List<String> params) {
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            ParamaterResolve paramaterResolve =
                paramaterResolves.stream().filter(p -> p.support(param)).findFirst().orElseThrow(RuntimeException::new);
            map.put(param, paramaterResolve.resolveParamater(param));
        }
        return map;
    }
}
