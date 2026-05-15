package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountBadSleepAnalyticsTest {

	@Test
	void shouldReturnCorrectCountForNonEmptyList() {
		CountBadSleepAnalytics analytics = new CountBadSleepAnalytics();
		List<SleepSession> sessions = List.of(
				new SleepSession("05.10.25 00:10;05.10.25 06:20;GOOD"),
				new SleepSession("10.10.25 13:00;10.10.25 14:30;NORMAL"),
				new SleepSession("11.10.25 23:10;12.10.25 07:00;BAD"),
				new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL"),
				new SleepSession("15.10.25 23:20;16.10.25 06:10;BAD")
		);
		SleepAnalysisResult result = analytics.apply(sessions);
		assertNotNull(result, "Результат не должен быть null");
		assertEquals("2", result.getValue(), "Количество сессий должно быть равно 4");
		assertEquals("Всего было плохих сессий сна за представленный период", result.getDescription());
	}

	@Test
	void shouldReturnZeroForEmptyList() {
		CountBadSleepAnalytics analytics = new CountBadSleepAnalytics();
		List<SleepSession> sessions = Collections.emptyList();
		SleepAnalysisResult result = analytics.apply(sessions);
		assertNotNull(result, "Результат не должен быть null");
		assertEquals("0", result.getValue(), "Для пустого списка количество сессий должно быть 0");
		assertEquals("Всего было плохих сессий сна за представленный период", result.getDescription());
	}


}