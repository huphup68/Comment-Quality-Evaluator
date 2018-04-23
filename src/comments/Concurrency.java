package comments;

import java.util.Arrays;
import edu.stanford.nlp.simple.*;

public final class Concurrency {
	private static final int LEVENSHTEIN_CUT_OFF_POINT = 2;
	
	private Concurrency() {		// The class can not be instantiated
		
	}
	
	/**
	 * Calculates the cost of substituting characters. 0 if they are the same, 1 otherwise.
	 * @param x The first character to compare
	 * @param y The second character to compare
	 * @return 0 if the parameters are equal, 1 otherwise
	 */
	private static int cost(char x, char y) {
		return x == y ? 0 : 1;
	}
    private static int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
    /**
     * 
     * @param comment A word from the comment
     * @param method A word from the method
     * @return The levenshtein score from comparing the comment word and the method word
     */
	public static int levenshtein(String comment, String method) {
	    int[][] matrix = new int[comment.length() + 1][method.length() + 1];
	    
	    for (int i = 0; i <= comment.length(); i++) {
	        for (int j = 0; j <= method.length(); j++) {
	            if (i == 0) {
	                matrix[i][j] = j;
	            }
	            else if (j == 0) {
	                matrix[i][j] = i;
	            }
	            else {
	                matrix[i][j] = min(matrix[i - 1][j - 1] 
	                 + cost(comment.charAt(i - 1), method.charAt(j - 1)), 
	                  matrix[i - 1][j] + 1, 
	                  matrix[i][j - 1] + 1);
	            }
	        }
	    }
	 
	    return matrix[comment.length()][method.length()];
	}
	/**
	 * 
	 * @param comment A word from the comment
	 * @param method A string representation of the method
	 * @return true if the levenshtein score of the parameters is less than 2, otherwise false.
	 */
	private static boolean similar(String comment, String method) {
		Document doc = new Document(method);
		for(Sentence sent : doc.sentences()) {
			for(String word : sent.words()) {
				if(levenshtein(comment, word) <= LEVENSHTEIN_CUT_OFF_POINT) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Calculates the concurrency of the two parameters
	 * @param comment A string representation of the comment
	 * @param method A string representation of the method
	 * @return The concurrency coefficient of the parameters
	 */
	public static double concurrencyCalc(String comment, String method) {
		int wordCounter = 0;
		int similarCounter = 0;
		Document doc = new Document(comment);
		for(Sentence sent : doc.sentences()) {
			for(String word : sent.words()) {
				wordCounter++;
				if(similar(word, method)) {
					similarCounter++;
				}
			}
		}
		return (double)similarCounter / (double)wordCounter;
	}
	
}
