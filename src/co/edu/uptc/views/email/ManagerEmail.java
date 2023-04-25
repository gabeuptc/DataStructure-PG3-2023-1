package co.edu.uptc.views.email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.io.File;
import java.util.Properties;

public class ManagerEmail {
    private final String emailRemitend;
    private final String password;
    private final Properties properties;
    private Session session;
    private MimeMessage emailToSend;
    private static ManagerEmail instance;

    private ManagerEmail() {
        this.emailRemitend ="data.structure.pg3.2023@gmail.com";
        this.password ="rbhqxkkigcsghdfd";
        this.properties = new Properties();
    }

    public static ManagerEmail getInstance(){
        if (instance==null){
            instance = new ManagerEmail();
        }
        return instance;
    }

    public void createEmail(String emailDestin,String subject, String body,String filePath){
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user",emailRemitend);
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.setProperty("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);

        try {
            MimeMultipart elements = new MimeMultipart();

            MimeBodyPart text = new MimeBodyPart();
            text.setContent(body, "text/html; charset=utf-8");
            elements.addBodyPart(text);

            MimeBodyPart file= new MimeBodyPart();
            File toSent = new File(filePath);
            file.setDataHandler(new DataHandler(new FileDataSource(toSent.getAbsolutePath())));
            file.setFileName(toSent.getName());
            elements.addBodyPart(file);

            emailToSend = new MimeMessage(session);
            emailToSend.setFrom(new InternetAddress(emailRemitend));
            emailToSend.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestin));
            emailToSend.setSubject(subject);
            emailToSend.setContent(elements);


        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null,"No se creó el correo");
        }
    }

    public void sendEmail(){
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect(emailRemitend, password);
            transport.sendMessage(emailToSend, emailToSend.getRecipients(Message.RecipientType.TO));
            transport.close();

            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null,"No se envió el mensaje");
        }
    }

}
