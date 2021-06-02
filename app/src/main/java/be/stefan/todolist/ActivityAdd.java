package be.stefan.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

import be.stefan.todolist.models.Category;
import be.stefan.todolist.models.ItemToDo;

public class ActivityAdd extends AppCompatActivity {

    public static final String NEWTODO = "new_todo";
    public static final String LEVELPRIORITY = "new_level_priority";
    public static final String CATEGORIES = "new_categories";

    private Spinner sp_levelPriority, sp_category;
    private EditText et_newTodo;
    private Button bt_add;
    private HashMap<String, ArrayAdapter> adapters;
    private ArrayList<String> listCat, listLevelPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        adapters = new HashMap<>();

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

        sp_levelPriority = findViewById(R.id.sp_priority);
        listLevelPriority = ItemToDo.getListEnum(getApplicationContext());
        buildSpinner(sp_levelPriority, listLevelPriority, "sp_levelPriority");

        sp_category = findViewById(R.id.sp_category);
        listCat = new ArrayList<>();
        listCat.add(new Category("cat 1").getName());
        listCat.add(new Category("cat 2").getName());
        listCat.add(new Category("cat 3").getName());

        listCat.add(getString(R.string.add_new_cat));

        buildSpinner(sp_category, listCat, "sp_category");
        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(sp_category.getSelectedItem().toString().equals(getString(R.string.add_new_cat))) {
                    openDialogCategory();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        bt_add = findViewById(R.id.bt_add);
        bt_add.setEnabled(false);
        bt_add.setOnClickListener(v -> sendValue());
    }

    private void openDialogCategory(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_new_cat)
                .setView(R.layout.dialog_add_cat)
                .setPositiveButton(R.string.dialog_btn_add, (dialog, which) -> {
                    EditText et_name = ((AlertDialog)dialog).findViewById(R.id.et_dialog_add_category_name);
                    String nameCategory = et_name.getText().toString();
                    listCat.add(listCat.size() -1, new Category(nameCategory).getName());
                    adapters.get("sp_category").notifyDataSetChanged();
                })
                .setNegativeButton(R.string.dialog_btn_cancel, (dialog, which) -> {})
                .show();
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

    private void buildSpinner(Spinner obj, ArrayList<String> items, String adapterName) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                android.R.id.text1,
                items
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        obj.setAdapter(adapter);

        adapters.put(adapterName, adapter);
    }
}