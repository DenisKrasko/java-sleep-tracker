package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MaxSleepDurationAnalytics implements Function<List<SleepSession>, SleepAnalysisResult> {

	@Override
	public SleepAnalysisResult apply(List<SleepSession> sessions) {
		SleepSession maxSleepDuration = sessions.stream()
				.max(SleepSession::compareByDurationSleep)
				.orElseThrow(() -> new IllegalArgumentException("Список сессий сна пуст!"));
		return new SleepAnalysisResult(
				"Самая максимальная сессия сна",
				String.valueOf(maxSleepDuration.getSleepDuration().toMinutes())
		);
	}
}
