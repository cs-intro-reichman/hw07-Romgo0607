
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);

	}

	public static String tail(String str) {
		if(str.length() != 1) {
			str = str.substring(1);
		} else {
			str = "";
		}
		return str;
	}

	public static int levenshtein(String word1, String word2) {
		int a = word1.length();
		int b = word2.length();
		String word1ToLower = word1.toLowerCase();
		String word2ToLower = word2.toLowerCase();
		if(b == 0) {
			return a;
		} else if(a == 0) {
			return b;
		} else if(word1ToLower.charAt(0) == word2ToLower.charAt(0)) {
			 return levenshtein(tail(word1ToLower), tail(word2ToLower));
		} 
		return 1 + Math.min(levenshtein(tail(word1ToLower), word2ToLower), (Math.min(levenshtein(word1ToLower, tail(word2ToLower)), levenshtein(tail(word1ToLower), tail(word2ToLower)))));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String finalWord = "";
		if(existInDictionary(word, dictionary)) {
			return word;
		} else {
			for(int i = 0; i < dictionary.length; i++) {
				// #feedback - you should find the minimum levenshtein distance, not necessarily the one that equals to the threshold.
				if (levenshtein(dictionary[i], word) == threshold) {
					finalWord = dictionary[i];
					break;
				}
			}
		}
		if(finalWord == "") {
			return word;
		} else {
			return finalWord;
		}
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		String lowerCaseWord = word.toLowerCase();
		boolean exist = false;
		for(int i = 0; i < dictionary.length; i++) {
			if(lowerCaseWord.equals(dictionary[i])) {
				exist = true;
			}
		}
		return exist;
	}
}
