package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountSleeplessNightAnalyticsTest {

	@Test
	void shouldReturnCorrectCountForNonEmptyList() {
		CountSleeplessNightAnalytics analytics = new CountSleeplessNightAnalytics();
		List<SleepSession> sessions = List.of(
				new SleepSession("03.10.25 07:50;03.10.25 09:40;BAD"),
				new SleepSession("05.10.25 00:10;05.10.25 06:20;GOOD"),
				new SleepSession("10.10.25 13:00;10.10.25 14:30;NORMAL"),
				new SleepSession("11.10.25 23:10;12.10.25 07:00;BAD"),
				new SleepSession("15.10.25 23:20;16.10.25 06:10;BAD")
		);
		SleepAnalysisResult result = analytics.apply(sessions);
		assertNotNull(result, "Результат не должен быть null");
		assertEquals("11", result.getValue(), "Количество бессонных ночей должно быть равно 11");
	}

	@Test
	void shouldThrowExceptionWhenListIsEmpty() {
		CountSleeplessNightAnalytics analytics = new CountSleeplessNightAnalytics();
		List<SleepSession> sessions = Collections.emptyList();
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> analytics.apply(sessions),
				"Метод должен выбросить IllegalArgumentException, если список пуст"
		);
		assertEquals("Список сессий сна пуст", exception.getMessage());
	}

	@Test
	void shouldNotCountAsSleeplessIfSleepStartedBeforeSixHours() {
		CountSleeplessNightAnalytics analytics = new CountSleeplessNightAnalytics();
		List<SleepSession> sessions = List.of(
				new SleepSession("03.10.25 05:59;03.10.25 07:40;BAD")
		);
		SleepAnalysisResult result = analytics.apply(sessions);
		assertEquals("0", result.getValue(), "Количество бессонных ночей должно быть равно 0");

	}

	@Test
	void shouldCountAsSleeplessIfSleepStartedAfterSixHours() {
		CountSleeplessNightAnalytics analytics = new CountSleeplessNightAnalytics();
		List<SleepSession> sessions = List.of(
				new SleepSession("03.10.25 06:00;03.10.25 19:40;GOOD")
		);
		SleepAnalysisResult result = analytics.apply(sessions);
		assertEquals("1", result.getValue(), "Количество бессонных ночей должно быть равно 1");

	}
}