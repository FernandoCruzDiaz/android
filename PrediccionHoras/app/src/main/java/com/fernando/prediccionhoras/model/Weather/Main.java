
package com.fernando.prediccionhoras.model.Weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private Long pressure;
    @SerializedName("humidity")
    @Expose
    private Long humidity;
    @SerializedName("temp_min")
    @Expose
    private Long tempMin;
    @SerializedName("temp_max")
    @Expose
    private Long tempMax;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Main() {
    }

    /**
     * 
     * @param humidity
     * @param pressure
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Main(Double temp, Long pressure, Long humidity, Long tempMin, Long tempMax) {
        super();
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Long getPressure() {
        return pressure;
    }

    public void setPressure(Long pressure) {
        this.pressure = pressure;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Long getTempMin() {
        return tempMin;
    }

    public void setTempMin(Long tempMin) {
        this.tempMin = tempMin;
    }

    public Long getTempMax() {
        return tempMax;
    }

    public void setTempMax(Long tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        if (temp != null ? !temp.equals(main.temp) : main.temp != null) return false;
        if (pressure != null ? !pressure.equals(main.pressure) : main.pressure != null)
            return false;
        if (humidity != null ? !humidity.equals(main.humidity) : main.humidity != null)
            return false;
        if (tempMin != null ? !tempMin.equals(main.tempMin) : main.tempMin != null) return false;
        return tempMax != null ? tempMax.equals(main.tempMax) : main.tempMax == null;
    }

    @Override
    public int hashCode() {
        int result = temp != null ? temp.hashCode() : 0;
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (tempMin != null ? tempMin.hashCode() : 0);
        result = 31 * result + (tempMax != null ? tempMax.hashCode() : 0);
        return result;
    }
}
