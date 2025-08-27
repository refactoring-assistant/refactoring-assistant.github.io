import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TextAnalyzer {
  private final String WORD_COUNT = "Word count: ";
  private final String SENTENCE_COUNT = "Sentence count: ";
  private final String AVG_WORD_LENGTH_COUNT = "Average word length: ";
  private final String MOST_COMMON_WORD = "Most common word: ";

  public void analyze(String filePath) {
    try {
      StringBuilder text = new StringBuilder();
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      String line;
      while ((line = reader.readLine()) != null) {
        text.append(line).append(" ");
      }
      reader.close();
      String content = text.toString().trim();

      String[] words = content.split("\\s+");
      int wordCount = words.length;

      String[] sentences = content.split("[.!?]");
      int sentenceCount = sentences.length;

      int totalWordLength = 0;
      Map<String, Integer> wordFreq = new HashMap<>();
      for (String word : words) {
        word = word.toLowerCase().replaceAll("[^a-z]", "");
        totalWordLength += word.length();
        wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
      }

      String mostCommon = Collections.max(wordFreq.entrySet(), Map.Entry.comparingByValue()).getKey();
      double avgWordLength = (double) totalWordLength / wordCount;

      System.out.println(WORD_COUNT + words.length);
      System.out.println(SENTENCE_COUNT + sentences.length);
      System.out.println(AVG_WORD_LENGTH_COUNT + avgWordLength);
      System.out.println(MOST_COMMON_WORD + mostCommon);

    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
  }
}

public class LMBE2 {
  public static void main(String[] args) {
    TextAnalyzer analyzer = new TextAnalyzer();
    String filePath = "random_text.txt";
    analyzer.analyze(filePath);
  }
}
