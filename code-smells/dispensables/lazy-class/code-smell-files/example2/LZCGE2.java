import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

class EmployeeVariation {
  private final String id;
  private String firstName;
  private String lastName;
  private String position;
  private double salary;
  private final LocalDate hireDate;
  private Integer badgeNumber;

  public EmployeeVariation(String id, String firstName, String lastName,
                  String position, double salary, LocalDate hireDate) {
    this.id = Objects.requireNonNull(id, "id must not be null");
    this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    this.position = Objects.requireNonNull(position, "position must not be null");
    if (salary < 0) throw new IllegalArgumentException("salary must be non-negative");
    this.salary = salary;
    this.hireDate = Objects.requireNonNull(hireDate, "hireDate must not be null");
  }

  public EmployeeVariation(String id, String firstName, String lastName,
                  String position, double salary, LocalDate hireDate, Integer badgeNumber) {
    this.id = Objects.requireNonNull(id, "id must not be null");
    this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    this.position = Objects.requireNonNull(position, "position must not be null");
    if (salary < 0) throw new IllegalArgumentException("salary must be non-negative");
    this.salary = salary;
    this.hireDate = Objects.requireNonNull(hireDate, "hireDate must not be null");
    this.badgeNumber = Objects.requireNonNull(badgeNumber, "badgeNumber must not be null");
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

  public Integer getBadgeNumber() {
    return this.badgeNumber;
  }

  public void setBadgeNumber(Integer badgeNumber) {
    this.badgeNumber = Objects.requireNonNull(badgeNumber);
  }
}

public class LZCGE2 {
  public static void main(String[] args) {
    EmployeeVariation emp1 = new EmployeeVariation(
      "E101",
      "Alice",
      "Smith",
      "Developer",
      90000.00,
      LocalDate.of(2019, 3, 1)
    );

    System.out.println("ID: " + emp1.getId());
    System.out.println("Name: " + emp1.getFullName());
    System.out.println("Position: " + emp1.getPosition());
    System.out.println("Salary: $" + emp1.getSalary());
    System.out.println("Hire Date: " + emp1.getHireDate());
    System.out.println("Years Employed: " + emp1.getYearsEmployed());

    emp1.setFirstName("Alicia");
    emp1.setLastName("Johnson");
    System.out.println("Updated Name: " + emp1.getFullName());

    emp1.setPosition("Senior Developer");
    System.out.println("Updated Position: " + emp1.getPosition());

    emp1.giveRaise(10);  // 10% raise
    System.out.println("After 10% Raise Salary: $" + emp1.getSalary());

    emp1.setBadgeNumber(12345);
    System.out.println("Badge Number: " + emp1.getBadgeNumber());

    System.out.println("------------------------------------------------");

    EmployeeVariation emp2 = new EmployeeVariation(
      "E102",
      "Bob",
      "Brown",
      "Manager",
      120000.00,
      LocalDate.of(2017, 7, 15),
      67890
    );

    System.out.println("ID: " + emp2.getId());
    System.out.println("Name: " + emp2.getFullName());
    System.out.println("Position: " + emp2.getPosition());
    System.out.println("Salary: $" + emp2.getSalary());
    System.out.println("Years Employed: " + emp2.getYearsEmployed());
    System.out.println("Badge Number: " + emp2.getBadgeNumber());

    emp2.setBadgeNumber(54321);
    System.out.println("Updated Badge Number: " + emp2.getBadgeNumber());
  }
}
