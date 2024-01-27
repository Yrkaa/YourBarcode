package com.example.yourbarcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    //Объявление переменных для эл. разметки
    Button createBarcodeBtn;
    EditText barcodeTextEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Инициализация переменных для эл. разметки
        createBarcodeBtn = findViewById(R.id.create_barcode_button);
        barcodeTextEt = findViewById(R.id.barcode_text_et);

        //Вызов диалога при нажатии на кнопку
        createBarcodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(barcodeTextEt.getText().length() == 0)
                    Toast.makeText(MainActivity.this, "Введите текст!", Toast.LENGTH_LONG).show();
                else
                    createBarcodeDialog(barcodeTextEt.getText().toString());
            }
        });

    }

    private void createBarcodeDialog(String text){
        //Создание диалога и назначение экрана разметки
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.barcode_fragment_layout);

        //Инициализация переменных для эл. разметки диалога
        ImageView barcodeIv = dialog.findViewById(R.id.barcode_iv);
        ProgressBar progressBar = dialog.findViewById(R.id.progress_bar);

        //Получение доступа к параметрам экрана пользователя
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();

        //Сохранение яркости пользователя
        float userBrightness = layoutParams.screenBrightness;

        //Включение яркости на максимум
        layoutParams.screenBrightness = 1;
        //Обновление параметров экрана
        getWindow().setAttributes(layoutParams);

        //Подгрузка изображения и уборка прогресс бара
        Picasso.get().load("https://bwipjs-api.metafloor.com/?bcid=code128&text="+text+"&alttext=Your+barcode+app").into(barcodeIv);
        progressBar.setVisibility(View.INVISIBLE);

        //Возвращение пользовательской яркости экрана при закрытии диалога
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                layoutParams.screenBrightness = userBrightness;
                getWindow().setAttributes(layoutParams);
            }
        });

        //Показ диалога
        dialog.show();


    }
}