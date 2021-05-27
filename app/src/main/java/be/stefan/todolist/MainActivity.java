package be.stefan.todolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList listToDo;
    private FloatingActionButton bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildActionBar();
        buildRecyclerView();

        bt_add = findViewById(R.id.bt_add);
        bt_add.setOnClickListener(v -> { addToDo(); });
    }

    private void buildActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void buildRecyclerView() {

//        listToDo.add(new Todo();
//        listToDo.add(new Todo();
//        listToDo.add(new Todo();
//        listToDo.add(new Todo();

//        RecyclerView rv_mainFoods = findViewById(R.id.rv_main_foods);
//        rv_toDo.setHasFixedSize(false);
//
//        ToDoRecycleViewAdapter adapter = new ToDoRecycleViewAdapter(listToDo);
//        rv_toDo.setAdapter(adapter);
//        rv_toDo.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    private void addToDo() {

    }
}