package sb.app.domain;

public class Address {
    
    public final String streetNumber, streetName, province, postalCode, country;

    public Address(String streetNumber, String streetName, String province, String postalCode, String country) {
        super();
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
    }
       

}
