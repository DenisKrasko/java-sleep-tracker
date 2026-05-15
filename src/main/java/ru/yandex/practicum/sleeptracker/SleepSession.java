package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepSession {
	private String session;
	private LocalDateTime startSleep;
	private LocalDateTime finishSleep;
	private SleepCharacterisctics sleepCharacterisctics;
	private Duration sleepDuration;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

	public SleepSession(String session) {
		this.session = session;
		String[] split = session.split(";");
		this.startSleep = LocalDateTime.parse(split[0], formatter);
		this.finishSleep = LocalDateTime.parse(split[1], formatter);
		this.sleepCharacterisctics = SleepCharacterisctics.valueOf(split[2]);
		this.sleepDuration = Duration.between(startSleep, finishSleep);
	}

	static int compareByDurationSleep(SleepSession s1, SleepSession s2) {
		return (int) (s1.sleepDuration.toMinutes() - s2.sleepDuration.toMinutes());
	}


	public String getSession() {
		return session;
	}

	public LocalDateTime getStartSleep() {
		return startSleep;
	}

	public LocalDateTime getFinishSleep() {
		return finishSleep;
	}

	public SleepCharacterisctics getSleepCharacterisctics() {
		return sleepCharacterisctics;
	}

	public Duration getSleepDuration() {
		return sleepDuration;
	}
}