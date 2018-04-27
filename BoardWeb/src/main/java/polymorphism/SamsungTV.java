package polymorphism;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("samsung")

public class SamsungTV implements TV {

	@Autowired
	private Speaker speaker;
	private int price;
	

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// 생성자 방식으로 의존관계 객체 주입
	public SamsungTV(Speaker speaker, int price	) {
		this.speaker = speaker;
		this.price = price;
	}
	
	public SamsungTV() {
		
	}

	public void initMethod() {
		System.out.println("SamsungTv 초기화");
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTv powerOn" + price);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polymorphism.TV#powerOff()
	 */
	@Override
	public void powerOff() {
		System.out.println("SamsungTv powerOff");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polymorphism.TV#volumeUp()
	 */
	@Override
	public void volumeUp() {
		//speaker = new SonySpeaker();
		speaker.VolumeUp();
		// System.out.println("SamsungTv volumeUp");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see polymorphism.TV#volumeDown()
	 */
	@Override
	public void volumeDown() {
		speaker = new SonySpeaker();
		speaker.VolumeDown();
		// System.out.println("SamsungTv volumeDown");
	}
}
