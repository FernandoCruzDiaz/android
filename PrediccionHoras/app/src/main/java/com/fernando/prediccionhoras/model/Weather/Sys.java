
package com.fernando.prediccionhoras.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("type")
    @Expose
    private Long type;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private Long sunrise;
    @SerializedName("sunset")
    @Expose
    private Long sunset;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys() {
    }

    /**
     * 
     * @param message
     * @param id
     * @param sunset
     * @param sunrise
     * @param type
     * @param country
     */
    public Sys(Long type, Long id, Double message, String country, Long sunrise, Long sunset) {
        super();
        this.type = type;
        this.id = id;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        if (type != null ? !type.equals(sys.type) : sys.type != null) return false;
        if (id != null ? !id.equals(sys.id) : sys.id != null) return false;
        if (message != null ? !message.equals(sys.message) : sys.message != null) return false;
        if (country != null ? !country.equals(sys.country) : sys.country != null) return false;
        if (sunrise != null ? !sunrise.equals(sys.sunrise) : sys.sunrise != null) return false;
        return sunset != null ? sunset.equals(sys.sunset) : sys.sunset == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (sunrise != null ? sunrise.hashCode() : 0);
        result = 31 * result + (sunset != null ? sunset.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", message=" + message +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
