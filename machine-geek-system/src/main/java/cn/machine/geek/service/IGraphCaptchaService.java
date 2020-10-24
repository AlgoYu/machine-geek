package cn.machine.geek.service;

/**
 * @Author: MachineGeek
 * @Description: 图形验证码服务
 * @Date: 2020/10/22
 */
public interface IGraphCaptchaService {
    String createCaptcha(String key);
    boolean verifyCaptcha(String key,String value);
}