public class ContactInfo {
    private String address;
    private String phoneNumber;
    public ContactInfo(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return this.address;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    @Override
    public String toString() {
        return " Address: " + address + " Phone Number: " + phoneNumber;
    }
}
