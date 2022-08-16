package com.soumyadeep.emailgui.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public boolean sendEmail(String to, String subject, String message) {
        String from="soumyadeepmukherjee6527@gmail.com";
        
        boolean flag=false;

        //variable for gmail SMTP host
        String host="smtp.gmail.com";
        
        Properties props = System.getProperties();
        System.out.println("Properties: " + props);

        //setting important information to properties object

        //host set
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        //Step 1: to get the Session object
		Session session=Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication("soumyadeepmukherjee6527@gmail.com", "dhsvaqdjzyiecpav");
			}
			
		});
		
		session.setDebug(true);
		
		//Step 2 : Compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//adding text to message
		m.setText(message);
		
		//send 
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		
		System.out.println("Sent success..........");
        flag=true;
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	
        return flag;
    }
    
}