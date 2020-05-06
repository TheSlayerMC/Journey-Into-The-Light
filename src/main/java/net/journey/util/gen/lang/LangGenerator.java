package net.journey.util.gen.lang;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class LangGenerator {
	private static final String START_MARK = "#MARK AUTO GEN START";
	private static final String END_MARK = "#MARK AUTO GEN END";
	private final File outputFile = new File("../src/main/resources/assets/journey/lang/en_us.lang");

	private HashMap<LangSection<?>, List<String>> genMap = new HashMap<>();

	<T> void addLangEntry(LangSection<T> section, T entry, String enName) {
		addLangEntry(section, section.createLangEntry(entry, enName));
	}

	void addLangEntry(LangSection<?> section, String entry) {
		List<String> list = genMap.computeIfAbsent(section, category -> new ArrayList<>());
		list.add(entry);
	}

	public void save() {
		sortValues();

		try {
			FileUtils.touch(outputFile);

			Path outputPath = outputFile.toPath();
			if (Files.isReadable(outputPath) && Files.isWritable(outputPath)) {
				List<String> lines = Files.readAllLines(outputPath, StandardCharsets.UTF_8);

				int size = lines.size();
				int startIndex = -1;
				int endIndex = -1;
				for (int i = 0; i < size; i++) {
					String s = lines.get(i);
					if (s.contains(START_MARK)) {
						if (startIndex != -1)
							throw new IllegalStateException("Can't handle this file. Found two start index marks in lines: " + startIndex + " and " + i);
						startIndex = i;
					} else if (s.contains(END_MARK)) {
						if (endIndex != -1)
							throw new IllegalStateException("Can't handle this file. Found two end index marks in lines: " + endIndex + " and " + i);
						endIndex = i;
					}
				}

				if (endIndex != -1 && startIndex == -1) {
					throw new IllegalStateException("Can't handle this file. End index is found, but not start index. " +
							"End index in " + endIndex + " line");
				}
				if (endIndex != -1 && endIndex < startIndex) {
					throw new IllegalStateException("Can't handle this file. End index is found earlier that start index. " +
							"End index in " + endIndex + " line, start index in " + startIndex + " line");
				}
				if (startIndex == -1) {
					startIndex = lines.size();
				}

				List<String> startList = lines.subList(0, startIndex);
				List<String> endList = endIndex != -1 ? lines.subList(endIndex + 1, lines.size()) : Collections.emptyList();

				List<String> newList = new ArrayList<>(startList.size() + endList.size());
				newList.addAll(startList);
				newList.add(START_MARK);

				genMap.forEach((category, list) -> {
					newList.add("");
					newList.add(category.getComment());
					newList.addAll(list);
				});

				newList.add(END_MARK);
				newList.addAll(endList);

				Files.write(outputPath, newList, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);

			} else throw new IOException("Can't access " + outputFile.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sortValues() {
		for (LangSection<?> section : genMap.keySet()) {
			genMap.get(section).sort(Comparator.naturalOrder());
		}
	}
}
