package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AverageSleepDurationAnalytics implements Function<List<SleepSession>, SleepAnalysisResult> {

	@Override
	public SleepAnalysisResult apply(List<SleepSession> sessions) {
		if (sessions == null || sessions.isEmpty())
			throw new IllegalArgumentException("Список сессий сна пуст!");
		double averageSleepDuration = sessions.stream()
				.mapToLong(session -> session.getSleepDuration().toMinutes())
				.average()
				.orElse(0.0);
		return new SleepAnalysisResult(
				"Средняя продолжительность сесси сна",
				String.valueOf((int) averageSleepDuration)
		);
	}
}