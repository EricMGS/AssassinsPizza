package ericmgs.assassinspizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dh = new DatabaseHelper(this);
    String tableName = "tbCardapio";
    String columnName = "sabor";
    List<String> listaSabores = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tela cheia
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();


        listaSabores.addAll(dh.getList(tableName));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaSabores);
        Spinner sabor1 = findViewById(R.id.spnSaborMetade1);
        Spinner sabor2 = findViewById(R.id.spnSaborMetade2);
        sabor1.setAdapter(dataAdapter);
        sabor2.setAdapter(dataAdapter);

    }

    public void meiaPizzaClicked(View view) {
        Spinner saborMetade2 = findViewById(R.id.spnSaborMetade2);
        switch(view.getId()) {
            case R.id.radioInteira:
                saborMetade2.setVisibility(View.INVISIBLE);
                break;
            case R.id.radioMeia:
                saborMetade2.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void onClickOk(View v) {
        Intent it = new Intent();
        RadioButton rdInteira = findViewById(R.id.radioInteira);
        String inteira;
        if (rdInteira.isChecked())
            inteira = "true";
        else
            inteira = "false";
        Spinner spnSabor1 = findViewById(R.id.spnSaborMetade1);
        String sabor1 = spnSabor1.getSelectedItem().toString();
        Spinner spnSabor2 = findViewById(R.id.spnSaborMetade2);
        String sabor2 = spnSabor2.getSelectedItem().toString();
        RadioGroup rdGroupTam = findViewById(R.id.rdTamanho);
        RadioButton rdTamSelected = findViewById(rdGroupTam.getCheckedRadioButtonId());
        String tamanho = rdTamSelected.getText().toString();

        it.putExtra("inteira", inteira);
        it.putExtra("tamanho", tamanho);
        it.putExtra("sabor1", sabor1);
        it.putExtra("sabor2", sabor2);
        setResult(RESULT_OK, it);
        finish();
    }

}

