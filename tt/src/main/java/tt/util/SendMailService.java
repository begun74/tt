package tt.util;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SendMailService {

    @Resource
    private Environment env;
    
    //@Autowired
    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();  
    
    private Properties props = System.getProperties();
    
//    public SendMailService () {
    //}
    
    public boolean sendOrder(String subj, String text) {
    	
    	try {
    			SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom(env.getProperty("fromAddress"));
				message.setTo(env.getProperty("toAddress"));
				message.setSubject(subj == null?env.getProperty("subject"):subj);
				message.setText(env.getProperty("msgBody")+"\n" +text);
				mailSender.setHost(env.getProperty("spring.mail.host"));
			    mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
			    mailSender.setProtocol(env.getProperty("spring.mail.protocol"));
			    mailSender.setUsername(env.getProperty("spring.mail.username"));
			    mailSender.setPassword(env.getProperty("spring.mail.password"));	
			    mailSender.setJavaMailProperties(props);
				mailSender.send(message);
		    	
				System.out.println("Send new order From: " +env.getProperty("fromAddress") + "   to: "+env.getProperty("toAddress"));
				
				return true;
    	}
    	catch(Exception exc) {
    		System.err.println("ERROR: SendMailService.sendOrder()  ");
    		exc.printStackTrace();
    		
    		return false;
    	}
		
    	
    }
    
    @PostConstruct
	void init(){

    	 props.put("mail.smtp.auth", env.getProperty("spring.mail.smtp.auth"));
    	 props.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.smtp.starttls"));
    	 props.put("mail.smtp.starttls.required", env.getProperty("spring.mail.smtp.starttls"));
    	 mailSender.setJavaMailProperties(props);
    	 
        //System.out.println("Send mail to host - " +env.getProperty("host"));
        //mailSender.setJavaMailProperties(props);
	}
}
