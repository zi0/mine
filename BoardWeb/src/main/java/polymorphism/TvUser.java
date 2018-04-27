package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {
		// BeanFactory factory = new BeanFactory();
		// TV tv = (TV)factory.getBean(args[0]);
		
		// spring 컨테이너 구동
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		// spring 컨테이너로부터 필요한 객체 요청
		// TV tv = (TV)factory.getBean(args[0]);
		TV tv = (TV)factory.getBean("tv");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
	
	}

}
