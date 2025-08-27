import java.util.ArrayList;
import java.util.List;

enum ServiceTypeVariation {
  MOBILE,
  INTERNET,
  CABLE_TV
}

class CustomerVariation {
  private String name;
  private String email;
  private List<ServiceTypeVariation> services = new ArrayList<>();

  public CustomerVariation(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public CustomerVariation() {}

  public String getName() { return name; }
  public String getEmail() { return email; }

  public String getProfile() {
    return "Customer Name: " + getName() + ", Email: " + getEmail();
  }

  public void registerService(ServiceTypeVariation service) {
    services.add(service);
  }

  public List<ServiceTypeVariation> getServices() {
    return services;
  }
}

class NullCustomerVariation extends CustomerVariation {
  public NullCustomerVariation() {
    super();
  }

  public boolean isNull() {
    return true;
  }
}

class VerizonUserManagementVariation {
  private List<CustomerVariation> mobileCustomers = new ArrayList<>();
  private List<CustomerVariation> internetCustomers = new ArrayList<>();
  private List<CustomerVariation> cableTVCustomers = new ArrayList<>();

  public void registerMobileCustomer(CustomerVariation customer) {
    if (customer instanceof NullCustomerVariation) {
      mobileCustomers.add(new NullCustomerVariation());
    } else {
      mobileCustomers.add(customer);
      customer.registerService(ServiceTypeVariation.MOBILE);
    }
  }

  public void registerInternetCustomer(CustomerVariation customer) {
    if (customer instanceof NullCustomerVariation) {
      internetCustomers.add(new NullCustomerVariation());
    } else {
      internetCustomers.add(customer);
      customer.registerService(ServiceTypeVariation.INTERNET);
    }
  }

  public void registerCableTVCustomer(CustomerVariation customer) {
    if (customer instanceof NullCustomerVariation) {
      cableTVCustomers.add(new NullCustomerVariation());
    } else {
      cableTVCustomers.add(customer);
      customer.registerService(ServiceTypeVariation.CABLE_TV);
    }
  }

  public void printAllCustomers() {
    System.out.println("=== MOBILE CUSTOMERS ===");
    for (CustomerVariation c : mobileCustomers) {
      System.out.println(c.getProfile());
    }

    System.out.println("=== INTERNET CUSTOMERS ===");
    for (CustomerVariation c : internetCustomers) {
      System.out.println(c.getProfile());
    }

    System.out.println("=== CABLE TV CUSTOMERS ===");
    if (cableTVCustomers != null) {
      for (CustomerVariation c : cableTVCustomers) {
        System.out.println(c.getProfile());
      }
    } else {
      System.out.println("No Cable TV customers.");
    }
  }
}

public class TFGE2 {
  public static void main(String[] args) {
    VerizonUserManagementVariation manager = new VerizonUserManagementVariation();

    CustomerVariation alice = new CustomerVariation("Alice", "alice@example.com");
    CustomerVariation bob = new CustomerVariation("Bob", "bob@example.com");
    NullCustomerVariation nullCustomer = new NullCustomerVariation();

    manager.registerMobileCustomer(alice);
    manager.registerInternetCustomer(bob);
    manager.registerCableTVCustomer(nullCustomer);

    manager.printAllCustomers();

    System.out.println("\nAlice's Registered Services:");
    for (ServiceTypeVariation service : alice.getServices()) {
      System.out.println("- " + service);
    }

    System.out.println("\nBob's Profile:");
    System.out.println(bob.getProfile());

    if (nullCustomer.isNull()) {
      System.out.println("\nNullCustomer detected: This is a placeholder customer.");
    }
  }
}
