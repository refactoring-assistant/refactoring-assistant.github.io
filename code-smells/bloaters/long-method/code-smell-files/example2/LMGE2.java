import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

class TextAnalyzerVariation {
  private final String WORD_COUNT = "Word count: ";
  private final String SENTENCE_COUNT = "Sentence count: ";
  private final String AVG_WORD_LENGTH_COUNT = "Average word length: ";
  private final String MOST_COMMON_WORD = "Most common word: ";

  public void analyze(String filePath) {
    String content = readFile(filePath);
    String[] words = extractWords(content);
    String[] sentences = extractSentences(content);
    Map<String, Integer> freqMap = computeWordFrequencies(words);
    printStats(words, sentences, freqMap);
  }

  private String readFile(String filePath) {
    StringBuilder text = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        text.append(line).append(" ");
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
    return text.toString().trim();
  }

  private String[] extractWords(String content) {
    return content.split("\\s+");
  }

  private String[] extractSentences(String content) {
    return content.split("[.!?]");
  }

  private Map<String, Integer> computeWordFrequencies(String[] words) {
    Map<String, Integer> freqMap = new HashMap<>();
    for (String word : words) {
      word = word.toLowerCase().replaceAll("[^a-z]", "");
      freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
    }
    return freqMap;
  }

  private void printStats(String[] words, String[] sentences, Map<String, Integer> freqMap) {
    int totalWordLength = 0;
    Map<String, Integer> wordFreq = new HashMap<>();
    for (String word : words) {
      word = word.toLowerCase().replaceAll("[^a-z]", "");
      totalWordLength += word.length();
      wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
    }

    double avgWordLength = (double) totalWordLength / words.length;
    String mostCommon = Collections.max(freqMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    System.out.println(WORD_COUNT + words.length);
    System.out.println(SENTENCE_COUNT + sentences.length);
    System.out.println(AVG_WORD_LENGTH_COUNT + avgWordLength);
    System.out.println(MOST_COMMON_WORD + mostCommon);
  }
}

public class LMGE2 {
  public static void main(String[] args) {
    TextAnalyzer analyzer = new TextAnalyzer();
    String filePath = "random_text.txt";
    analyzer.analyze(filePath);
  }
}
