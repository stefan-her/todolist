package be.stefan.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import be.stefan.todolist.models.ItemToDo;

public class ActivityAdd extends AppCompatActivity {

    public static final String NEWTODO = "new_todo";
    public static final String LEVELPRIORITY = "new_level_priority";

    private Spinner sp_levelPriority;
    private EditText et_newTodo;
    private Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_newTodo = findViewById(R.id.et_newtodo);
        et_newTodo.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        checkEditText();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        );

        sp_levelPriority = findViewById(R.id.sp_newpriority);
        buildSpinner(sp_levelPriority);
        bt_add = findViewById(R.id.bt_add);
        bt_add.setEnabled(false);
        bt_add.setOnClickListener(v -> sendValue());
    }

    private void checkEditText() {
        boolean v = et_newTodo.getText().toString().trim().length() > 0;
        if(v) { bt_add.setEnabled(true); }
        else { bt_add.setEnabled(false); }
    }

    private void sendValue() {
        Intent i = new Intent();
        i.putExtra(NEWTODO, et_newTodo.getText().toString().trim());
        i.putExtra(LEVELPRIORITY, (int) sp_levelPriority.getSelectedItemId());
        setResult(RESULT_OK, i);
        finish();
    }

    private void buildSpinner(Spinner obj) {
        ArrayList<String> levelPrior = new ArrayList<>();
        for (ItemToDo.levelPriority type : ItemToDo.levelPriority.values()) {
            String el = String.valueOf(type).toLowerCase();
            int id = getResources().getIdentifier(el, "string", getPackageName());
            if(id != 0) { levelPrior.add(getString(id)); }
            else { levelPrior.add((el)); }
        }

        ArrayAdapter<String> sp_levelPriority = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1,
                levelPrior
        );

        sp_levelPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        obj.setAdapter(sp_levelPriority);
    }
}