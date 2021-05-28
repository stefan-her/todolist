package be.stefan.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import be.stefan.todolist.adapters.ToDoListAdapter;
import be.stefan.todolist.models.ItemToDo;

public class MainActivity extends AppCompatActivity {

    public static final int CODE_ACTIVITE = 1;
    private ArrayList<ItemToDo> listToDo;
    private FloatingActionButton bt_add;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listToDo = new ArrayList<>();

        buildActionBar();
        try { buildRecyclerView();
        } catch (ParseException e) {  e.printStackTrace(); }

        bt_add = findViewById(R.id.bt_add);
        bt_add.setOnClickListener(v -> { addToDo(); });
    }

    private void buildActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void buildRecyclerView() throws ParseException {

//        listToDo.add(new ItemToDo("A faire 1", 1, false, "2020-09-05", getApplicationContext()));
//        listToDo.add(new ItemToDo("A faire 2", 3, true, "2020-10-21", getApplicationContext()));
//        listToDo.add(new ItemToDo("A faire 3", 2, false, "2020-11-19", getApplicationContext()));
//        listToDo.add(new ItemToDo("A faire 4", 2, false, "2020-12-01", getApplicationContext()));
//        listToDo.add(new ItemToDo("A faire 5", 3, true, "2021-02-22", getApplicationContext()));
//        listToDo.add(new ItemToDo("A faire 6", 1, false, "2021-05-27", getApplicationContext()));

        TextView tv_empty = findViewById(R.id.empty_view);
        RecyclerView rv_toDo = findViewById(R.id.rv_todolist);
        rv_toDo.setHasFixedSize(false);

        ToDoListAdapter adapter = new ToDoListAdapter(listToDo, getApplicationContext());

        rv_toDo.setAdapter(adapter);


//        if (listToDo.isEmpty()) {
//            rv_toDo.setVisibility(View.GONE);
//            tv_empty.setVisibility(View.VISIBLE);
//        }
//        else {
//            rv_toDo.setVisibility(View.VISIBLE);
//            tv_empty.setVisibility(View.GONE);
//        }


    }

    private void addToDo() {
        Log.d("Log ---->","Add to do");
        Intent i = new Intent(getApplicationContext(), ActivityAdd.class);
        startActivityForResult(i, CODE_ACTIVITE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void insertTodo(String v, int prior) throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(ItemToDo.PATTERNDATEIN);
        listToDo.add(new ItemToDo(v, prior +1, false, formatter.format(date), getApplicationContext()));
        Toast.makeText(getApplicationContext(), R.string.toaster_add, Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.hasExtra(ActivityAdd.NEWTODO) && data.hasExtra(ActivityAdd.LEVELPRIORITY)) {
            try {
                insertTodo(
                        data.getStringExtra(ActivityAdd.NEWTODO),
                        data.getIntExtra(ActivityAdd.LEVELPRIORITY, 0)
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}