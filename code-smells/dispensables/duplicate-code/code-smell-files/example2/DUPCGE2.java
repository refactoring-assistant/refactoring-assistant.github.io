import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

enum ActionVariation {
  Tap, Scroll, Toggle;
}

class AnalyticEventVariation {
  private final Action action;
  private final String category;
  private final double value;

  private static final List<AnalyticEventVariation> db = new ArrayList<>();
  private static final StringBuilder logFile = new StringBuilder();

  public AnalyticEventVariation(Action action, String category, double value) {
    this.action = action;
    this.category = category;
    this.value = value;
  }

  public void captureAnalyticEvent(AnalyticEventVariation analyticEvent) {
    String summary = validateAndSummarize(analyticEvent, "CAPTURE");

    db.add(analyticEvent);
    System.out.println(summary);
    System.out.println("Saved to DB. Total events in DB: " + db.size() + "\n");
  }

  public void logAnalyticEvent(AnalyticEventVariation analyticEvent) {
    String summary = validateAndSummarize(analyticEvent, "LOG");

    String timestamp = LocalDateTime.now().toString();
    logFile.append(timestamp).append(" - ").append(summary).append("\n");
    System.out.println(summary);
    System.out.println("Appended to log.\n");
  }

  private String validateAndSummarize(AnalyticEventVariation event, String contextLabel) {
    if (event.value < 0) {
      System.err.println("Warning: Negative value for event");
    }

    return String.format("[%s] Action: %s, Category: %s, Value: %.2f",
      contextLabel, event.action, event.category, event.value);
  }

  public static void printLogFile() {
    System.out.println("📝 Full Log File:");
    System.out.println(logFile.toString());
  }

  public static void printDbContents() {
    System.out.println("📦 Simulated DB Contents:");
    for (AnalyticEventVariation e : db) {
      System.out.printf("- %s | %s | %.2f\n", e.action, e.category, e.value);
    }
  }
}

class DUPCGE2 {
  public static void main(String[] args) {
    AnalyticEventVariation e1 = new AnalyticEventVariation(Action.Tap, "NavBar", 3.0);
    AnalyticEventVariation e2 = new AnalyticEventVariation(Action.Scroll, "HomeFeed", -1.5);
    AnalyticEventVariation e3 = new AnalyticEventVariation(Action.Toggle, "Settings", 0.0);

    e1.captureAnalyticEvent(e1);
    e2.logAnalyticEvent(e2);
    e3.captureAnalyticEvent(e3);
    e3.logAnalyticEvent(e3);

    AnalyticEventVariation.printDbContents();
    AnalyticEventVariation.printLogFile();
  }
}