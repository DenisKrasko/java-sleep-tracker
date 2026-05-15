package ru.yandex.practicum.sleeptracker;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerLoader {

	public File getSleepingLogFile(String sleepingLogFile) throws FileNotFoundException {
		Path sessinonsFilePath = Paths.get(sleepingLogFile);
		File sessinonsFile = sessinonsFilePath.toFile();
		if (!sessinonsFile.exists()) throw new FileNotFoundException();
		return sessinonsFile;
	}

	public List<SleepSession> readSleepSessionsFromFile(File sessionsFile) {
		List<SleepSession> sleepingSessions = new ArrayList<>();
		try (FileReader fileReader = new FileReader(sessionsFile, StandardCharsets.UTF_8)) {
			BufferedReader br = new BufferedReader(fileReader);
			while (br.ready()) {
				sleepingSessions.add(new SleepSession(br.readLine()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sleepingSessions;
	}
}