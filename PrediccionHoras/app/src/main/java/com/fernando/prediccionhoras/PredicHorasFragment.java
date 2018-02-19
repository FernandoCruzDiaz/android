package com.fernando.prediccionhoras;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fernando.prediccionhoras.model.Forecast.ForecastInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PredicHorasFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;

    MypredicHorasRecyclerViewAdapter adapter;
    ForecastInfo forecast;



    public static PredicHorasFragment newInstance() {
        PredicHorasFragment fragment = new PredicHorasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_predichoras_list, container, false);

        final Context context = view.getContext();
        final RecyclerView recyclerView = (RecyclerView) view;

        String lugar = "Seville";
        OpenWeatherApi openWeatherApi = ServiceGenerator.createService(OpenWeatherApi.class);
        Call<ForecastInfo> call = openWeatherApi.getWeatherByCityHour(lugar);
        call.enqueue(new Callback<ForecastInfo>() {
            @Override
            public void onResponse(Call<ForecastInfo> call, Response<ForecastInfo> response) {
                Log.i("prueba","Correcto");
                forecast = response.body();

                adapter = new MypredicHorasRecyclerViewAdapter(getActivity(), forecast.getList());

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ForecastInfo> call, Throwable t) {
                Log.i("pruebaerr","error");
            }
        });

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        return view;
    }

}
