package com.example.findlocations;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.findlocations.Model.Coordinates;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity
{
    LocationManager locationManager;
    myLocationListener locationListener;
    private static final int REQUEST_CODE=0;
    List<Address> addresses;
    Geocoder geocoder;
    String Myaddress;
    Coordinates coordinates;
    ProgressDialog progress;
    String address,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        geocoder = new Geocoder(this, Locale.getDefault());
        locationListener = new myLocationListener();
        coordinates = new Coordinates();
    }


    public void showLocation(View view) // when Click get Location button
    {
        showProgressDialog();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION},REQUEST_CODE);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    private void showProgressDialog() // showProgressDialog  while getting Location
    {
        progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Loading Location");
        progress.setMessage("please Waiting ...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    public void sendMessage(View view) // when Click Send Message Button
    {
        if((coordinates.getLongitude()!=0.0) && (coordinates.getLatitude()!=0.0))
        {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + Uri.encode("01063576984")));
            intent.putExtra("sms_body", "This is the Location "+Myaddress);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Please Detect Location First", Toast.LENGTH_SHORT).show();
        }
    }

    public void showMap(View view)  // when Click ShowMap Button
    {
        if(coordinates.getLongitude()==0.0 || coordinates.getLatitude()==0.0)
        {
            Toast.makeText(this, "Need to get Location Before View Map", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent=new Intent(MainActivity.this,MapsActivity.class);
            intent.putExtra("long",coordinates.getLongitude());
            intent.putExtra("lat",coordinates.getLatitude());
            intent.putExtra("address",Myaddress);
            startActivity(intent);
        }
    }

    private String getAddreesFormCoordinates(double longitude, double latitude)
    {
        try {
             addresses = geocoder.getFromLocation(longitude, latitude, 1);
             address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
             city = addresses.get(0).getLocality();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return city+address;
    }

    private class myLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            progress.dismiss();
            Toast.makeText(MainActivity.this, "Latitude "+location.getLatitude()+"\nLongitude"+location.getLongitude()+"\nAltitude"+location.getAltitude(), Toast.LENGTH_SHORT).show();
            locationManager.removeUpdates(locationListener);
            if(location.getLongitude()!=0.0&&location.getLatitude()!=0.0)
            {
                coordinates.setLongitude(location.getLongitude());
                coordinates.setLatitude(location.getLatitude());
                Myaddress=getAddreesFormCoordinates(coordinates.getLongitude(),coordinates.getLatitude());
            }
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        @Override
        public void onProviderEnabled(String provider) {

        }
        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case REQUEST_CODE:
                {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, REQUEST_CODE);

                } else {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
                break;
            }
        }
    }
}
