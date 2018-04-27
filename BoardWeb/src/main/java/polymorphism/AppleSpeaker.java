package polymorphism;

import org.springframework.stereotype.Component;

@Component("apple")

public class AppleSpeaker implements Speaker{

	public AppleSpeaker() {
		System.out.println("AppleSpeaker 객체 생성");
	}
	
	@Override
	public void VolumeUp() {
		System.out.println("AppleSpeaker 소리 올림");		
	}

	@Override
	public void VolumeDown() {
		System.out.println("AppleSpeaker 소리 내림");		
	}

	
}
