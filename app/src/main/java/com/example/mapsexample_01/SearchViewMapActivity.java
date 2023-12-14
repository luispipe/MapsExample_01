package com.example.mapsexample_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class  SearchViewMapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap gmap;
    Marker marker;
    SearchView searchView;

    UiSettings settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view_map);
        searchView=findViewById(R.id.search);
        searchView.clearFocus();

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location= searchView.getQuery().toString();
                if(location == null){
                    Toast.makeText(getApplicationContext(),"Location not found",Toast.LENGTH_LONG).show();
                }else{
                    Geocoder geocoder= new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> addressList= geocoder.getFromLocationName(location,1);
                        if (addressList.size()>0){
                            LatLng latLng= new LatLng(addressList.get(0).getLatitude(),addressList.get(0).getLongitude());
                            if(marker != null){
                                marker.remove();
                            }
                            MarkerOptions markerOptions= new MarkerOptions().position(latLng).title(location);
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                            CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(latLng,5);
                            gmap.animateCamera(cameraUpdate);
                            marker= gmap.addMarker(markerOptions);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gmap= googleMap;
        settings= googleMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        settings.setMapToolbarEnabled(true);
        settings.setCompassEnabled(true);
    }
}