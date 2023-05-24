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

import com.example.a71p.data.DatabaseHelper;
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
    private boolean mapReady = false;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment1);
        mapFragment.getMapAsync(this);
        databaseHelper = new DatabaseHelper(getActivity());
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapReady = true;
        displayItemLocations();
    }

    private void displayItemLocations() {
        if (googleMap != null && mapReady) {
            List<String> itemLocations = databaseHelper.getLocations();
            for (String location : itemLocations) {
                Geocoder geocoder = new Geocoder(requireContext());
                try {
                    List<Address> addresses = geocoder.getFromLocationName(location, 1);
                    if (addresses != null && addresses.size() > 0) {
                        Address address = addresses.get(0);
                        double latitude = address.getLatitude();
                        double longitude = address.getLongitude();
                        LatLng latLng = new LatLng(latitude, longitude);
                        googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("MapsFragment", "Geocoder error", e);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mapReady) {
            displayItemLocations();
        }
    }

    public void setLocations(List<String> locations) {
        if (googleMap != null && mapReady && locations != null) {
            for (String location : locations) {
                Geocoder geocoder = new Geocoder(requireContext());
                try {
                    List<Address> addresses = geocoder.getFromLocationName(location, 1);
                    if (addresses != null && addresses.size() > 0) {
                        Address address = addresses.get(0);
                        double latitude = address.getLatitude();
                        double longitude = address.getLongitude();
                        LatLng latLng = new LatLng(latitude, longitude);
                        googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        googleMap.getUiSettings().setZoomControlsEnabled(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("MapsFragment", "Geocoder error", e);
                }
            }
        }
    }
}
