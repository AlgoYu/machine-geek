package cn.machine.geek.service;

/**
 * @Author: MachineGeek
 * @Description: 邮箱服务类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/13
 */
public interface IEmailService {
    void sendEmail(String from,String subject,String content,String... to);
}
