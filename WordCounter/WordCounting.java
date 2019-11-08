package com.chrishh.problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class WordCounting {

	public static void main(String[] args) throws FileNotFoundException {

		File inputFile = new File("exercise.txt");
		Scanner sc = new Scanner(inputFile);
		String line = "";
		String allLines = "";
		Map<String, Integer> wordMap = new HashMap<String, Integer>();

		while (sc.hasNext()) {
			allLines += (line = sc.nextLine());
		}
		sc.close();
		String[] words = allLines.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

		for (int i = 0; i < words.length - 1; i++) {
			Integer counter = wordMap.get(words[i]);
			if (counter != null) {

				counter = wordMap.containsKey(words[i]) ? wordMap.get(words[i]) : 0;
				wordMap.put(words[i], counter + 1);

			} else {
				counter = 1;
				wordMap.put(words[i], counter);
			}
		}

		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + " ==== " + entry.getValue());
		}
	}
}