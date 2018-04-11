package comments;
import edu.stanford.nlp.simple.*;

public class Readability {
	private String comment;
	private Document doc;
	private int sentenceCount = 0;
	private int complexWords = 0;
	private int wordCount = 0;
	private String[] words = {"a", "b", "c", "d", "e", "f"};		// Temporary complex words REPLACE LATER
	public Readability(String comment) {
		this.comment = comment;
		doc = new Document(this.comment);
		for(Sentence sent : doc.sentences()) {
			sentenceCount++;
			for(String word : sent.words()) {
				wordCount++;
			}
		}
	}
	/** 
	 * Searches the array of words to see if the examined word is included
	 * @return True if the word examined exists in the array, otherwise false 
	 * */
	private boolean binarySearch(String exWord) {
		int bot = 0;
		int top = wordCount;	
		int mid;
		while(bot <= top) {
			mid = (top + bot)/2;
			if(exWord.compareToIgnoreCase(words[mid]) < 0) {
				top = mid -1;
			}
			else if(exWord.compareToIgnoreCase(words[mid]) > 0) { 
				bot = mid +1;
			}
			else {
				return true;
			}
		}
		return false;
	}
	/** 
	 * Applies the formula for calculating the Dale-Chall sore to the comment
	 * @return The Dale-Chall score of the comment 
	 * */
	private double daleChallCalculator() {
		double res;
		res = 0.157*((double)complexWords/(double)wordCount*100) + (0.0496*((double)wordCount/(double)sentenceCount));
		if((double)complexWords/(double)wordCount > 0.05) {
			res += 3.6365;
		}
		return res;
	}
	/** 
	 * Iterates over each word to count how many are complex and then returns score
	 * @return The Dale-Chall score of the comment
	 */
	public double daleChall(){
		for(Sentence sent : doc.sentences()) {
			for(String word : sent.words()) {
				if(!binarySearch(word)) {
					complexWords++;
				}
			}
		}
		return daleChallCalculator();
	}
}