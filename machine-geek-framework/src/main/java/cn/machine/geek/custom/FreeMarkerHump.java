package cn.machine.geek.custom;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: MachineGeek
 * @Description: FreeMarker驼峰函数
 * @Email: 794763733@qq.com
 * @Date: 2020/11/9
 */
public class FreeMarkerHump implements TemplateMethodModelEx {
    // 正则表达式
    private final Pattern linePattern = Pattern.compile("_(\\w)");

    @Override
    public Object exec(List list) throws TemplateModelException {
        String str = list.get(0).toString();
        return this.lineToHump(str);
    }

    /**
    * @Author: MachineGeek
    * @Description: 下划线转驼峰
    * @Date: 5:42 下午
     * @param str
    * @Return: java.lang.String
    */
    private String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}