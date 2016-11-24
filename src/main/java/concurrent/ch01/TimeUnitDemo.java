package concurrent.ch01;

import java.util.concurrent.TimeUnit;

public class TimeUnitDemo {
	private TimeUnit timeUnit = TimeUnit.DAYS;
	public static void main(String[] args) {
		TimeUnitDemo demo = new TimeUnitDemo();
		demo.outInfo();
		demo.timeUnit =TimeUnit.HOURS;
		demo.outInfo();
		demo.timeUnit =TimeUnit.MINUTES;
		demo.outInfo();
		demo.timeUnit =TimeUnit.SECONDS;
		demo.outInfo();
	}
	
	public void outInfo() {
		System.out.println(timeUnit.name());
		System.out.println(timeUnit.toDays(1));
		System.out.println(timeUnit.toHours(1));
		System.out.println(timeUnit.toMinutes(1));
		System.out.println(timeUnit.toMicros(1));
		System.out.println(timeUnit.toMillis(1));
		System.out.println(timeUnit.toNanos(1));
		System.out.println(timeUnit.toSeconds(1));
		System.out.println("一天有："+ (timeUnit.convert(1, TimeUnit.DAYS)+ timeUnit.name()));
		System.out.println("12小时"+ (timeUnit.convert(12, TimeUnit.HOURS)+ timeUnit.name()));
		System.out.println("3600秒有："+ (timeUnit.convert(3600, TimeUnit.MINUTES)+ timeUnit.name()));
		System.out.println("--------------------------");
	}
}
