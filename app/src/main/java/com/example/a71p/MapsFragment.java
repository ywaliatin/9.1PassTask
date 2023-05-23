package com.example.a71p;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private MapView mapView;
    private String location;
    private boolean mapReady = false;
    private Button addButton;

    private double latitude; // Instance variable for latitude
    private double longitude; // Instance variable for longitude
    private String location1; // Instance variable for location
    private double latitude1; // Instance variable for latitude
    private double longitude1; // Instance variable for longitude
    private double latitude2; // Instance variable for latitude
    private double longitude2; // Instance variable for longitude
    private String location2; // Instance variable for location

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.autocomplete_fragment1);
        mapFragment.getMapAsync(this);





        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapReady = true;

        latitude = -37.840935; // Example latitude
        longitude = 144.946457; // Example longitude
        location = "Melbourne";

        latitude1 = -37.918018; // Example latitude
        longitude1 = 145.035736; // Example longitude
        location1 = "Bentleigh";

        latitude2 = -37.9333333; // Example latitude
        longitude2 = 145.035; // Example longitude
        location2 = "Moorabin";

        addMarker(latitude, longitude, location);

        LatLng latLng = new LatLng(latitude, longitude);

        //addMarker(latitude, longitude, location);

        LatLng latLng1 = new LatLng(latitude1, longitude1);
        LatLng latLng2 = new LatLng(latitude2, longitude2);

        googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
        googleMap.addMarker(new MarkerOptions().position(latLng1).title(location));
        googleMap.addMarker(new MarkerOptions().position(latLng2).title(location));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Call the addMarker() method if latitude, longitude, and location are already set
        //if (latitude != 0 && longitude != 0 && location != null) {
          //  addMarker(latitude, longitude, location);
        //}
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mapReady) {
            updateMapWithLocation();
        }
    }

    private void updateMapWithLocation() {
        if (googleMap != null && location != null) {
            // Convert location to LatLng using Geocoder
            Geocoder geocoder = new Geocoder(requireContext());
            try {
                List<Address> addresses = geocoder.getFromLocationName(location, 1);
                if (addresses != null && addresses.size() > 0) {
                    Address address = addresses.get(0);
                    double latitude = address.getLatitude();
                    double longitude = address.getLongitude();

                    // Add marker to the map
                    LatLng latLng = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLocations(List<String> locations) {
        if (googleMap != null && locations != null) {
            for (String location : locations) {
                // Convert location to LatLng using Geocoder
                Geocoder geocoder = new Geocoder(requireContext());
                try {
                    List<Address> addresses = geocoder.getFromLocationName(location, 1);
                    if (addresses != null && addresses.size() > 0) {
                        Address address = addresses.get(0);
                        double latitude = address.getLatitude();
                        double longitude = address.getLongitude();

                        // Add marker to the map
                        LatLng latLng = new LatLng(latitude, longitude);
                        googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("MapsFragment", "Geocoder error", e); // This will print the exception in LogCat
                }
            }
        }
    }

    private void addMarker(double latitude, double longitude, String location) {
        if (googleMap != null) {
            LatLng latLng = new LatLng(latitude, longitude);
            googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }



}
