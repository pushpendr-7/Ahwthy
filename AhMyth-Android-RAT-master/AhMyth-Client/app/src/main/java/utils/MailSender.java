package ahmyth.mine.ahmyth.utils;

import android.os.AsyncTask;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
    private static final String USERNAME = "rrealgemigindian@gmail.com";
    private static final String PASSWORD = "wnsphqubqqtrzkkm";

    public void sendEmail(String subject, String body) {
        new SendMailTask().execute(subject, body);
    }

    private class SendMailTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String subject = params[0];
            String body = params[1];
            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }
                    });

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(USERNAME));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(USERNAME));
                message.setSubject(subject);
                message.setText(body);
                Transport.send(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

