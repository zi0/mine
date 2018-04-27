package polymorphism;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV{
	
	@Autowired				// 클래스타입
	@Qualifier("apple")		// id값으로 검색
	@Resource(name="apple")	
	Speaker speaker;
		
	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void powerOn() {
		System.out.println("LgTV powerOn");
	}
	
	public void volumeUp() {
		speaker.VolumeUp();
	}
	
	public void volumeDown() {
		speaker.VolumeDown();
	}

	public void powerOff() {
		System.out.println("LgTv powerOff");
	}
}	
	
