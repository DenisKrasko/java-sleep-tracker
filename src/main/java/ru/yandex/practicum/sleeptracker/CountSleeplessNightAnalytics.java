package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class CountSleeplessNightAnalytics implements Function<List<SleepSession>, SleepAnalysisResult> {

	@Override
	public SleepAnalysisResult apply(List<SleepSession> sessions) {
		if (sessions == null || sessions.isEmpty()) {
			throw new IllegalArgumentException("Список сессий сна пуст");
		}
		LocalDateTime logStart = sessions.stream()
				.map(SleepSession::getStartSleep)
				.min(LocalDateTime::compareTo)
				.orElseThrow();
		LocalDateTime logEnd = sessions.stream()
				.map(SleepSession::getFinishSleep)
				.max(LocalDateTime::compareTo)
				.orElseThrow();
		long totalDays = Duration.between(logStart, logEnd).toDays() + 1;
		LocalDate startDate = logStart.toLocalDate();
		long sleeplessNightCount = 0;
		for (int i = 0; i < totalDays; i++) {
			LocalDate currentDay = startDate.plusDays(i);
			LocalDateTime targetStart = LocalDateTime.of(currentDay, LocalTime.of(0,0));
			LocalDateTime targetEnd = LocalDateTime.of(currentDay, LocalTime.of(6,0));
			boolean hasSleepThisNight = false;
			for (SleepSession session : sessions) {
				if (session.getStartSleep().isBefore(targetEnd) && session.getFinishSleep().isAfter(targetStart)) {
					hasSleepThisNight = true;
					break;
				}
			}
			if (!hasSleepThisNight)
				sleeplessNightCount++;
		}
		return new SleepAnalysisResult(
				"Количество бессонных ночей: ",
				String.valueOf(sleeplessNightCount)
		);
	}
}
