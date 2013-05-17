package com.dealwallet.webdriver.samples;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendEmailwithAttachFile extends Object{
	
	 public static void main(String args[]) throws Exception {
	try{

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        //for yahoo use smtp.mail.yahoo.com 
        //for gmail use smtp.gmail.com
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gvs048@gmail.com", "9030863011");
            }
        });

        mailSession.setDebug(true); // Enable the debug mode

        Message msg = new MimeMessage( mailSession );

        //--[ Set the FROM, TO, DATE and SUBJECT fields
        msg.setFrom( new InternetAddress( "gvs048@gmail.com" ) );
        msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse("subbuqat@gmail.com") );
        msg.setSentDate( new Date());
        msg.setSubject( "Hello World!" );
        //s="Hello from my first e-mail sent with JavaMail";
        //--[ Create the body of the mail
        msg.setText( "s" );
        
        
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText("hi, hello");
        
        BodyPart messageBodyPart = new MimeBodyPart();
        
        Multipart multipart = new MimeMultipart();
        messageBodyPart = new MimeBodyPart();
        String filename = "C:\\Users\\Tsss-Pc1\\Desktop\\3000";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(messageBodyPart1);

        // Put parts in message
        msg.setContent(multipart);
        
        
       //--[ Ask the Transport class to send our mail message
        Transport.send( msg );

        
    }catch(Exception E){
        System.out.println( "Oops something has gone pearshaped!");
        System.out.println( E );
    }
}
}

