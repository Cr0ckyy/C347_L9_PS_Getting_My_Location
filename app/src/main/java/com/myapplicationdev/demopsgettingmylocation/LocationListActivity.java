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
    TextView tvCount;
    ListView lvLoc;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        tvCount = findViewById(R.id.tvRecords);
        lvLoc = findViewById(R.id.lvLocations);
        btnRefresh = findViewById(R.id.btnRefresh);


        String folderLocation_I = getFilesDir().getAbsolutePath() + "/Folder";
        File targetFile = new File(folderLocation_I, "location.txt");
        if (targetFile.exists()) {
            StringBuilder data = new StringBuilder();
            try {
                FileReader reader = new FileReader(targetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                while (line != null) {
                    data.append(line).append("\n");
                    line = br.readLine();
                }

                String[] array = data.toString().split("\n");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, array);
                lvLoc.setAdapter(adapter);

                tvCount.setText("Num of Records: " + array.length);
                br.close();
                reader.close();
            } catch (Exception e) {

                Toast.makeText(LocationListActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            Log.d("Content", data.toString());
        }

        btnRefresh.setOnClickListener(v -> {
            String folderLocation_I1 = getFilesDir().getAbsolutePath() + "/Folder";
            File targetFile1 = new File(folderLocation_I1, "location.txt");

            if (targetFile1.exists()) {
                StringBuilder data = new StringBuilder();

                try {
                    FileReader reader = new FileReader(targetFile1);
                    BufferedReader br = new BufferedReader(reader);

                    String line = br.readLine();
                    while (line != null) {
                        data.append(line).append("\n");
                        line = br.readLine();
                    }

                    String[] array = data.toString().split("\n");
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, android.R.id.text1, array);
                    lvLoc.setAdapter(adapter);

                    tvCount.setText("Number of Records: " + array.length);
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
