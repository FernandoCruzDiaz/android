package com.fernando.prediccionhoras;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fernando.prediccionhoras.model.Weather.WeatherInfo;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance() {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final ImageView imageView = rootView.findViewById(R.id.imageTiempo2);
            final TextView textViewHumedad = rootView.findViewById(R.id.textHumedad2);
            final TextView textViewTemperatura = rootView.findViewById(R.id.textTemperatura2);
            final TextView textViewViento = rootView.findViewById(R.id.textVelocidadViento2);
            final TextView textViewEstado = rootView.findViewById(R.id.textEstadoCielo);



            final String lugar = "Sevilla";
            OpenWeatherApi openWeatherApi = ServiceGenerator.createService(OpenWeatherApi.class);
            Call<WeatherInfo> call = openWeatherApi.getWeatherByCity(lugar);
            call.enqueue(new Callback<WeatherInfo>() {
                @Override
                public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                    if (response.isSuccessful()) {
                        WeatherInfo weatherInfo = response.body();
                        TextView nombre = getActivity().findViewById(R.id.textNombreCiudad);

                        String velocidad = String.valueOf(weatherInfo.getWind().getSpeed());
                        int temp = weatherInfo.getMain().getTemp().intValue();
                        String temperatura = String.valueOf(temp);
                        String humedad = String.valueOf(weatherInfo.getMain().getHumidity());
                        String url = "http://openweathermap.org/img/w/";

                        textViewHumedad.setText("Humedad: " +humedad + "%");
                        textViewTemperatura.setText(temperatura+ "º");
                        textViewViento.setText("Velocidad aire: " +velocidad + " m/s");
                        textViewEstado.setText(weatherInfo.getWeather().get(0).getDescription());
                        nombre.setText(weatherInfo.getName());

                        Picasso.with(getContext()).load(url + weatherInfo.getWeather().get(0).getIcon() + ".png").resize(300, 300).into(imageView);
                    } else {
                        Toast.makeText(getContext(), "Fallo", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<WeatherInfo> call, Throwable t) {
                    Toast.makeText(getContext(), "Fallo", Toast.LENGTH_SHORT).show();
                    Log.e("Retrofit", t.toString());
                }


            });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = PlaceholderFragment.newInstance();

            switch (position){
                case 0: fragment = PlaceholderFragment.newInstance();
                    break;
                case 1: fragment = PredicHorasFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
