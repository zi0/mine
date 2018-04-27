package com.yedam.app.view.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	/**
	 * 1. 오전 09:30:00에 호출이 되는 스케쥴러
	 */
	@Scheduled(cron = "0 30 09 * * *")
	public void cronTest1() {
		System.out.println("오전 09:30:00에 호출이 됩니다 ");
	}

	/**
	 * 2. 오전 09:30:00에 호출이 되는 스케쥴러
	 */
	@Scheduled(cron = "0 50/1 09 * * *")
	public void cronTest2() {
		System.out.println("오전 09:30:00에 호출이 됩니다 ");
	}

	@Scheduled(fixedDelay = 5000)
	public void doSomething() {
		System.out.println("주기적으로 실행될 것이다");
	}
	
	
}
