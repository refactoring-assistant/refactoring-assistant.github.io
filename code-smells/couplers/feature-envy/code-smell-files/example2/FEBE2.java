import java.time.LocalDateTime;
import java.util.*;

class RemoteController {
  private final String controllerId;
  private final Map<String, Command> commandBindings;
  private final List<String> pressHistory;
  private boolean enabled;

  public RemoteController(String controllerId) {
    this.controllerId = controllerId;
    this.commandBindings = new HashMap<>();
    this.pressHistory = new ArrayList<>();
    this.enabled = true;
  }

  public String getControllerId() {
    return controllerId;
  }

  public Map<String, Command> getCommandBindings() {
    return Collections.unmodifiableMap(commandBindings);
  }

  public List<String> getPressHistory() {
    return Collections.unmodifiableList(pressHistory);
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setCommand(String buttonName, Command command) {
    commandBindings.put(buttonName, command);
  }

  public void pressButton(String buttonName) {
    if (!enabled) {
      System.out.println("RemoteController '" + controllerId + "' is disabled.");
      return;
    }
    pressHistory.add(buttonName);
    Command cmd = commandBindings.get(buttonName);
    if (cmd != null) {
      System.out.println("Button '" + buttonName + "' pressed on '" + controllerId + "'");
      cmd.execute();
    } else {
      System.out.println("No command assigned to button '" + buttonName + "'");
    }
  }

  static class Command {
    private final Runnable action;

    public Command(Runnable action) {
      this.action = action;
    }

    public void execute() {
      action.run();
    }
  }
}

class SignalProcessor {
  private final Map<String, Long> pressCounts;
  private final Set<String> configuredButtons;
  private int totalPresses = 0;
  private LocalDateTime lastProcessedAt;

  public SignalProcessor(RemoteController remoteController) {
    this.pressCounts = new HashMap<>();
    this.configuredButtons = new HashSet<>();
  }

  public void process(RemoteController remote) {
    configuredButtons.clear();
    configuredButtons.addAll(remote.getCommandBindings().keySet());
    pressCounts.clear();
    totalPresses = 0;

    for (String btn : remote.getPressHistory()) {
      pressCounts.put(btn, pressCounts.getOrDefault(btn, 0L) + 1);
      totalPresses++;
    }

    lastProcessedAt = LocalDateTime.now();
  }

  public List<String> getInactiveButtons() {
    List<String> inactive = new ArrayList<>();
    for (String btn : configuredButtons) {
      if (!pressCounts.containsKey(btn)) {
        inactive.add(btn);
      }
    }
    return inactive;
  }

  public void reset() {
    pressCounts.clear();
    configuredButtons.clear();
    totalPresses = 0;
    lastProcessedAt = null;
    System.out.println("SignalProcessor metrics have been reset.");
  }

  public void generateUsageReport(RemoteController myRemoteController) {
    System.out.println("-- Usage Report for Controller '" + myRemoteController.getControllerId() + "' --");
    System.out.println("Enabled: " + myRemoteController.isEnabled());

    Set<String> buttons = myRemoteController.getCommandBindings().keySet();
    List<String> history = myRemoteController.getPressHistory();

    System.out.println("Total buttons configured: " + buttons.size());
    System.out.println("Total button presses: " + history.size());

    Map<String, Long> counts = new HashMap<>();
    for (String b : history) {
      counts.put(b, counts.getOrDefault(b, 0L) + 1);
    }

    System.out.println("Presses by button:");
    buttons.forEach(b -> System.out.println("  " + b + ": " + counts.getOrDefault(b, 0L)));

    List<String> unused = new ArrayList<>();
    for (String b : buttons) {
      if (!counts.containsKey(b)) unused.add(b);
    }
    System.out.println("Unused buttons: " + (unused.isEmpty() ? "none" : unused));
  }
}

enum Button {
  LIGHTS_ON("LightsOn"),
  LIGHTS_OFF("LightsOff"),
  TEMP_UP("TempUp");

  private final String label;

  Button(String label) {
    this.label = label;
  }

  public String label() {
    return label;
  }
}

public class FEBE2 {
  public static void main(String[] args) {
    RemoteController remote = new RemoteController("LivingRoomRemote");

    remote.setCommand(Button.LIGHTS_ON.label(),
      new RemoteController.Command(() -> System.out.println("Lights turned on!")));
    remote.setCommand(Button.LIGHTS_OFF.label(),
      new RemoteController.Command(() -> System.out.println("Lights turned off!")));
    remote.setCommand(Button.TEMP_UP.label(),
      new RemoteController.Command(() -> System.out.println("Temperature increased!")));

    remote.pressButton(Button.LIGHTS_ON.label());
    remote.pressButton(Button.TEMP_UP.label());
    remote.pressButton("Unknown");

    remote.setEnabled(false);
    remote.pressButton(Button.LIGHTS_OFF.label());
    remote.setEnabled(true);
    remote.pressButton(Button.LIGHTS_OFF.label());

    SignalProcessor processor = new SignalProcessor(remote);
    processor.process(remote);
    processor.generateUsageReport(remote);
    System.out.println("Inactive buttons: " + processor.getInactiveButtons());
    processor.reset();
  }
}
