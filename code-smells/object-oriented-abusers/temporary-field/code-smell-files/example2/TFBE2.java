import java.util.ArrayList;
import java.util.List;

enum ServiceType {
  MOBILE,
  INTERNET,
  CABLE_TV
}

class Customer {
  private String name;
  private String email;
  private List<ServiceType> services = new ArrayList<>();

  public Customer(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public void registerService(ServiceType service) {
    services.add(service);
  }

  public String getName() { return name; }
  public String getEmail() { return email; }

  public String getProfile() {
    return "Customer Name: " + getName() + ", Email: " + getEmail();
  }
}

class VerizonUserManagement {
  private List<Customer> mobileCustomers = new ArrayList<>();
  private List<Customer> internetCustomers = new ArrayList<>();
  private List<Customer> cableTVCustomers;

  public void registerMobileCustomer(Customer customer) {
    if (customer != null) {
      mobileCustomers.add(customer);
      customer.registerService(ServiceType.MOBILE);
    }
  }

  public void registerInternetCustomer(Customer customer) {
    if (customer != null) {
      internetCustomers.add(customer);
      customer.registerService(ServiceType.INTERNET);
    }
  }

  public void registerCableTVCustomer(Customer customer) {
    if (customer != null) {
      cableTVCustomers = new ArrayList<>();
      cableTVCustomers.add(customer);
      customer.registerService(ServiceType.CABLE_TV);
    }
  }

  public void printAllCustomers() {
    System.out.println("=== MOBILE CUSTOMERS ===");
    for (Customer c : mobileCustomers) {
      System.out.println(c.getProfile());
    }

    System.out.println("=== INTERNET CUSTOMERS ===");
    for (Customer c : internetCustomers) {
      System.out.println(c.getProfile());
    }

    System.out.println("=== CABLE TV CUSTOMERS ===");
    if (cableTVCustomers != null) {
      for (Customer c : cableTVCustomers) {
        System.out.println(c.getProfile());
      }
    } else {
      System.out.println("No Cable TV customers.");
    }
  }
}

public class TFBE2 {
  public static void main(String[] args) {
    VerizonUserManagement manager = new VerizonUserManagement();
    Customer alice = new Customer("Alice", "alice@example.com");
    Customer bob = new Customer("Bob", "bob@example.com");
    Customer charlie = new Customer("Charlie", "charlie@example.com");

    manager.registerMobileCustomer(alice);
    manager.registerInternetCustomer(bob);
    manager.registerCableTVCustomer(charlie);

    manager.printAllCustomers();
  }
}
