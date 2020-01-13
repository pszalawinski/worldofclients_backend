package worldofclients.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotNull
    private String companyName;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String streetNumber;
    @NotNull
    private String zipCode;
    private String lat;
    private String lng;


    public Client() {
    }

    public Client(@NotNull String companyName, @NotNull String country, @NotNull String city, @NotNull String street, @NotNull String streetNumber, @NotNull String zipCode, String lat, String lng) {
        this.companyName = companyName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.lat = lat;
        this.lng = lng;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String setCompanyName(String companyName) {
        this.companyName = companyName;
        return companyName;
    }

    public String getCountry() {
        return country;
    }

    public String setCountry(String country) {
        this.country = country;
        return country;
    }

    public String getCity() {
        return city;
    }

    public String setCity(String city) {
        this.city = city;
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String setStreet(String street) {
        this.street = street;
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;

        return streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return zipCode;
    }

    public String getLat() {
        return lat;
    }

    public String setLat(String lat) {
        this.lat = lat;
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String setLng(String lng) {
        this.lng = lng;
        return lng;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
