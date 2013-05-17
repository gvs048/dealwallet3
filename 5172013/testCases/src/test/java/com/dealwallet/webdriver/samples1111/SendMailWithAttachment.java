package com.dealwallet.webdriver.samples;



import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

public class SendMailWithAttachment {

 public static void main(String args[]) throws Exception {
 String host = "192.168.10.123";
 String from = "gvs048@gmail.com";
 String to = "subbuqat@gmail.com";

 // Get system properties
 Properties properties = System.getProperties();

 // Setup mail server
 properties.setProperty("mail.smtp.host", "smtp.gmail.com");

 // Get the default Session object.
 Session session = Session.getDefaultInstance(properties);

 // Define message
 Message message = new MimeMessage(session);
 message.setFrom(new InternetAddress(from));
 message.addRecipient(Message.RecipientType.TO,
 new InternetAddress(to));
 message.setSubject("JavaMail Attachment");

 // Create the message part 
 BodyPart messageBodyPart = new MimeBodyPart();

 // Fill the message
 messageBodyPart.setText("hi");

 Multipart multipart = new MimeMultipart();
 multipart.addBodyPart(messageBodyPart);

 // Part two is attachment
 messageBodyPart = new MimeBodyPart();
 String filename = "C:\\Users\\Tsss-Pc1\\Desktop\\3000.txt";
 DataSource source = new FileDataSource(filename);
 messageBodyPart.setDataHandler(new DataHandler(source));
 messageBodyPart.setFileName(filename);
 multipart.addBodyPart(messageBodyPart);

 // Put parts in message
 message.setContent(multipart);

 // Send the message
 Transport.send(message);
  System.out.println("Msg Send ....");
 }
}