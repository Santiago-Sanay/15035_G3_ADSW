package com.alex.ultim2.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.alex.ultim2.R;
import com.alex.ultim2.models.IGoogleSheets;
import com.alex.ultim2.utils.BaseActivity;
import com.alex.ultim2.utils.Common;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterScreen extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    EditText etName, etSurname, etAge,txtLatitud,txtLongitud;
    GoogleMap mMap;
    AppCompatButton btnRegister;

    int lastId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        etName = findViewById(R.id.et_name);
        etSurname = findViewById(R.id.et_surname);
        etAge = findViewById(R.id.et_age);

        txtLatitud=findViewById(R.id.txtLatitud);
        txtLongitud=findViewById(R.id.txtLongitud);

        btnRegister = findViewById(R.id.btn_register);

        lastId = getIntent().getIntExtra("count", 0);

        btnRegister.setOnClickListener(v -> registerPerson());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void registerPerson() {
        ProgressDialog progressDialog = ProgressDialog.show(this,
                "Registrando nueva persona",
                "Espere por favor",
                true,
                false);

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String age = etAge.getText().toString();
        String latitud = txtLatitud.getText().toString();
        String longitud = txtLongitud.getText().toString();

        AsyncTask.execute(() -> {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://script.google.com/macros/s/AKfycbwBGbO3XILGPba-CvFOND9uY_i5j0lxwhD_w8Ng-l6xLvk2jPFjEyjMqykiZlv6TqUn/")
                        .build();

                IGoogleSheets iGoogleSheets = retrofit.create(IGoogleSheets.class);

                int id = lastId + 1;
                //int id = 10;

                String jsonRequest = "{\n" +
                        "    \"spreadsheet_id\": \"" + Common.GOOGLE_SHEET_ID + "\",\n" +
                        "    \"sheet\": \"" + Common.SHEET_NAME + "\",\n" +
                        "    \"rows\": [\n" +
                        "        [\n" +
                        "            \"" + id + "\",\n" +
                        "            \"" + name + "\",\n" +
                        "            \"" + surname + "\",\n" +
                        "            \"" + age + "\",\n" +
                        "            \"" + latitud+ "\",\n" +
                        "            \"" + longitud+ "\"\n" +
                        "        ]\n" +
                        "    ]\n" +
                        "}";

                Call<String> call = iGoogleSheets.getStringRequestBody(jsonRequest);

                Response<String> response = call.execute();
                int code = response.code();

                progressDialog.dismiss();
                if (code == 200) {
                    startActivity(new Intent(RegisterScreen.this, ConsultScreen.class)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(mexico).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText(String.valueOf(latLng.latitude));
        txtLongitud.setText(String.valueOf(latLng.longitude));

        mMap.clear();
        LatLng mexico = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(mexico).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng mexico = new LatLng(19.8077463,-99.4077038);
        mMap.addMarker(new MarkerOptions().position(mexico).title("México"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mexico));
    }
}