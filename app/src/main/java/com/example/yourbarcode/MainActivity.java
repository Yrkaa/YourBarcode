package com.example.yourbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    Button createBarcodeBtn;
    EditText barcodeTextEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBarcodeBtn = findViewById(R.id.create_barcode_button);
        barcodeTextEt = findViewById(R.id.barcode_text_et);

    }

    private void createBarcodeDialog(){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.barcode_fragment_layout);
        dialog.show();
    }
}