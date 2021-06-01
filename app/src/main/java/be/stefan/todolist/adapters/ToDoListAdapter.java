package be.stefan.todolist.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import be.stefan.todolist.R;
import be.stefan.todolist.models.ItemToDo;


public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private ArrayList<ItemToDo> listToDo;
    private Context context;

    public ToDoListAdapter(ArrayList items, Context context) {
        this.listToDo = items;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_dateIn, tv_priority_num;
        private View v_priority;
        private CheckBox fb_done;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.item_title);
            v_priority = itemView.findViewById(R.id.item_priority);
            tv_priority_num = itemView.findViewById(R.id.item_priority_num);
            tv_dateIn = itemView.findViewById(R.id.item_dateIn);
            fb_done = itemView.findViewById(R.id.item_done);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemToDo item = listToDo.get(position);
        holder.tv_title.setText(item.getTitle());
        holder.tv_priority_num.setText(context.getResources().getText(R.string.priority) + String.valueOf(item.getPriority()));
        holder.tv_dateIn.setText(item.getSt_dateIn());

        /*
        if(item.isDone()) {
            holder.fb_done.setBackgroundTintList(
                    ColorStateList.valueOf(context.getColor(R.color.app_floatation_on))
            );
        } else {
            holder.fb_done.setBackgroundTintList(
                    ColorStateList.valueOf(context.getColor(R.color.app_floatation_off))
            );
        }
*/
        if(item.isDone()) { holder.fb_done.setChecked(true); }
        else { holder.fb_done.setChecked(false); }


        int color;
        switch (item.getPriority()) {
            case 1: color = R.color.app_priority1;
                break;
            case 2: color = R.color.app_priority2;
                break;
            case 3: color = R.color.app_priority3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getPriority());
        }

        holder.v_priority.setBackgroundColor(
                context.getResources().getColor(color, context.getTheme())
        );
    }

    @Override
    public int getItemCount() {
        return listToDo.size();
    }
}
