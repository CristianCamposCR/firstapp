package mx.edu.utez.firstapp.utils;

import mx.edu.utez.firstapp.controllers.contact.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public boolean sendMail(EmailDto email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("20213tn003@utez.edu.mx");
            helper.setSubject("Market | nuevo mensaje");
            helper.setText(template(email), true);
            mailSender.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String template (EmailDto email) {
        return "HOLIS" + email.getFullname() + "los comentarios son: "  + email.getComments() + "lo estas enviando desde" +email.getEmail();
    }
}
