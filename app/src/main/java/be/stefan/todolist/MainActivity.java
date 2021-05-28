package be.stefan.todolist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import be.stefan.todolist.adapters.ToDoListAdapter;
import be.stefan.todolist.models.ItemToDo;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ItemToDo> listToDo;
    private FloatingActionButton bt_add;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listToDo = new ArrayList<>();

        buildActionBar();
        buildRecyclerView();

        bt_add = findViewById(R.id.bt_add);
        bt_add.setOnClickListener(v -> { addToDo(); });
    }

    private void buildActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void buildRecyclerView() {

//        listToDo.add(new ItemToDo("A faire 1", 1, false, "2020-09-05 12:05"));
//        listToDo.add(new ItemToDo("A faire 2", 3, true, "2020-10-21 14:40"));
//        listToDo.add(new ItemToDo("A faire 3", 2, false, "2020-11-19 20:32"));
//        listToDo.add(new ItemToDo("A faire 4", 2, false, "2020-12-01 18:05"));
//        listToDo.add(new ItemToDo("A faire 5", 3, true, "2021-02-22 15:15"));
//        listToDo.add(new ItemToDo("A faire 6", 1, false, "2021-05-27 08:10"));

        listToDo.add(new ItemToDo("A faire 1", 1, false));
        listToDo.add(new ItemToDo("A faire 2", 3, true));
        listToDo.add(new ItemToDo("A faire 3", 2, false));
        listToDo.add(new ItemToDo("A faire 4", 2, false));
        listToDo.add(new ItemToDo("A faire 5", 3, true));
        listToDo.add(new ItemToDo("A faire 6", 1, false));

        RecyclerView rv_toDo = findViewById(R.id.rv_todolist);
        rv_toDo.setHasFixedSize(false);

        ToDoListAdapter adapter = new ToDoListAdapter(listToDo, getApplicationContext());
        rv_toDo.setAdapter(adapter);

    }

    private void addToDo() {

    }
}