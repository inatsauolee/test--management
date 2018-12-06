package ma.map.tm.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:messages.properties")
public class MessageService {
	
	@Autowired
	private Environment env;
	
	public String getProperty(String name, Object...atrs) {
		return  MessageFormat.format(env.getProperty(name), atrs);
		
	}
}