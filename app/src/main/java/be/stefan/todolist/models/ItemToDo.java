package be.stefan.todolist.models;



import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ItemToDo {

    private final String PATTERNDATE = "yyyy-MM-dd HH:mm";

    private String title, st_dateIn;
    //private LocalDateTime dateIn;
    private int priority;
    private boolean done;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ItemToDo(String title, int priority, boolean done) {
        setTitle(title);
        //setDateIn(dateIn);
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


/*
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String dateForm(String datetoConvert) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERNDATE);
        LocalDateTime date = LocalDateTime.parse(datetoConvert);

        DateFormatSymbols formatSymbols = new DateFormatSymbols();
        String[] week = new String[] {
                "",
                "Di",
                "Lu",
                "Ma",
                "Me",
                "Je",
                "Ve",
                "Sa" };
        formatSymbols.setShortWeekdays(week);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE dd MMM yyyy HH:mm", formatSymbols);

        return dateFormat.format(datetoConvert);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateIn(String dateIn) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERNDATE);
        LocalDateTime date = LocalDateTime.parse(dateIn, formatter);

        this.dateIn = date;
        this.setSt_dateIn(dateIn);
    }

    public String getSt_dateIn() {
        return st_dateIn;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setSt_dateIn(String dateIn) {
        String date = this.dateForm(dateIn);
        this.st_dateIn = date;
    }

    public LocalDateTime getDateIn() {
        return dateIn;
    }
*/

}
