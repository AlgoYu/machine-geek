package cn.machine.geek.service.impl;

import cn.machine.geek.service.IGraphCaptchaService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MachineGeek
 * @Description: 图形验证码实现类
 * @Date: 2020/10/22
 */
@Service
public class GraphCaptchaServiceImpl implements IGraphCaptchaService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private final String CAPTCHA_KEY = "CAPTCHA_TOKEN_";
    private final int CAPTCHA_EXPIRE = 3;
    /** @Author: MachineGeek
    * @Description: 创建Base64验证码
    * @Date: 2020/10/22
    * @param key
    * @Return java.lang.String
    */
    @Override
    public String createCaptcha(String key) {
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        // 存入Redis
        redisTemplate.opsForValue().set(this.CAPTCHA_KEY + key,specCaptcha.text().toLowerCase(),CAPTCHA_EXPIRE, TimeUnit.MINUTES);
        // 输出为Base64
        return specCaptcha.toBase64();
    }
    /** @Author: MachineGeek
    * @Description: 校验验证码
    * @Date: 2020/10/22
     * @param key
     * @param value
    * @Return boolean
    */
    @Override
    public boolean verifyCaptcha(String key, String value) {
        String captcha = (String) redisTemplate.opsForValue().get(this.CAPTCHA_KEY + key);
        if(!StringUtil.isNullOrEmpty(captcha) && captcha.equals(value)){
            return true;
        }
        return false;
    }
}
