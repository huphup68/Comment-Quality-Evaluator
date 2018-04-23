package comments;

import edu.stanford.nlp.simple.*;

public final class Readability {
	private Readability() {
		
	}
	/** 
	 * Searches the array of words to see if the examined word is included
	 * @return True if the word examined exists in the array, otherwise false 
	 * */
	private static boolean binarySearch(String exWord) {
		int bot = 0;
		int top = DaleChallWords.getDaleChall().length;	
		int mid;
		while(bot <= top) {
			mid = (top + bot)/2;
			if(Concurrency.levenshtein(DaleChallWords.getDaleChall()[mid], exWord) > 2) {
				if(exWord.compareToIgnoreCase(DaleChallWords.getDaleChall()[mid]) < 0) {
						top = mid -1;
				}
				else if(exWord.compareToIgnoreCase(DaleChallWords.getDaleChall()[mid]) > 0) { 
					bot = mid +1;
				}
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
	private static double daleChallCalculator(int complexWords, int wordCount, int sentenceCount) {
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
	public static double daleChall(String text){
		int complexWords = 0;
		int wordCount = 0;
		int sentenceCount = 0;
		Document doc = new Document(text);
		for(Sentence sent : doc.sentences()) {
			sentenceCount++;
			for(String word : sent.words()) {
				wordCount++;
				if(!binarySearch(word)) {
					complexWords++;
				}
			}
		}
		return daleChallCalculator(complexWords, wordCount, sentenceCount);
	}
}
