package cn.machine.geek.service.impl;

import cn.machine.geek.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author: MachineGeek
 * @Description: 邮箱服务实现类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/13
 */
@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
    * @Author: MachineGeek
    * @Description: 发送邮件
    * @Date: 2020/11/13
     * @param from
     * @param subject
     * @param content
     * @param to
    * @Return: void
    */
    @Override
    public void sendEmail(String from,String subject,String content,String... to) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setTo(to);
        javaMailSender.send(simpleMailMessage);
    }
}
