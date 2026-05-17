public class Address {
    private final String street;
    private final String city;
    private final String zipCode;
    private final String country;

    public Address(String street, String city, String zipCode, String country) {
        this.street  = street;
        this.city    = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getStreet() {
        return street;
    }
    public String getZipCode() {
        return zipCode;
    }
}