package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChronotypeAnalyticsTest {

	@Test
	void shouldReturnOwlChrono() {
		ChronotypeAnalytics analytics = new ChronotypeAnalytics();
		List<SleepSession> sessions = List.of(
				new SleepSession("02.10.25 23:20;03.10.25 10:20;BAD")
		);
		SleepAnalysisResult result = analytics.apply(sessions);
		assertNotNull(result);
		assertEquals("сова", result.getValue());
	}

	@Test
	void shouldReturnLarkChrono() {
		ChronotypeAnalytics analytics = new ChronotypeAnalytics();
		List<SleepSession> sessions = List.of(
				new SleepSession("02.10.25 21:20;03.10.25 06:20;BAD")
		);
		SleepAnalysisResult result = analytics.apply(sessions);
		assertNotNull(result);
		assertEquals("жаворонок", result.getValue());
	}

	@Test
	void shouldReturnPigeonChrono() {
		ChronotypeAnalytics analytics = new ChronotypeAnalytics();
		List<SleepSession> sessions = List.of(
				new SleepSession("02.10.25 23:20;03.10.25 06:20;BAD")
		);
		SleepAnalysisResult result = analytics.apply(sessions);
		assertNotNull(result);
		assertEquals("голубь", result.getValue());
	}
}