import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

class EmployeeVariation {
  private UUID id;
  private String firstName;
  private String lastName;
  private String position;
  private double salary;
  private final LocalDate hireDate;

  public EmployeeVariation(UUID id,
                           String firstName,
                           String lastName,
                           String position,
                           double salary,
                           LocalDate hireDate) {
    this.id        = Objects.requireNonNull(id,        "id must not be null");
    this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
    this.lastName  = Objects.requireNonNull(lastName,  "lastName must not be null");
    this.position  = Objects.requireNonNull(position,  "position must not be null");
    if (salary < 0) throw new IllegalArgumentException("salary must be non-negative");
    this.salary    = salary;
    this.hireDate  = Objects.requireNonNull(hireDate,  "hireDate must not be null");
  }

  public UUID getId()            { return id; }
  public String getFirstName()     { return firstName; }
  public void   setFirstName(String fn) { this.firstName = Objects.requireNonNull(fn); }
  public String getLastName()      { return lastName; }
  public void   setLastName(String ln)  { this.lastName = Objects.requireNonNull(ln); }
  public String getPosition()      { return position; }
  public void   setPosition(String p)   { this.position = Objects.requireNonNull(p); }
  public double getSalary()        { return salary; }
  public void   giveRaise(double pct) {
    if (pct < 0) throw new IllegalArgumentException("percent must be non-negative");
    this.salary += this.salary * pct / 100.0;
  }
  public LocalDate getHireDate()   { return hireDate; }
  public int       getYearsEmployed() {
    return Period.between(hireDate, LocalDate.now()).getYears();
  }
  public String    getFullName()   { return firstName + " " + lastName; }
}

class SecurityEmployeeVariation extends EmployeeVariation {
  private Integer badgeNumber;

  public SecurityEmployeeVariation(UUID id,
                                   String firstName,
                                   String lastName,
                                   String position,
                                   double salary,
                                   LocalDate hireDate,
                                   Integer badgeNumber) {
    super(id, firstName, lastName, position, salary, hireDate);
    this.badgeNumber = Objects.requireNonNull(badgeNumber, "badgeNumber must not be null");
  }

  public Integer getBadgeNumber()        { return badgeNumber; }
  public void    setBadgeNumber(Integer bn) {
    this.badgeNumber = Objects.requireNonNull(bn);
  }
  public boolean hasBadge()              { return badgeNumber != null; }

  public void swipeBadge(String location) {
    if (!hasBadge()) throw new IllegalStateException("Cannot swipe badge: no badge assigned");
    System.out.println(getFullName()
      + " swiped badge #" + badgeNumber
      + " at " + location);
  }

  public void renewBadge(Integer newBadgeNumber) {
    if (!hasBadge()) throw new IllegalStateException("Cannot renew badge: no badge assigned");
    this.badgeNumber = Objects.requireNonNull(newBadgeNumber);
    System.out.println(getFullName()
      + " renewed badge: new badge #" + badgeNumber);
  }

  public String badgeInfo() {
    return hasBadge() ? "Badge #" + badgeNumber : "No badge assigned";
  }
}

class Manager extends SecurityEmployeeVariation {
  private final String department;
  private final List<EmployeeVariation> teamMembers;

  public Manager(UUID id,
                 String firstName,
                 String lastName,
                 String position,
                 double salary,
                 LocalDate hireDate,
                 Integer badgeNumber,
                 String department) {
    super(id, firstName, lastName, position, salary, hireDate, badgeNumber);
    this.department  = Objects.requireNonNull(department, "department must not be null");
    this.teamMembers = new ArrayList<>();
  }

  public String getDepartment() {
    return department;
  }

  public void addTeamMember(EmployeeVariation member) {
    Objects.requireNonNull(member, "member must not be null");
    teamMembers.add(member);
  }

  public boolean removeTeamMember(EmployeeVariation member) {
    return teamMembers.remove(member);
  }

  public int getTeamSize() {
    return teamMembers.size();
  }

  public void conductTeamMeeting(String agenda) {
    System.out.println("Manager " + getFullName()
      + " is holding a meeting for department ‘" + department
      + "’ with agenda: " + Objects.requireNonNull(agenda));
  }
}

public class LCGE2 {
  public static void main(String[] args) {
    EmployeeVariation emp = new EmployeeVariation(
      UUID.randomUUID(),
      "Alice",
      "Johnson",
      "Developer",
      80000,
      LocalDate.of(2020, 6, 15)
    );
    System.out.println("EmployeeVariation ID: " + emp.getId());
    System.out.println("Name: " + emp.getFullName());
    System.out.println("Position: " + emp.getPosition());
    System.out.println("Hire Date: " + emp.getHireDate());
    System.out.println("Years Employed: " + emp.getYearsEmployed());
    emp.giveRaise(10);
    System.out.println("New Salary: $" + emp.getSalary());
    emp.setFirstName("Alicia");
    emp.setLastName("Smith");
    emp.setPosition("Senior Developer");
    System.out.println("Updated Profile: "
      + emp.getFullName()
      + " – " + emp.getPosition());

    SecurityEmployeeVariation sec = new SecurityEmployeeVariation(
      UUID.randomUUID(),
      "Bob",
      "Brown",
      "Security Guard",
      50000,
      LocalDate.of(2019, 1, 10),
      05667);

    System.out.println("\nSecurity EmployeeVariation: " + sec.getFullName());
    System.out.println("Has badge? " + sec.hasBadge());
    System.out.println(sec.badgeInfo());
    sec.setBadgeNumber(1234);
    System.out.println("Assigned badge #: " + sec.getBadgeNumber());
    System.out.println("Has badge? " + sec.hasBadge());
    sec.swipeBadge("Gate A");
    sec.renewBadge(2345);
    System.out.println("Badge info: " + sec.badgeInfo());

    Manager mgr = new Manager(
      UUID.randomUUID(),
      "Carol",
      "White",
      "Engineering Manager",
      120000,
      LocalDate.of(2018, 3, 1),
      5678,
      "Engineering"
    );
    System.out.println("\nManager: " + mgr.getFullName());
    System.out.println("Department: " + mgr.getDepartment());
    System.out.println("Current Team Size: " + mgr.getTeamSize());

    EmployeeVariation team1 = new EmployeeVariation(
      UUID.randomUUID(),
      "David",
      "Lee",
      "Developer",
      75000,
      LocalDate.of(2021, 9, 10)
    );
    EmployeeVariation team2 = new EmployeeVariation(
      UUID.randomUUID(),
      "Eva",
      "Martinez",
      "Developer",
      78000,
      LocalDate.of(2022, 1, 5)
    );
    mgr.addTeamMember(team1);
    mgr.addTeamMember(team2);
    System.out.println("Team Size after additions: " + mgr.getTeamSize());

    mgr.conductTeamMeeting("Sprint Planning & Metrics");

    mgr.removeTeamMember(team1);
    System.out.println("Team Size after removal: " + mgr.getTeamSize());
  }
}
