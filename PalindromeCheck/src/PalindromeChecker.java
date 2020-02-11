import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PalindromeChecker {

	private static Word checkPalindrome(String originalWord) {
		String word = originalWord;
		word = word.toLowerCase().replaceAll(" ", "").replaceAll("-", "").replaceAll("'", "");

		for (int i = 0; i < word.length() / 2; i++) {
			if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
				if (i == 0) {
					return new Word(originalWord, word, false, '\0');
				}
				return new Word(originalWord, word, false, word.charAt(i - 1));
			}
		}
		return new Word(originalWord, word, true, '\0');
	}

	public static void main(String[] args) throws FileNotFoundException {

		File inputFile = new File("word-list.txt");
		Scanner sc = new Scanner(inputFile);
		List<String> words = new ArrayList<String>();
		while (sc.hasNext()) {
			words.add(sc.nextLine());
		}
		sc.close();

		List<Word> yesPal = new ArrayList<Word>();
		List<Word> notPal = new ArrayList<Word>();
		for (String testWord : words) {
			Word word = checkPalindrome(testWord);
			if (word.isPalindrome()) {
				yesPal.add(word);
			} else {
				notPal.add(word);
			}
		}

		System.out.println("    *****   Palindromes   *****");
		Collections.sort(yesPal);
		for (Word pal : yesPal) {
			System.out.println(pal.getOriginalText());
		}
		System.out.println();
		System.out.println("    *****   Not Palindromes   *****");
		Collections.sort(notPal);
		for (Word obj : notPal) {
			if ((obj.getLastMatching()) == '\0') {
				System.out.println(obj.getOriginalText());
			} else {
				System.out.println(obj.getOriginalText() + " the last matching pair is " + obj.getLastMatching());
			}
		}
	}
}



		
