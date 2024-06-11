package pl.rCieplinski.ecommerce.catalog.sales.reservation;

public class CustomerDetails {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;

    public CustomerDetails(String customerId, String firstname, String lastname, String email) {

        this.customerId = customerId;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
