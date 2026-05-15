package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MinSleepDurationAnalytics implements Function<List<SleepSession>, SleepAnalysisResult> {

	@Override
	public SleepAnalysisResult apply(List<SleepSession> sessions) {
		SleepSession minSleepDuration = sessions.stream()
				.min(SleepSession::compareByDurationSleep)
				.orElseThrow(() -> new IllegalArgumentException("Список сессий сна пуст!"));

		return new SleepAnalysisResult(
				"Самая минимальная сессия сна",
				String.valueOf(minSleepDuration.getSleepDuration().toMinutes())
		);
	}
}