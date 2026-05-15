package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class AverageSleepDurationAnalyticsTest {

	@Test
	void shouldFindAverageSleepSession() {
		AverageSleepDurationAnalytics analytics = new AverageSleepDurationAnalytics();
		List<SleepSession> sessions = List.of(
				new SleepSession("05.10.25 00:10;05.10.25 06:20;GOOD"),
				new SleepSession("10.10.25 13:00;10.10.25 14:30;NORMAL"),
				new SleepSession("11.10.25 23:10;12.10.25 07:00;BAD"),
				new SleepSession("02.10.25 23:50;03.10.25 06:40;NORMAL")
		);
		SleepAnalysisResult result = analytics.apply(sessions);
		assertNotNull(result, "Результат не должен быть null");
		assertEquals("335", result.getValue(), "Должна быть выбрана сессия с 5 часами");
		assertEquals("Средняя продолжительность сесси сна", result.getDescription());
	}

	@Test
	void shouldThrowExceptionWhenListIsEmpty() {
		AverageSleepDurationAnalytics analytics = new AverageSleepDurationAnalytics();
		List<SleepSession> sessions = Collections.emptyList();
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class,
				() -> analytics.apply(sessions),
				"Метод должен выбросить IllegalArgumentException, если список пуст"
		);
		assertEquals("Список сессий сна пуст!", exception.getMessage());
	}

}