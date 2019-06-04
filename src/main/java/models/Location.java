package models;

import java.util.Objects;

public class Location {
    private int id;
    private String city;
    private String streetAdress;

    public Location() {
    }

    public Location(String city, String streetAdress) {
        this.city = city;
        this.streetAdress = streetAdress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAdress() {
        return streetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        this.streetAdress = streetAdress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(city, location.city) &&
                Objects.equals(streetAdress, location.streetAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, streetAdress);
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", streetAdress='" + streetAdress + '\'' +
                '}';
    }
}
