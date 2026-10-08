package model;

public class Customer implements Cloneable {

    private int customerId;
    private String name;
    private String email;
    private Address address;

    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static class Address {

        private String city;
        private String state;

        public Address(String city, String state) {
            this.city = city;
            this.state = state;
        }

        @Override
        public String toString() {
            return city + ", " + state;
        }
    }

    @Override
    public String toString() {

        return "Customer ID: " + customerId
                + ", Name: " + name
                + ", Email: " + email
                + ", Address: " + address;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer other = (Customer) obj;

        return customerId == other.customerId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(customerId);
    }

    @Override
    public Customer clone() {

        try {

            Customer copy = (Customer) super.clone();

            if (address != null) {
                copy.address =
                        new Address(address.city, address.state);
            }

            return copy;

        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}