package ru.yandex.practicum.sleeptracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    private static List<Function<List<SleepSession>, SleepAnalysisResult>> functions = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("Ошибка: вы не указали путь к файлу лога сна");
            return;
        }
        String logFilePath = args[0];
        SleepTrackerApp sleepTrackerApp = new SleepTrackerApp();
        List<SleepSession> list = sleepTrackerApp.readingFile(logFilePath);

        functions.add(new CountSleepAnalytics());
        functions.add(new MinSleepDurationAnalytics());
        functions.add(new MaxSleepDurationAnalytics());
        functions.add(new AverageSleepDurationAnalytics());
        functions.add(new CountBadSleepAnalytics());
        functions.add(new CountSleeplessNightAnalytics());
        functions.add(new ChronotypeAnalytics());
        for (Function<List<SleepSession>, SleepAnalysisResult> function : functions) {
            System.out.println(function.apply(list));
        }
    }

    public List<SleepSession> readingFile(String logFilePath) throws FileNotFoundException {
        SleepTrackerLoader loader = new SleepTrackerLoader();
        File sessionsFile = loader.getSleepingLogFile(logFilePath);
        List<SleepSession> sleepSessions = loader.readSleepSessionsFromFile(sessionsFile);
        return sleepSessions;
    }
}