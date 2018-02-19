package com.fernando.prediccionhoras;


import com.fernando.prediccionhoras.model.Forecast.ForecastInfo;
import com.fernando.prediccionhoras.model.Weather.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fernando on 13/02/2018.
 */

public interface OpenWeatherApi {

    @GET("weather")
    Call<WeatherInfo> getWeatherByCity(@Query("q") String city);

    @GET("forecast")
    Call<ForecastInfo> getWeatherByCityHour(@Query("q") String city);


}
