package cn.machine.geek.utils;

/**
 * @Author: MachineGeek
 * @Description: 随机生成工具类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/05
 */
public class RandomUtil {
    /**
     * @Author: MachineGeek
     * @Description: 生成随机长度的字符串
     * @Date: 2020/9/27 14:22
     * @param stringLength: 要生成的字符串长度
     * @return: java.lang.String 生成的字符串
     */
    public static String generateRandomString(int stringLength) {
        String string = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            int index = (int) Math.floor(Math.random() * string.length());//向下取整0-25
            sb.append(string.charAt(index));
        }
        return sb.toString();
    }
}