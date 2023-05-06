package com.zfenrir.core.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 模板消息占位符解析器
 * 
 * @author zhuliang
 * @Date 2023-3-14
 *
 */
public class PlaceholderResolver {
    /**
     * 默认前缀占位符
     */
    private static final String DEFAULT_PLACEHOLDER_PREFIX = "{";

    /**
     * 默认后缀占位符
     */
    private static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    /**
     * 默认单例解析器
     */
    private static PlaceholderResolver defaultResolver = new PlaceholderResolver();

    /**
     * 占位符前缀
     */
    private String placeholderPrefix = DEFAULT_PLACEHOLDER_PREFIX;

    /**
     * 占位符后缀
     */
    private String placeholderSuffix = DEFAULT_PLACEHOLDER_SUFFIX;

    private PlaceholderResolver() {}

    private PlaceholderResolver(String placeholderPrefix, String placeholderSuffix) {
        this.placeholderPrefix = placeholderPrefix;
        this.placeholderSuffix = placeholderSuffix;
    }

    /**
     * 获取默认的占位符解析器，即占位符前缀为"${", 后缀为"}"
     *
     * @return
     */
    public static PlaceholderResolver getDefaultResolver() {
        return defaultResolver;
    }

    public static PlaceholderResolver getResolver(String placeholderPrefix, String placeholderSuffix) {
        return new PlaceholderResolver(placeholderPrefix, placeholderSuffix);
    }

    /**
     * 解析文本中的占位符符
     * 
     * @param content
     * @param values
     * @return
     */
    public List<String> resolvepLaceholders(String content) {
        List<String> laceholders = new ArrayList<>();
        int start = content.indexOf(this.placeholderPrefix);
        if (start == -1) {
            return laceholders;
        }
        // 值索引
        while (start != -1) {
            int end = content.indexOf(this.placeholderSuffix, start);
            if (end == -1) {
                break;
            }
            int errorStart = content.indexOf(this.placeholderPrefix, start + 1);
            if (errorStart > -1 && errorStart < end) {
                start = errorStart;
                continue;
            }
            laceholders.add(content.substring(start, end + 1));
            start = content.indexOf(this.placeholderPrefix, end);
        }
        return laceholders;
    }

    /**
     * 解析带有指定占位符的模板字符串，默认占位符为前缀：${ 后缀：}<br/>
     * <br/>
     * 如：template = category:${}:product:${}<br/>
     * values = {"1", "2"}<br/>
     * 返回 category:1:product:2<br/>
     *
     * @param content 要解析的带有占位符的模板字符串
     * @param values 按照模板占位符索引位置设置对应的值
     * @return
     */
    public String resolve(String content, String[] values) {
        int start = content.indexOf(this.placeholderPrefix);
        if (start == -1) {
            return content;
        }
        // 值索引
        int valueIndex = 0;
        StringBuilder result = new StringBuilder(content);
        while (start != -1) {
            int end = result.indexOf(this.placeholderSuffix, start);
            if (end == -1) {
                break;
            }
            int errorStart = result.indexOf(this.placeholderPrefix, start + 1);
            if (errorStart > -1 && errorStart < end) {
                start = errorStart;
                continue;
            }
            String replaceContent = values[valueIndex++];
            result.replace(start, end + this.placeholderSuffix.length(), replaceContent);
            start = result.indexOf(this.placeholderPrefix, start + replaceContent.length());
        }

        return result.toString();
    }

    /**
     * 解析带有指定占位符的模板字符串，默认占位符为前缀：${ 后缀：}<br/>
     * <br/>
     * 如：template = category:${}:product:${}<br/>
     * values = {"1", "2"}<br/>
     * 返回 category:1:product:2<br/>
     *
     * @param content 要解析的带有占位符的模板字符串
     * @param values 按照模板占位符索引位置设置对应的值
     * @return
     */
    public String resolve(String content, Object... values) {
        return resolve(content, Stream.of(values).map(String::valueOf).toArray(String[]::new));
    }

    /**
     * 根据替换规则来替换指定模板中的占位符值
     *
     * @param content 要解析的字符串
     * @param rule 解析规则回调
     * @return
     */
    public String resolveByRule(String content, Function<String, String> rule) {
        int start = content.indexOf(this.placeholderPrefix);
        if (start == -1) {
            return content;
        }
        StringBuilder result = new StringBuilder(content);
        // 值索引
        while (start != -1) {
            int end = result.indexOf(this.placeholderSuffix, start);
            if (end == -1) {
                break;
            }
            int errorStart = result.indexOf(this.placeholderPrefix, start + 1);
            if (errorStart > -1 && errorStart < end) {
                start = errorStart;
                continue;
            }
            // 获取占位符属性值，如${id}, 即获取id
            String placeholder = result.substring(start + this.placeholderPrefix.length(), end);
            // 替换整个占位符内容，即将${id}值替换为替换规则回调中的内容
            String replaceContent = placeholder.trim().isEmpty() ? "" : rule.apply(placeholder);
            result.replace(start, end + this.placeholderSuffix.length(), replaceContent);
            start = result.indexOf(this.placeholderPrefix, start + replaceContent.length());
        }
        return result.toString();
    }

    /**
     * 替换模板中占位符内容，占位符的内容即为map key对应的值，key为占位符中的内容。<br/>
     * <br/>
     * 如：content = product:${id}:detail:${did}<br/>
     * valueMap = id -> 1; pid -> 2<br/>
     * 经过解析返回 product:1:detail:2<br/>
     *
     * @param content 模板内容。
     * @param valueMap 值映射
     * @return 替换完成后的字符串。
     */
    public String resolveByMap(String content, final Map<String, String> valueMap) {
        return resolveByRule(content, placeholderValue -> valueMap.get(placeholderValue));
    }

    /**
     * 根据properties文件替换占位符内容
     *
     * @param content
     * @param properties
     * @return
     */
    public String resolveByProperties(String content, final Properties properties) {
        return resolveByRule(content, properties::getProperty);
    }

    /**
     * 将下划线形式转成大小写的形式
     * 
     * @param text
     * @return
     */
    public static String underlineToCamelCase(String text) {
        String[] words = text.split("[\\W_]+");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                word = word.isEmpty() ? word : word.toLowerCase();
            } else {
                word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            }
            builder.append(word);
        }
        return builder.toString();
    }

    /**
     * 将 驼峰形式的字符串 转成 全小写/大写以下划线分割形式的字符串
     * 
     * @param text
     * @param defaultConfig true,全转成大写; 默认小写
     * @return
     */
    public static String camelCaseToUnderline(String text, Boolean... defaultConfig) {
        Matcher matcher = Pattern.compile("[A-Z]").matcher(text);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group().toLowerCase());
        }
        matcher.appendTail(sb);
        if (sb.toString().startsWith("_")) {
            sb.delete(0, 1);
        }
        if (defaultConfig.length > 0 && defaultConfig[0]) {
            return sb.toString().toUpperCase();
        }
        return sb.toString();
    }

    public static String parseTemplate(String template, Map<String, String> params) {
        Pattern pattern = Pattern.compile("\\{(.*?!{)\\}");
        Matcher matcher = pattern.matcher(template);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = params.getOrDefault(key, "");
            matcher.appendReplacement(result, value);
        }
        matcher.appendTail(result);

        return result.toString();
    }

    static Pattern pattern = Pattern.compile("\\{(.*?)()\\}");
    public static List<String> parseTemplate(String template) {
        Matcher matcher = pattern.matcher(template);
        // Pattern pattern = Pattern.compile("\\{(.*?)(!\\{)\\}");
        List<String> aList = new ArrayList<>();
        while (matcher.find()) {
            aList.add(matcher.group());
        }
        return aList;
    }
    public static void main(String[] args) {
        String str = "用户id:${userId}, 用户名称：${userName}, aaaa";
        long start = System.currentTimeMillis();
        // for (int i = 0; i < 1000000; i++) {
        // defaultResolver.resolvepLaceholders(str);
        // }
        // long end1 = System.currentTimeMillis();
        // System.out.println(end1 - start);
        // for (int i = 0; i < 1000000; i++) {
        // parseTemplate(str);
        // }
        long end2 = System.currentTimeMillis();
        // System.out.println(end2 - end1);
        str = "{用户id:${userId}, 用户名称：${userName}}";
        System.out.println(parseTemplate(str));
        System.out.println(defaultResolver.resolvepLaceholders(str));
        str = "用户id:${userId, 用户名称：${userName}";
        System.out.println(defaultResolver.resolvepLaceholders(str));
        System.out.println(parseTemplate(str));
        str = "用户id:userId}{, 用户名称：${userName}";
        System.out.println(defaultResolver.resolvepLaceholders(str));
        str = "用户id:userId}{, 用户名称：${userName}{{";
        System.out.println(defaultResolver.resolvepLaceholders(str));
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName", "测试");
        System.out.println(defaultResolver.resolveByMap(str, hashMap));
        System.out.println(defaultResolver.resolve(str, "测试"));
        str = "用户id";
        System.out.println(defaultResolver.resolvepLaceholders(str));
        str = "}";
        System.out.println(str.lastIndexOf('}'));
    }

}
