import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

class Employee {
  private final String id;
  private String firstName;
  private String lastName;
  private String position;
  private double salary;
  private final LocalDate hireDate;

  public Employee(String id, String firstName, String lastName,
                  String position, double salary, LocalDate hireDate) {
    this.id = Objects.requireNonNull(id, "id must not be null");
    this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    this.position = Objects.requireNonNull(position, "position must not be null");
    if (salary < 0) throw new IllegalArgumentException("salary must be non-negative");
    this.salary = salary;
    this.hireDate = Objects.requireNonNull(hireDate, "hireDate must not be null");
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = Objects.requireNonNull(firstName);
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = Objects.requireNonNull(lastName);
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = Objects.requireNonNull(position);
  }

  public double getSalary() {
    return salary;
  }

  public void giveRaise(double percent) {
    if (percent < 0) throw new IllegalArgumentException("percent must be non-negative");
    this.salary += this.salary * percent / 100.0;
  }

  public LocalDate getHireDate() {
    return hireDate;
  }

  public int getYearsEmployed() {
    return Period.between(hireDate, LocalDate.now()).getYears();
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }
}

class Security extends Employee {
  private Integer badgeNumber;

  public Security(String id, String firstName, String lastName,
                  String position, double salary, LocalDate hireDate, Integer badgeNumber) {
    super(id, firstName, lastName, position, salary, hireDate);
    this.badgeNumber = badgeNumber;
  }

  public Integer getBadgeNumber() {
    return this.badgeNumber;
  }

  public void setBadgeNumber(Integer badgeNumber) {
    this.badgeNumber = Objects.requireNonNull(badgeNumber);
  }
}

public class LZCBE2 {
  public static void main(String[] args) {
    Employee dev = new Employee(
      "E001",
      "John",
      "Doe",
      "Software Developer",
      80000.00,
      LocalDate.of(2018, 1, 15)
    );

    System.out.println("Employee: " + dev.getFullName());
    System.out.println("Position: " + dev.getPosition());
    System.out.println("Years Employed: " + dev.getYearsEmployed());
    System.out.printf("Current Salary: $%.2f%n", dev.getSalary());

    dev.giveRaise(10);
    System.out.printf("After 10%% raise: $%.2f%n%n", dev.getSalary());

    Security guard = new Security(
      "S001",
      "Alice",
      "Smith",
      "Security Guard",
      45000.00,
      LocalDate.of(2020, 5, 20),
      12345
    );

    System.out.println("Security: " + guard.getFullName());
    System.out.println("Position: " + guard.getPosition());
    System.out.println("Years Employed: " + guard.getYearsEmployed());
    System.out.printf("Current Salary: $%.2f%n", guard.getSalary());
    System.out.println("Badge Number: " + guard.getBadgeNumber());

    guard.setBadgeNumber(67890);
    System.out.println("New Badge Number: " + guard.getBadgeNumber());
  }
}
