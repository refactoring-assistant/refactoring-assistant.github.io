import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalTime;

class AttendanceManagerVariation {
  private final Map<Integer, EmployeeVariation> employeesById;
  private LocalDateTime lastAccessTime;

  public AttendanceManagerVariation(List<EmployeeVariation> employees) {
    this.employeesById = employees.stream()
      .collect(Collectors.toMap(EmployeeVariation::getId, Function.identity()));
  }

  public ScheduleVariation getSchedule(int employeeId, LocalDate date) {
    updateAccessTime();
    EmployeeVariation emp = employeesById.get(employeeId);
    if (emp == null) {
      throw new IllegalArgumentException("No employee with ID " + employeeId);
    }
    return emp.getSchedule(date);
  }

  public boolean isEmployeeOnShift(int employeeId, LocalDate date) {
    updateAccessTime();
    return getSchedule(employeeId, date).isOnShift();
  }

  public boolean isEmployeeOnShift(int employeeId, LocalDate date, java.time.LocalDateTime asOf) {
    updateAccessTime();
    return getSchedule(employeeId, date).isOnShift(asOf);
  }

  public LocalDateTime getLastAccessTime() {
    return lastAccessTime;
  }

  private void updateAccessTime() {
    this.lastAccessTime = LocalDateTime.now();
  }
}

class EmployeeVariation {
  private final int id;
  private final String name;
  private final Map<LocalDate, ScheduleVariation> schedules;

  public EmployeeVariation(int id, String name, Map<LocalDate, ScheduleVariation> schedules) {
    this.id = id;
    this.name = name;
    this.schedules = schedules;
  }

  public int getId() {
    return id;
  }

  ScheduleVariation getSchedule(LocalDate date) {
    return schedules.getOrDefault(date, ScheduleVariation.offDay(date));
  }
}

class ScheduleVariation {
  private final LocalDateTime start;
  private final LocalDateTime end;

  private ScheduleVariation(LocalDateTime start, LocalDateTime end) {
    this.start = start;
    this.end   = end;
  }

  public static ScheduleVariation forShift(LocalDate date, LocalTime startTime, LocalTime endTime) {
    return new ScheduleVariation(
      LocalDateTime.of(date, startTime),
      LocalDateTime.of(date, endTime)
    );
  }

  public static ScheduleVariation offDay(LocalDate date) {
    LocalDateTime dt = date.atStartOfDay();
    return new ScheduleVariation(dt, dt);
  }

  protected boolean isOnShift(LocalDateTime asOf) {
    return !asOf.isBefore(start) && !asOf.isAfter(end);
  }

  protected boolean isOnShift() {
    return isOnShift(LocalDateTime.now());
  }
}

public class MCGE2 {
  public static void main(String[] args) {
    LocalDate today = LocalDate.now();

    ScheduleVariation aliceShift = ScheduleVariation.forShift(today,
      LocalTime.of(9,0),
      LocalTime.of(17,0));
    EmployeeVariation alice = new EmployeeVariation(101, "Alice",
      Map.of(today, aliceShift));

    EmployeeVariation bob = new EmployeeVariation(102, "Bob", Map.of());

    AttendanceManagerVariation manager =
      new AttendanceManagerVariation(Arrays.asList(alice, bob));

    boolean aliceWorking = manager.isEmployeeOnShift(101, today);
    System.out.println("Alice on shift? " + aliceWorking);

    boolean bobWorking = manager.isEmployeeOnShift(102, today);
    System.out.println("Bob on shift? " + bobWorking);
    System.out.println("Last accessed: " + manager.getLastAccessTime());
  }
}
