package com.ocs.ccell;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ocs.ccell.connect.RetrofitClientInstance;
import com.ocs.ccell.model.PackageBalanceRemaining;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreen extends AppCompatActivity {

    int voiceAmount, smsAmount, dataAmount,price,duration;
    int voiceRemaining,smsRemaining,dataRemaining;
    int voiceUsage,dataUsage,smsUsage;
    TextView textViewMsisdn,textViewPackageName,textViewDuraiton;
    String packageName;
    private Button buttonQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        textViewMsisdn = findViewById(R.id.textViewPhoneNumber);
        textViewPackageName = findViewById(R.id.textViewPackageName);
        textViewDuraiton = findViewById(R.id.textViewDuraiton);

        Intent intent = getIntent();
        String edtMsisdn = intent.getStringExtra("msisdn");
        textViewMsisdn.setText(edtMsisdn.startsWith("0") ? edtMsisdn : 0+edtMsisdn);

        Call<PackageBalanceRemaining> packageBalanceRemainingCall = RetrofitClientInstance.getBalanceRemaining().getPackageBalanceRemaining(edtMsisdn);
        packageBalanceRemainingCall.enqueue(new Callback<PackageBalanceRemaining>() {
            @Override
            public void onResponse(Call<PackageBalanceRemaining> call, Response<PackageBalanceRemaining> response) {
                if(response.isSuccessful()){
                    PackageBalanceRemaining packageBalanceRemaining = response.body();
                    dataRemaining = packageBalanceRemaining.getData();
                    voiceRemaining = packageBalanceRemaining.getVoice();
                    smsRemaining = packageBalanceRemaining.getSms();
                    price = packageBalanceRemaining.getPrice();
                    dataAmount = packageBalanceRemaining.getPack().getData();
                    voiceAmount = packageBalanceRemaining.getPack().getVoice();
                    smsAmount = packageBalanceRemaining.getPack().getSms();
                    packageName = packageBalanceRemaining.getPack().getName();
                    duration = packageBalanceRemaining.getPack().getDuration();

                    String edtduraiton = String.valueOf(duration);
                    textViewDuraiton.setText("Kalan gün sayısı: "+edtduraiton);
                    textViewPackageName.setText(packageName);
                    dataUsage = dataAmount -(dataRemaining);
                    voiceUsage = voiceAmount -voiceRemaining;
                    smsUsage = smsAmount -smsRemaining;

                                PieChart pieChartData = (PieChart) findViewById(R.id.piechartData);
                                pieChartData.addPieSlice(new PieModel("Kalan Mobil Veri",dataRemaining, Color.parseColor("#00ff5f")));
                                pieChartData.addPieSlice(new PieModel("Kullanılan Mobil Veri",dataUsage,Color.parseColor("#ff0000")));
                                pieChartData.startAnimation();

                                PieChart pieChartVoice = (PieChart) findViewById(R.id.piechartVoice);
                                pieChartVoice.addPieSlice(new PieModel("Kalan Dakika",voiceRemaining,Color.parseColor("#00ff5f")));
                                pieChartVoice.addPieSlice(new PieModel("Kullanılan Dakika",voiceUsage,Color.parseColor("#ff0000")));
                                pieChartVoice.startAnimation();

                                PieChart pieChartSms = (PieChart) findViewById(R.id.piechartSms);
                                pieChartSms.addPieSlice(new PieModel("Kalan SMS",smsRemaining,Color.parseColor("#00ff5f")));
                                pieChartSms.addPieSlice(new PieModel("Kullanılan SMS",smsUsage,Color.parseColor("#ff0000")));
                                pieChartSms.startAnimation();

                                buttonQuit = (Button) findViewById(R.id.buttonQuit);
                                buttonQuit.setOnClickListener(view -> {
                                    Intent nextPage = new Intent(MainScreen.this, LoginScreen.class);
                                    startActivity(nextPage);
                                });
                            }
            }
            @Override
            public void onFailure(Call<PackageBalanceRemaining> call, Throwable t) {
                Toast.makeText(MainScreen.this,"Hata!  "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                System.out.println(t.getLocalizedMessage());
            }
        });
    }
}
