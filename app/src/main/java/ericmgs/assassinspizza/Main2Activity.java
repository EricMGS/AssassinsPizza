package ericmgs.assassinspizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity {
    ListView listaPedido;
    ArrayList<Item> arrayPedido = new ArrayList<Item>();
    ItemAdapter adaptador;
    static final int addItemCode = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Tela cheia
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        getSupportActionBar().hide();

        listaPedido = (ListView) findViewById(R.id.listaPedido);
        adaptador = new ItemAdapter(Main2Activity.this, arrayPedido);
        listaPedido.setAdapter(adaptador);
    }


    public void onClickAddItem(View v) {
        Intent it = new Intent(this, MainActivity.class);
        startActivityForResult(it, addItemCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == addItemCode)
            if (resultCode == RESULT_OK) {
                boolean inteira;
                if (data.getStringExtra("inteira") == "true")
                    inteira = true;
                else
                    inteira = false;
                String tamanho = data.getStringExtra("tamanho");
                String sabor1 = data.getStringExtra("sabor1");
                String sabor2 = data.getStringExtra("sabor2");
                Item item = new Item(inteira, tamanho, sabor1, sabor2);
                arrayPedido.add(item);
                adaptador.notifyDataSetChanged();
            }
    }
}

