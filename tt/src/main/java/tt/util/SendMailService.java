package tt.util;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tt.model.Order;
import tt.model.OrderItems;

@Component
@PropertySource("classpath:mail.properties")
public class SendMailService {

    @Resource
    private Environment env;
    
    //@Autowired
    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();  
    
    private Properties props = System.getProperties();
    
    public SendMailService () {
    	
    }
    
    public boolean sendOrder(Order order) {
    	
    	try {
    		String subject = env.getProperty("subject");
    		
    		if(new Boolean(env.getProperty("sendOrder.toAddress"))) {
    			SimpleMailMessage message = new SimpleMailMessage();
    			//MimeMessage message = mailSender.createMimeMessage();
				message.setSubject(subject+" № "+order.getId());
				
				String messBody = " № "+order.getId()+"\n";
				messBody += "Имя: "+order.getPerson_name()+"\n";
				messBody += "E-mail: "+order.getEmail()+"\n";
				messBody += "Телефон: "+order.getPhone()+"\n";
				
				messBody += "Примечание:  "+ order.getComment()+"\n\n\n";
				
				for(OrderItems oi: order.getOrderItems()) 
					messBody = messBody + (oi.getTail().getDirNomenclature().getName() +" (модель "+oi.getTail().getDirNomenclature().getModel()+ " арт. "+oi.getTail().getDirNomenclature().getArticle()+")  размер: "+oi.getTail().getSize() + "    - "+ oi.getAmount()+"шт. \n");

				
				message.setText(env.getProperty("msgBody")+" "+messBody+"\n");
				

    			//MimeMessageHelper helper = new MimeMessageHelper(message, true);
    			
				message.setFrom(env.getProperty("fromAddress"));
				message.setTo(env.getProperty("toAddress"));
    			//helper.setTo(env.getProperty("toAddress"));
    			//helper.setFrom(env.getProperty("fromAddress"));
			    
				mailSender.send(message);
		    	
				System.out.println("Send "+subject+" № "+order.getId()+" From: " +env.getProperty("fromAddress") + "   to: "+env.getProperty("toAddress"));
				
				return true;
    		}
    		else
    			return false;
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

		mailSender.setHost(env.getProperty("spring.mail.host"));
	    mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
	    mailSender.setProtocol(env.getProperty("spring.mail.protocol"));
	    mailSender.setUsername(env.getProperty("spring.mail.username"));
	    mailSender.setPassword(env.getProperty("spring.mail.password"));	
	    mailSender.setJavaMailProperties(props);
    	
        //System.out.println("Send mail to host - " +env.getProperty("host"));
        //mailSender.setJavaMailProperties(props);
	}
}
