package com.example.app_tarea1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText cedula,nombres,fechaNac,ciudad,correoElect,telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cedula = findViewById(R.id.etxtCedula);
        nombres = findViewById(R.id.etxtNombres);
        fechaNac = findViewById(R.id.etd1FechaNac);
        ciudad = findViewById(R.id.etxtCiudad);

        correoElect = findViewById(R.id.emtxt1Correo);
        telefono = findViewById(R.id.ptxt1Telf);
    }

    public void limpiarCampos(View view) {
        cedula.setText("");
        nombres.setText("");
        fechaNac.setText("");
        ciudad.setText("");
        correoElect.setText("");
        telefono.setText("");
    }

    public void registrarDatos(View view){
        String mensaje="";

        mensaje=validarCamposFormulario();
        if(mensaje.isEmpty()){
            Bundle bundle = new Bundle();
            bundle.putString("cedula",cedula.getText().toString());
            bundle.putString("nombres",nombres.getText().toString());
            bundle.putString("fechaNac",fechaNac.getText().toString());
            bundle.putString("ciudad",ciudad.getText().toString());
            bundle.putString("correoElect",correoElect.getText().toString());
            bundle.putString("telefono",telefono.getText().toString());

            Intent intent = new Intent(MainActivity.this, MainBienvenida.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
        }
    }

    public String validarCamposFormulario(){
        String mensaje="";
        Pattern pattern = null;

        if(!cedula.getText().toString().isEmpty()){
            if(cedula.getText().toString().length()==10){
                pattern = Pattern.compile("\\d+");
                if(!pattern.matcher(cedula.getText().toString()).matches()){
                    mensaje+="Campo cédula solo permite números, ";
                }
            }else{
                mensaje+="Campo cédula con longitud no válida, ";
            }
        }else{
            mensaje+="Campo cédula vacío, ";
        }

        if(!nombres.getText().toString().isEmpty()){
            if(nombres.getText().toString().length()<=500){
                pattern = Pattern.compile(".*[A-Z].*");
                if(!pattern.matcher(nombres.getText().toString()).matches()){
                    mensaje+="Campo nombres solo permite letras mayúsculas, ";
                }
            }else{
                mensaje+="Campo nombres con longitud no válida, ";
            }
        }else{
            mensaje+="Campo nombres vacío, ";
        }

        if(!ciudad.getText().toString().isEmpty()){
            if(ciudad.getText().toString().length()<=200){
                pattern = Pattern.compile(".*[A-Z].*");
                if(!pattern.matcher(ciudad.getText().toString()).matches()){
                    mensaje+="Campo ciudad solo permite letras mayúsculas, ";
                }
            }else{
                mensaje+="Campo ciudad con longitud no válida, ";
            }
        }else{
            mensaje+="Campo ciudad vacío, ";
        }

        if(!validarFecha(fechaNac)){
            mensaje+="Formato de fecha no válido";
        }

        if(!correoElect.getText().toString().isEmpty()){
                pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
                if(!pattern.matcher(correoElect.getText().toString()).matches()){
                    mensaje+="Campo correo no tiene un formato válido, ";
                }
        }else{
            mensaje+="Campo correo vacío, ";
        }

        if(!telefono.getText().toString().isEmpty()){
            if(telefono.getText().toString().length()==10){
                pattern = Pattern.compile("\\d+");
                if(!pattern.matcher(telefono.getText().toString()).matches()){
                    mensaje+="Campo teléfono tiene un formato no válido, ";
                }
            }else{
                mensaje+="Campo teléfono con longitud no válida, ";
            }
        }else{
            mensaje+="Campo teléfono vacío, ";
        }

        return mensaje;
    }

    public static boolean validarFecha(EditText editText) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (editText.getText().toString().isEmpty()) return false;

        // pattern doesn't match so returning false
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", text)) {
            editText.setError("Valor ingresado no válido");
            return false;
        }

        return true;
    }

}