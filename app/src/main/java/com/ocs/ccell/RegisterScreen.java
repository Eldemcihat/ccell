package com.ocs.ccell;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import com.ocs.ccell.connect.RetrofitClientInstance;
import com.ocs.ccell.model.RegisterRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterScreen extends AppCompatActivity {
    private String msisdn;
    private String email;
    private String name;
    private Integer packageId;
    private String password;
    private String surname;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);


        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextSecondName = findViewById(R.id.editTextSecondName);
        EditText editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextRegisterPassword = findViewById(R.id.editTextRegisterPassword);
        spinner = findViewById(R.id.spinnerPackagePick);
        //listPackage();
        Button buttonSignIn = findViewById(R.id.buttonKaydol);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msisdn = editTextPhoneNumber.getText().toString();
                email = editTextEmail.getText().toString();
                name = editTextName.getText().toString();
                password = editTextRegisterPassword.getText().toString();
                surname = editTextSecondName.getText().toString();
                packageId = 3;/*(int) spinner.getSelectedItemId();*/

                JsonObject packageObject = new JsonObject();
                packageObject.addProperty("id", packageId);

                Map<String, Object> map = new HashMap<>();
                map.put("msisdn", msisdn);
                map.put("email", email);
                map.put("name", name);
                map.put("password", password);
                map.put("surname", surname);
                map.put("pack", packageObject); // packageId içeren nesneyi ekleyin



                Call<RegisterRequest> registerRequestCall = RetrofitClientInstance.getRegisterService()
                        .register(map);
                registerRequestCall.enqueue(new Callback<RegisterRequest>() {
                    @Override
                    public void onResponse(Call<RegisterRequest> call, Response<RegisterRequest> response) {
                        if (response.code() == 201) {
                            Toast.makeText(RegisterScreen.this, "Kayıt Başarılı!", Toast.LENGTH_LONG).show();
                            // İleri bir süre sonra LoginScreen'e yönlendirme işlemi
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
                                }
                            }, 700);
                        } else {
                            Toast.makeText(RegisterScreen.this, "Kayıt başarısız! Hata kodu: " + response.code(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterRequest> call, Throwable t) {
                        Toast.makeText(RegisterScreen.this,"Hata! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        Button buttonAlreadyRegister;
        buttonAlreadyRegister = (Button) findViewById(R.id.buttonZatenUye);
        buttonAlreadyRegister.setOnClickListener(view -> {
            Intent nextPage = new Intent(RegisterScreen.this, LoginScreen.class);
            startActivity(nextPage);
        });
    }
    /*private void listPackage() {
        Spinner spinnerPackagePick = findViewById(R.id.spinnerPackagePick);
        spinnerPackagePick.setVisibility(View.INVISIBLE);

        Call<List<PackageRequest>> call = RetrofitClientInstance.getPackage().getPackageNameId();
        call.enqueue(new Callback<List<PackageRequest>>() {
            @Override
            public void onResponse(Call<List<PackageRequest>> call, Response<List<PackageRequest>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<PackageRequest> packageList = response.body();
                    if (!packageList.isEmpty()) {
                        List<String> packages = new ArrayList<>();
                        for (PackageRequest packageRequest : packageList) {
                            if (packageRequest.getPackageName() != null) {
                                packages.add(packageRequest.getPackageName());
                            }
                        }

                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(RegisterScreen.this, android.R.layout.simple_spinner_item, packages);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerPackagePick.setAdapter(null); // Clear the previous adapter
                        spinnerPackagePick.setAdapter(spinnerArrayAdapter);

                        spinnerPackagePick.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(RegisterScreen.this, "Hata! Paketler alınamadı", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterScreen.this, "Hata! Yanıt alınamadı", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PackageRequest>> call, Throwable t) {
                Toast.makeText(RegisterScreen.this, "Hata! " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }*/


}


