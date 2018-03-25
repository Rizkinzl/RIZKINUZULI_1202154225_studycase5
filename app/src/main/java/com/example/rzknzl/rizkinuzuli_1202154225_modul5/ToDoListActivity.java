package com.example.rzknzl.rizkinuzuli_1202154225_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity {

    //deklarasi komponen yang akan digunakan
    Database dtbase;
    RecyclerView Recyview;
    Adapter adapter;
    ArrayList<AddData> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        setTitle("To Do List");

        //refferencing recyclerview yang ada pada layout
        Recyview = findViewById(R.id.recview);
        datalist = new ArrayList<>();
        dtbase = new Database(this);
        dtbase.readdata(datalist);

        //menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //membuat adapter baru
        adapter = new Adapter(this, datalist, color);

        //menghindari perubahan ukuran yang tidak perlu ketika menambahkan / hapus item pada recycler view
        Recyview.setHasFixedSize(true);

        //menampilkan layoutnya linier
        Recyview.setLayoutManager(new LinearLayoutManager(this));

        //inisiasi adapter untuk recycler view
        Recyview.setAdapter(adapter);

        //menjalankan method hapus data pada list to do
        hapusgeser();
    }

    //membuat method untuk menghapus item pada to do list
    public void hapusgeser() {

        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);

                //apabila item di swipe ke arah kiri
                if (direction == ItemTouchHelper.LEFT) {

                    //remove item yang dipilih dengan mengenali todonya sebagai primary key
                    if (dtbase.removedata(current.getTodo())) {

                        //menghapus data
                        adapter.deleteData(position);

                        //membuat snack bar dan pemberitahuan bahwa item sudah terhapus dengan durasi 1 sekon
                        Snackbar.make(findViewById(R.id.coor), "Data Deleted", 1000).show();
                    }
                }
            }
        };

        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(Recyview);
    }

    //ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //mendapatkan id dari item yang
        int id = item.getItemId();

        //apabila item yang dipilih adalah setting
        if (id == R.id.action_settings) {
            Intent setting = new Intent(ToDoListActivity.this, SettingActivity.class);
            startActivity(setting);
            finish();
        }
        return true;
    }

    //method yang akan dijalankan ketika tombol add di klik
    public void add(View view) {
        Intent addtodo = new Intent(ToDoListActivity.this, AddToDoActivity.class);
        startActivity(addtodo);
    }
}