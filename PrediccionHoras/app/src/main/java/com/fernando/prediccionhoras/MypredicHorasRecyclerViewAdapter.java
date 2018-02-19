package com.fernando.prediccionhoras;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fernando.prediccionhoras.model.Forecast.ForecastInfo;
import com.fernando.prediccionhoras.model.Forecast.WeatherList;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MypredicHorasRecyclerViewAdapter extends RecyclerView.Adapter<MypredicHorasRecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private final List<WeatherList> mValues;

    public MypredicHorasRecyclerViewAdapter(Context context, List<WeatherList> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_predichoras, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        int tempMax = holder.mItem.getMain().getTempMax().intValue();
        int tempMin = holder.mItem.getMain().getTempMin().intValue();
        String temperaturaMaxima = String.valueOf(tempMax + "º");
        String temperaturaMinima = String.valueOf(tempMin + "º");
        String url = "http://openweathermap.org/img/w/";

        holder.tempMax.setText(temperaturaMaxima);
        holder.tempMin.setText(temperaturaMinima);
        holder.hora.setText(holder.mItem.getDtTxt());
        holder.estado.setText(holder.mItem.getWeather().get(0).getDescription());

        Picasso.with(ctx).load(url + holder.mItem.getWeather().get(0).getIcon() + ".png").resize(300, 300).into(holder.icono);
    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView tempMax, tempMin, hora, estado;
        public ImageView icono;
        public ForecastInfo forecastInfo;
        public WeatherList mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            icono = mView.findViewById(R.id.imageIconoHoras);
            tempMax = mView.findViewById(R.id.textTempMax);
            tempMin = mView.findViewById(R.id.textTempMin);
            estado = mView.findViewById(R.id.textViewEstado);
            hora = mView.findViewById(R.id.textHoras);
        }


    }
}