package com.example.app_tarea1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainBienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bienvenida);
        EditText txtMensaje = findViewById(R.id.txt1Mensaje);
        Intent intent=getIntent();

        txtMensaje.setText("Cédula: "+intent.getStringExtra("cedula")+", "+"Nombres: "+
                intent.getStringExtra("nombres")+", "+"Fecha de nacimiento: "+
                intent.getStringExtra("fechaNac")+", "+"Ciudad: "+
                intent.getStringExtra("ciudad")+", "+"Correo electrónico: "+
                intent.getStringExtra("correoElect")+", "+"Teléfono: "+
                intent.getStringExtra("telefono"));
    }
}