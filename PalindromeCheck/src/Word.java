
public class Word implements Comparable<Word>{

	private String originalText;
	private String text;
	private boolean palindrome;
	private char lastMatching;

	public Word(String originalText, String text, boolean palindrome, char lastMatching) {
		this.originalText = originalText;
		this.text = text;
		this.palindrome = palindrome;
		this.lastMatching = lastMatching;
	}

	public String getText() {
		return text;
	}

	public boolean isPalindrome() {
		return palindrome;
	}

	public char getLastMatching() {
		return lastMatching;
	}

	@Override
	public int compareTo(Word w) {
		return this.getText().compareTo(w.getText());
	}

	public String getOriginalText() {
		return originalText;
	}


}
