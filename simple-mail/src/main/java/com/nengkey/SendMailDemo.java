package com.nengkey;



/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 17-8-4
 * Time: 上午9:59
 * To change this template use File | Settings | File Templates.
 */
public class SendMailDemo {




        /**
         * 使用Transport 非静态方法 发送邮件
         * 连接163服务，给QQ邮箱发邮件
         */
        public static void main(String[] args) throws Exception {

            // 属性
//            Properties properties = new Properties();
//            // 设置认证属性
//            properties.setProperty("mail.smtp.auth", "true");
//            // 设置通信协议
//            properties.setProperty("mail.transport.protocol", "smtp");
//            // 邮件环境信息
//            Session session = Session.getInstance(properties);
//            // 调试,打印信息
//            session.setDebug(true);
//
//            // 邮件
//            Message message = new MimeMessage(session);
//            // 主题
//            message.setSubject("test message");
//            // 发送人
//            message.setFrom(new InternetAddress("xxxx@163.com"));
//            // 内容
//            message.setText("this is content");
//
//            // 邮件传输对象
//            Transport transport = session.getTransport();
//            // 传输连接：host，port，user，pass/主机，端口，用户名，密码
//            transport.connect("smtp.163.com", 25, "xxxx@163.com", "xxxxxx");
//            // 发送邮件
//            transport.sendMessage(message, new Address[] { new InternetAddress("123456@qq.com") });
//
//            // 关闭连接
//            transport.close();

        System.out.println("Send email!");
        }

}
