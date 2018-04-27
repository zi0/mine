package polymorphism;

import org.springframework.stereotype.Component;

@Component("sony")

public class SonySpeaker implements Speaker {
	public SonySpeaker() {
		System.out.println("SonySpeaker 객체 생성");
	}
	
	@Override
	public void VolumeUp() {
		System.out.println("SonySpeaker 소리 올림");
	}
	
	@Override
	public void VolumeDown() {
		System.out.println("SonySpeaker 소리 내림");
	}
}
