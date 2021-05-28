package be.stefan.todolist.models;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import be.stefan.todolist.R;

public class ItemToDo {

    public static final String PATTERNDATEIN = "yyyy-MM-dd";
    private static final String PATTERNDATEOUT = "EEEE dd MM yyyy";

    public enum levelPriority {
        HIGH,
        MIDDLE,
        LOW
    }

    private String title, st_dateIn;
    private Date dateIn;
    private int priority;
    private boolean done;
    private Context context;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ItemToDo(String title, int priority, boolean done, String dateIn, Context context) throws ParseException {
        this.context = context;
        setTitle(title);
        setDateIn(dateIn);
        setPriority(priority);
        setDone(done);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateIn(String dateIn) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERNDATEIN);
        Date date = formatter.parse(dateIn);
        this.dateIn = date;
        this.setSt_dateIn();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setSt_dateIn() {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        dateFormatSymbols.setWeekdays(new String[]{
                "",
                (String) context.getResources().getText(R.string.sunday),
                (String) context.getResources().getText(R.string.monday),
                (String) context.getResources().getText(R.string.tuesday),
                (String) context.getResources().getText(R.string.wednesday),
                (String) context.getResources().getText(R.string.thusday),
                (String) context.getResources().getText(R.string.friday),
                (String) context.getResources().getText(R.string.saturday)
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERNDATEOUT, dateFormatSymbols);
        this.st_dateIn = simpleDateFormat.format(this.dateIn);
    }

    public String getSt_dateIn() {
        return st_dateIn;
    }

}
