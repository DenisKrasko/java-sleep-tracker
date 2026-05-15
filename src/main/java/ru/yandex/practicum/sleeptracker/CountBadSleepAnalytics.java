package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class CountBadSleepAnalytics implements Function<List<SleepSession>, SleepAnalysisResult> {

	@Override
	public SleepAnalysisResult apply(List<SleepSession> sessions) {
		long countBadSleep = sessions.stream()
				.filter(session -> session.getSleepCharacterisctics().equals(SleepCharacterisctics.BAD))
				.count();
		return new SleepAnalysisResult(
				"Всего было плохих сессий сна за представленный период",
				String.valueOf(countBadSleep)
		);
	}
}