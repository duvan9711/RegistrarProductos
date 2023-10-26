package com.example.registrarproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private EditText name, quantity, prize;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nombrePro);
        quantity = findViewById(R.id.cantPro);
        prize = findViewById(R.id.precioPro);
        table = findViewById(R.id.table1);
        Button register = findViewById(R.id.button);
        Button close = findViewById(R.id.button1);

        ArrayList<Producto> canasta = new ArrayList<>();

        register.setOnClickListener(view -> {
            String nombre = name.getText().toString();
            String cantidad = quantity.getText().toString();
            String precio = prize.getText().toString();
            if (nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty()){
                Toast.makeText(getApplicationContext(), "Todos los campos se deben diligenciar", Toast.LENGTH_LONG).show();
            }else {
                int cantidadNew = Integer.parseInt(cantidad);
                int precioNew = Integer.parseInt(precio);
                Producto nuevo = new Producto(nombre, cantidadNew, precioNew);
                canasta.add(nuevo);
                total(canasta);
                llenarTable(nuevo);
            }
            clear();
        });
    }

    @SuppressLint("SetTextI18n")
    public void llenarTable(Producto producto){
        int valor = producto.getPrecio()*producto.getCantidad();
        TableRow fila = new TableRow(this);
        TextView cell1 = new TextView(this);
        cell1.setText(producto.getNombre());
        cell1.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        fila.addView(cell1);

        TextView cell2 = new TextView(this);
        cell2.setText(producto.getCantidad() + " ");
        cell2.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        fila.addView(cell2);

        TextView cell3 = new TextView(this);
        cell3.setText(producto.getPrecio() + " ");
        cell3.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        fila.addView(cell3);

        TextView cell4 = new TextView(this);
        cell4.setText(valor + " ");
        cell4.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
        fila.addView(cell4);

        table.addView(fila);

    }

    public void total (ArrayList<Producto>compra){
        int total = 0;
        for (Producto i:compra){
            total = i.getPrecio() * i.getCantidad();
        }
        System.out.println(total);
    }

    public void clear(){
        name.setText("");
        name.requestFocus();
        quantity.setText("");
        prize.setText("");
    }

}