package com.running.coins.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * MailService
 *
 * @author guxiang
 * @date 2018/4/29
 */
@Service
@Slf4j
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.addressesEmail}")
    private String addressesEmail;


    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer configurer;


    public void sendMessageMail(Object params,Object userInfos,Object dailyVotedCountVos, Object weeklyAwardedReportVos, String title, String templateName) {

        try {

            log.info("---1");
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            log.info("---2");
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            log.info("---3");
            helper.setFrom(from);
            log.info("---4");
            helper.setTo(InternetAddress.parse(addressesEmail));
            log.info("---5");
            helper.setSubject("【" + title + "】");
            log.info("---6");

            Map<String, Object> model = new HashMap<>();
            log.info("---7");
            model.put("MailBeanList", params);
            log.info("---8");
            model.put("UserInfoList", userInfos);
            model.put("dailyVotedRecordList",dailyVotedCountVos);
            model.put("weeklyAwardedList",weeklyAwardedReportVos);
            log.info("---9");
            try {
                Template template = configurer.getConfiguration().getTemplate(templateName);
                log.info("---10");
                try {
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                    log.info("---11");

                    helper.setText(text, true);
                    log.info("---12");
                    mailSender.send(mimeMessage);
                    log.info("---13");
                } catch (TemplateException e) {
                    log.info("---14");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                log.info("---15");
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            log.info("---16");
            e.printStackTrace();
        }
    }
}