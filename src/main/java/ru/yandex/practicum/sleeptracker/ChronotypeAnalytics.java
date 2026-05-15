package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ChronotypeAnalytics implements Function<List<SleepSession>, SleepAnalysisResult> {
	@Override
	public SleepAnalysisResult apply(List<SleepSession> sessions) {
		if (sessions == null || sessions.isEmpty()) {
			throw new IllegalArgumentException("Список сессий сна пуст");
		}
		int owl = 0;
		int lark = 0;
		int pigeon = 0;
		for (SleepSession session : sessions) {
			LocalDate startDate = session.getStartSleep().toLocalDate();
			LocalDate endDate = session.getFinishSleep().toLocalDate();
			LocalDateTime targetStart1 = LocalDateTime.of(startDate, LocalTime.MIDNIGHT);
			LocalDateTime targetEnd1 = LocalDateTime.of(startDate, LocalTime.of(6, 0));
			LocalDateTime targetStart2 = LocalDateTime.of(endDate, LocalTime.MIDNIGHT);
			LocalDateTime targetEnd2 = LocalDateTime.of(endDate, LocalTime.of(6, 0));
			boolean isNightSession = (session.getStartSleep().isBefore(targetEnd1) && session.getFinishSleep().isAfter(targetStart1)) ||
							(session.getStartSleep().isBefore(targetEnd2) && session.getFinishSleep().isAfter(targetStart2));
			if (!isNightSession) {
				continue;
			}
			java.time.LocalTime startTimeSleep = session.getStartSleep().toLocalTime();
			java.time.LocalTime endTimeSleep = session.getFinishSleep().toLocalTime();
			if ((startTimeSleep.isAfter(LocalTime.of(23, 0))
					&& endTimeSleep.isAfter(LocalTime.of(9, 0)))
					|| (startTimeSleep.isAfter(LocalTime.of(0, 0))
					&& endTimeSleep.isAfter(LocalTime.of(9, 0)))) {
				owl++;
			} else if (startTimeSleep.isBefore(LocalTime.of(22, 0))
					&& endTimeSleep.isBefore(LocalTime.of(7, 0))) {
				lark++;
			} else {
				pigeon++;
			}
		}
		String userChronotype = determinationChronotype(owl, lark, pigeon);
		return new SleepAnalysisResult(
				"Хронотип пользователя: ",
				String.valueOf(userChronotype)
		);
	}

	String determinationChronotype(int owl, int lark, int pigeon) {
		if (owl == 0 && lark == 0 && pigeon == 0) {
			return "Небыло найдено ни одного сна в ночной промежуток";
		}
		if ((owl == lark && owl >= pigeon) || (owl == pigeon && owl >= lark) || (lark == pigeon && lark >= owl)) {
			return "голубь";
		}
		if (owl > lark && owl > pigeon) {
			return "сова";
		} else if (lark > owl && lark > pigeon) {
			return "жаворонок";
		} else {
			return "голубь";
		}
	}
}