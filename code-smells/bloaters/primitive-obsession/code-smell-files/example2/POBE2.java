import java.util.HashSet;
import java.util.Set;

class LoginModel {
  private String email;
  private Set<String> registeredEmails = new HashSet<>();
  private final String[] tutorialModals = new String[3];

  public LoginModel(String email) {
    this.email = email;
  }

  public void setTutorialModals() {
    tutorialModals[0] = "Greeting Message";
    tutorialModals[1] = "4";
    tutorialModals[2] = "System Settings";
  }

  public void registerEmail(String email) {
    registeredEmails.add(email);
    System.out.println("Registered email: " + email);
  }

  public boolean isRegistered(String email) {
    return registeredEmails.contains(email);
  }
}

public class POBE2 {
  public static void main(String[] args) {
    LoginModel user = new LoginModel("reira@example.com");
    user.registerEmail("reira@example.com");
    user.setTutorialModals();
    String checkEmail = "reira@example.com";

    if (user.isRegistered(checkEmail)) {
      System.out.println(checkEmail + " is successfully registered.");
    } else {
      System.out.println(checkEmail + " is NOT registered.");
    }
  }
}