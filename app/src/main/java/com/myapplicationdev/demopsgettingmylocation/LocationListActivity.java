package com.myapplicationdev.demopsgettingmylocation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LocationListActivity extends AppCompatActivity {

    Button btnRefresh;
    TextView tvRecords;
    ListView lvLocations;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        tvRecords = findViewById(R.id.tvRecords);
        lvLocations = findViewById(R.id.lvLocations);
        btnRefresh = findViewById(R.id.btnRefresh);


        String myFolderLocation = getFilesDir().getAbsolutePath() + "/Folder";
        File myTargetFile = new File(myFolderLocation, "location.txt");

        if (myTargetFile.exists()) {
            StringBuilder data = new StringBuilder();

            try {

                FileReader reader = new FileReader(myTargetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();

                while (line != null) {
                    data.append(line).append("\n");
                    line = br.readLine();
                }

                String[] array = data.toString().split("\n");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, array);

                lvLocations.setAdapter(adapter);
                tvRecords.setText("Number of Records: " + array.length);

                br.close();
                reader.close();
            } catch (Exception e) {

                Toast.makeText(LocationListActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            Log.d("Content", data.toString());
        }

        btnRefresh.setOnClickListener(v -> {
            String myFolderLocation1 = getFilesDir().getAbsolutePath() + "/Folder";
            File myTargetFile1 = new File(myFolderLocation1, "location.txt");

            if (myTargetFile1.exists()) {
                StringBuilder data = new StringBuilder();

                try {
                    FileReader reader = new FileReader(myTargetFile1);
                    BufferedReader br = new BufferedReader(reader);

                    String line = br.readLine();

                    while (line != null) {
                        data.append(line).append("\n");
                        line = br.readLine();
                    }

                    // Split the data after it has been added by adding a new line.
                    String[] array = data.toString().split("\n");
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, array);

                    lvLocations.setAdapter(adapter);
                    tvRecords.setText("Number of Records: " + array.length);

                    br.close();
                    reader.close();

                } catch (Exception e) {
                    Toast.makeText(LocationListActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                Log.d("Content", data.toString());
            }
        });
    }
}
