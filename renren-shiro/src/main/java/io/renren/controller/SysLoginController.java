package io.renren.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.nengkey.MailEntity;
import com.nengkey.MailSystemService;
import io.renren.entity.SysUserEntity;
import io.renren.service.SysUserService;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;
import io.renren.validator.ValidatorUtils;
import io.renren.validator.group.AddGroup;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录相关
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@Controller
public class SysLoginController {
	@Autowired
	private Producer producer;
    @Autowired
    private SysUserService sysUserService;

	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
	}
	
	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public R login(String username, String password, String captcha)throws IOException {
        Subject subject;
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return R.error("验证码不正确");
		}
		
		try{
			 subject = ShiroUtils.getSubject();
			//sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);

			subject.login(token);
		}catch (UnknownAccountException e) {
			return R.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return R.error(e.getMessage());
		}catch (LockedAccountException e) {
			return R.error(e.getMessage());
		}catch (AuthenticationException e) {
			return R.error("账户验证失败");
		}
        SysUserEntity user   = (SysUserEntity)ShiroUtils.getUserEntity();

        Long userId = user.getUserId();
        //系统管理员，拥有最高权限
        if(userId == 1){
            return R.ok("admin");
        }else{
            return R.ok("user");
        }
//		return R.ok();
//        return R.ok().put("userId", userId);
	}

    /**
     * 注册
     */
    @ResponseBody
    @RequestMapping(value = "/sys/register", method = RequestMethod.POST)
    public R register(String username, String password, String mobile ,String email)throws IOException{
        SysUserEntity user = new SysUserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setStatus(1);
         List<Long> roleIdList  = new ArrayList<Long>();
        Long  roleId    =  new Long(1);
        roleIdList.add(roleId);
        user.setRoleIdList(roleIdList);
        ValidatorUtils.validateEntity(user, AddGroup.class);
         user.setCreateUserId(roleId);
        sysUserService.save(user) ;
//         R  r;
//        if(sendMail(user)  == true ){
//            r = R.ok("注册成功!") ;
//
//        }else{
//        r  = R.ok("注册失败!") ;
//        }
//        return r;
         return    sendMail(user);
    }

    private R sendMail(SysUserEntity user){

        MailEntity mail = new MailEntity();
        mail.setHost("smtp.exmail.qq.com");
        mail.setSender("heyg@wxgyxy.cn");
        mail.setUsername("何英高");
//        mail.setPassword("220163");

        mail.setReceiver(user.getEmail());

        mail.setSubject("注册成功");
        mail.setMessage("恭喜" + user.getUsername()+"报名成功！");

        R  r = MailSystemService.Sendmail(mail);
        return   r;
    }
	/**
	 * 退出
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.logout();
		return "redirect:login.html";
	}
	
}
