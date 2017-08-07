package com.nengkey;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 17-8-4
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailSystem {

    static Authenticator auth = new Authenticator() {

        @Override

        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication("neckx@sohu.com", "220163");
        }
    };
    public static void Sendmail(Mail mail) {

        Properties props = new Properties();
        props.put("mail.smtp.host", mail.getHost());
        props.put("mail.smtp.auth", "true");
        props.put("mail.from", mail.getSender());

//         Authenticator auth = new Authenticator() {
//            @Override
//           protected PasswordAuthentication getPasswordAuthentication() {
//
//                return new PasswordAuthentication(mail.getSender(), "220163");
//            }
//        };
        Session session = Session.getInstance(props, auth);
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO, mail.getReceiver());
            msg.setSubject(mail.getSubject());
            msg.setSentDate(new Date());
            msg.setText(mail.getMessage());
            Transport.send(msg);
        } catch (MessagingException mex) {
            System.out.println("send failed, exception: " + mex);
        }

    }
    public static void main(String[] args) {
        Mail mail = new Mail();
        mail.setHost("smtp.sohu.com");
        mail.setSender("neckx@sohu.com");
        mail.setUsername("何英高");
        mail.setPassword("220163");

        mail.setReceiver("heyg@wxgyxy.cn");

        mail.setSubject("更新mail信息");
        mail.setMessage("更新个人信息成功");

        MailSystem.Sendmail(mail);
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.sohu.com");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.from", "neckx@sohu.com");
//        Session session = Session.getInstance(props, auth);
//        try {
//            MimeMessage msg = new MimeMessage(session);
//            msg.setFrom();
//            msg.setRecipients(Message.RecipientType.TO, "heyg@wxgyxy.cn");
//            msg.setSubject("JavaMail hello world example");
//            msg.setSentDate(new Date());
//            msg.setText("Hello, world! 发送邮件成功！\n");
//            Transport.send(msg);
//        } catch (MessagingException mex) {
//            System.out.println("send failed, exception: " + mex);
//        }

    }
}