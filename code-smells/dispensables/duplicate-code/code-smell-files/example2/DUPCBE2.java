import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

enum Action {
  Tap, Scroll, Toggle;
}

class AnalyticEvent {
  private final Action action;
  private final String category;
  private final double value;

  private static final List<AnalyticEvent> db = new ArrayList<>();
  private static final StringBuilder logFile = new StringBuilder();

  public AnalyticEvent(Action action, String category, double value) {
    this.action = action;
    this.category = category;
    this.value = value;
  }

  public void captureAnalyticEvent(AnalyticEvent analyticEvent) {
    if (analyticEvent.value < 0) {
      System.err.println("Warning: Negative value for event");
    }

    String summary = String.format(
      "[CAPTURE] Action: %s, Category: %s, Value: %.2f",
      analyticEvent.action, analyticEvent.category, analyticEvent.value
    );

    db.add(analyticEvent);
    System.out.println(summary);
    System.out.println("Saved to DB. Total events in DB: " + db.size() + "\n");
  }

  public void logAnalyticEvent(AnalyticEvent analyticEvent) {
    if (analyticEvent.value < 0) {
      System.err.println("Warning: Negative value for event");
    }

    String summary = String.format(
      "[LOG] Action: %s, Category: %s, Value: %.2f",
      analyticEvent.action, analyticEvent.category, analyticEvent.value
    );

    String timestamp = LocalDateTime.now().toString();
    logFile.append(timestamp).append(" - ").append(summary).append("\n");
    System.out.println(summary);
    System.out.println("Appended to log.\n");
  }

  public static void printLogFile() {
    System.out.println("📝 Full Log File:");
    System.out.println(logFile.toString());
  }

  public static void printDbContents() {
    System.out.println("📦 Simulated DB Contents:");
    for (AnalyticEvent e : db) {
      System.out.printf("- %s | %s | %.2f\n", e.action, e.category, e.value);
    }
  }
}

class DUPCBE2 {
  public static void main(String[] args) {
    AnalyticEvent e1 = new AnalyticEvent(Action.Tap, "NavBar", 3.0);
    AnalyticEvent e2 = new AnalyticEvent(Action.Scroll, "HomeFeed", -1.5);
    AnalyticEvent e3 = new AnalyticEvent(Action.Toggle, "Settings", 0.0);

    e1.captureAnalyticEvent(e1);
    e2.logAnalyticEvent(e2);
    e3.captureAnalyticEvent(e3);
    e3.logAnalyticEvent(e3);

    AnalyticEvent.printDbContents();
    AnalyticEvent.printLogFile();
  }
}
