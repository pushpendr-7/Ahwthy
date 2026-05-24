package ahmyth.mine.ahmyth.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;

public class MailSender {
    private static final String USERNAME = "rrealgemigindian@gmail.com";
    private static final String PASSWORD = "jldewikyetjscuqx";

    public void sendEmail(String subject, String body) {
        sendEmail(subject, body, null);
    }

    public void sendEmail(String subject, String body, String attachmentPath) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(USERNAME));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body, "UTF-8");
            multipart.addBodyPart(textPart);

            if (attachmentPath != null && !attachmentPath.isEmpty()) {
                MimeBodyPart filePart = new MimeBodyPart();
                DataSource source = new FileDataSource(new File(attachmentPath));
                filePart.setDataHandler(new DataHandler(source));
                filePart.setFileName(new File(attachmentPath).getName());
                multipart.addBodyPart(filePart);
            }

            message.setContent(multipart);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}