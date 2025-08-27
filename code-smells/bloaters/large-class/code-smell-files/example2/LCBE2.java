import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class Employee {
  private final UUID id;
  private String firstName, lastName, position;
  private double salary;
  private final LocalDate hireDate;
  private Integer badgeNumber;
  private String department;
  private List<EmployeeVariation> teamMembers;

  public Employee(UUID id,
                  String firstName,
                  String lastName,
                  String position,
                  double salary,
                  LocalDate hireDate) {
    this.id = Objects.requireNonNull(id, "id must not be null");
    this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    this.position = Objects.requireNonNull(position, "position must not be null");
    if (salary < 0) throw new IllegalArgumentException("salary must be non-negative");
    this.salary = salary;
    this.hireDate = Objects.requireNonNull(hireDate, "hireDate must not be null");
  }

  public Employee(UUID id,
                  String firstName,
                  String lastName,
                  String position,
                  double salary,
                  LocalDate hireDate,
                  Integer badgeNumber) {
    this.id = Objects.requireNonNull(id, "id must not be null");
    this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    this.position = Objects.requireNonNull(position, "position must not be null");
    if (salary < 0) throw new IllegalArgumentException("salary must be non-negative");
    this.salary = salary;
    this.hireDate = Objects.requireNonNull(hireDate, "hireDate must not be null");
    this.badgeNumber = Objects.requireNonNull(badgeNumber, "badgeNumber must not be null");
  }

  public Employee(UUID id,
                  String firstName,
                  String lastName,
                  String position,
                  double salary,
                  LocalDate hireDate,
                  Integer badgeNumber,
                  String department) {
    this(id, firstName, lastName, position, salary, hireDate, badgeNumber);
    this.department   = Objects.requireNonNull(department,   "department must not be null");
    this.teamMembers  = new ArrayList<>();
  }

  public UUID getId() {
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

  public boolean hasBadge() {
    return badgeNumber != null;
  }

  public void swipeBadge(String location) {
    if (!hasBadge()) {
      throw new IllegalStateException("Cannot swipe badge: no badge assigned");
    }
    System.out.println(getFullName() + " swiped badge #" + badgeNumber + " at " + location);
  }

  public void renewBadge(Integer newBadgeNumber) {
    if (!hasBadge()) {
      throw new IllegalStateException("Cannot renew badge: no existing badge assigned");
    }
    this.badgeNumber = Objects.requireNonNull(newBadgeNumber, "newBadgeNumber must not be null");
    System.out.println(getFullName() + " renewed badge: new badge #" + badgeNumber);
  }

  public String badgeInfo() {
    return hasBadge() ? "Badge #" + badgeNumber : "No badge assigned";
  }

  public boolean isManager() {
    return department != null;
  }

  public String getDepartment() {
    if (!isManager()) {
      throw new IllegalStateException("Cannot get department: not a manager");
    }
    return department;
  }

  public void addTeamMember(EmployeeVariation member) {
    if (!isManager()) {
      throw new IllegalStateException("Cannot add team member: not a manager");
    }
    Objects.requireNonNull(member, "member must not be null");
    teamMembers.add(member);
  }

  public boolean removeTeamMember(EmployeeVariation member) {
    if (!isManager()) {
      throw new IllegalStateException("Cannot remove team member: not a manager");
    }
    return teamMembers.remove(member);
  }

  public int getTeamSize() {
    if (!isManager()) {
      throw new IllegalStateException("Cannot get team size: not a manager");
    }
    return teamMembers.size();
  }

  public void conductTeamMeeting(String agenda) {
    if (!isManager()) {
      throw new IllegalStateException("Cannot conduct meeting: not a manager");
    }
    System.out.println("Manager " + getFullName()
      + " is holding a meeting for department ‘" + department
      + "’ with agenda: " + Objects.requireNonNull(agenda));
  }
}

public class LCBE2 {
  public static void main(String[] args) {
    Employee emp1 = new Employee(
      UUID.randomUUID(),
      "Alice",
      "Johnson",
      "Developer",
      80_000,
      LocalDate.of(2020, 6, 15)
    );
    System.out.println(emp1.getFullName() + " hired on " + emp1.getHireDate());
    System.out.println("Years employed: " + emp1.getYearsEmployed());
    System.out.println("Current salary: $" + emp1.getSalary());

    emp1.giveRaise(10);
    System.out.println("After 10% raise: $" + emp1.getSalary());

    emp1.setFirstName("Alicia");
    emp1.setLastName("Smith");
    emp1.setPosition("Senior Developer");
    System.out.println("Updated profile: " + emp1.getFullName() + " – " + emp1.getPosition());

    System.out.println("Badge info before assignment: " + emp1.badgeInfo());
    emp1.setBadgeNumber(1111);
    System.out.println("Badge info after assignment: " + emp1.badgeInfo());
    emp1.swipeBadge("Main Entrance");
    emp1.renewBadge(2222);
    System.out.println("Badge info after renewal: " + emp1.badgeInfo());

    Employee mgr = new Employee(
      UUID.randomUUID(),
      "Bob",
      "Williams",
      "Engineering Manager",
      120_000,
      LocalDate.of(2018, 3, 1),
      5000,
      "Engineering"
    );
    System.out.println("\nIs " + mgr.getFullName() + " a manager? " + mgr.isManager());
    System.out.println(mgr.getFullName() + " heads the " + mgr.getDepartment() + " department.");

    EmployeeVariation e2 = new EmployeeVariation(UUID.randomUUID(), "Carol", "Nguyen", "Developer", 75_000, LocalDate.of(2021, 9, 10));
    EmployeeVariation e3 = new EmployeeVariation(UUID.randomUUID(), "David", "Lee", "Developer", 78_000, LocalDate.of(2022, 1, 5));

    mgr.addTeamMember(e2);
    mgr.addTeamMember(e3);
    System.out.println(mgr.getFullName() + " now has " + mgr.getTeamSize() + " direct reports.");

    mgr.conductTeamMeeting("Q3 Roadmap and Metrics");

    mgr.removeTeamMember(e3);
    System.out.println("After removal, team size: " + mgr.getTeamSize());
  }
}
