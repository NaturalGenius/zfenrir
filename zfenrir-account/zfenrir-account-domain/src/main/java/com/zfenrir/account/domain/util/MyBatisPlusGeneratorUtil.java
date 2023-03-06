package com.zfenrir.account.domain.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.zfenrir.common.common.abstracts.ZfenrirBaseController;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus code auto generator util
 * 
 * @author zhuliang
 *
 *         2021-10-30
 */
public class MyBatisPlusGeneratorUtil {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/zfenrir";
    private static final String username = "root";
    private static final String password = "zhuliang";
    private static final String[] tables =
        {"user", "role", "system", "role_permission", "user_role", "permission", "system_operate_log"};

    public static void main(String[] args) {
        generator();
    }

    public static void generator() {
        FastAutoGenerator.create(url, username, password).globalConfig(builder -> {
            builder.author("zhuliang") // 设置作者
                // .enableSwagger() // 开启 swagger 模式
                .fileOverride()// 覆盖已生成文件
                .outputDir("D:\\project\\myproject\\zfenrir\\zfenrir-user\\zfenrir-user-domain\\src\\main\\java"); // 指定输出目录
            ;
        }).packageConfig(builder -> {
            Map<OutputFile, String> map = new HashMap<>();
            map.put(OutputFile.controller,
                "D:\\project\\myproject\\zfenrir\\zfenrir-user\\zfenrir-user-api\\src\\main\\java\\com\\zfenrir\\user\\api\\controller");
            map.put(OutputFile.mapperXml,
                "D:\\project\\myproject\\zfenrir\\zfenrir-user\\zfenrir-user-domain\\src\\main\\resources\\mapper\\auto");
            builder.parent("com.zfenrir.user.domain")// 设置父包名
                .entity("entity.auto").service("service").mapper("mapper.auto").pathInfo(map); // 设置mapperXml生成路径
        }).strategyConfig(builder -> {
            builder.addInclude(tables) // 设置需要生成的表名
                .entityBuilder().enableColumnConstant().convertFileName((name) -> name.concat("Entity"))
                .controllerBuilder().superClass(ZfenrirBaseController.class).enableRestStyle()
            // .addTablePrefix("t_", "c_") // 设置过滤表前缀
            ;
        })
            // .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }

}
