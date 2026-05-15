package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class CountSleepAnalytics implements Function<List<SleepSession>, SleepAnalysisResult> {

	@Override
	public SleepAnalysisResult apply(List<SleepSession> sessions) {
		long countSleep = sessions.stream()
				.count();
		return new SleepAnalysisResult(
				"Всего было сессий сна за представленный период",
				String.valueOf(countSleep)
		);
	}
}