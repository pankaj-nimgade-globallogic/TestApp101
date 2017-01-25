package system.utils.pack111;

import android.graphics.Paint;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import system.utils.PageConfiguration;

/**
 * Created by pankaj.nimgade on 17-01-2017.
 */

public class PTMLogsWritter {

    public static MyBlock111 getPTMLogs() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -100);
        calendar.add(Calendar.HOUR_OF_DAY, getRandomInterval());
        List<MyTable111.Column111> column111s = new ArrayList<>();
        column111s.add(new MyTable111.Column111(50, Paint.Align.RIGHT, "Event".toUpperCase()));
        column111s.add(new MyTable111.Column111(20, Paint.Align.RIGHT, "Code".toUpperCase()));
        column111s.add(new MyTable111.Column111(30, Paint.Align.RIGHT, "Time".toUpperCase()));
        MyTable111 myTable111 = new MyTable111(column111s, 100);
        myTable111.setPatternOn(true);

        List<MyTable111.Cell> patientCells = new ArrayList<>();
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_NORMAL);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        patientCells.add(new MyTable111.Cell("Event".toUpperCase(), paint));
        patientCells.add(new MyTable111.Cell("Code".toUpperCase(), paint));
        patientCells.add(new MyTable111.Cell("Time".toUpperCase(), paint));
        myTable111.addRows(new MyTable111.Row(patientCells));


        for (int i = 0; i < 500; i++) {
            List<MyTable111.Cell> patientCells1 = new ArrayList<>();
            paint = new Paint();
            paint.setTextAlign(Paint.Align.RIGHT);
            paint.setFakeBoldText(false);
            paint.setTextSize(PageConfiguration.FONT_SIZE_NORMAL);
            paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
            patientCells1.add(new MyTable111.Cell("Pump Alarm".toUpperCase(), paint));
            patientCells1.add(new MyTable111.Cell("8235".toUpperCase(), paint));
            calendar.add(Calendar.HOUR_OF_DAY, getRandomInterval());
            SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy HH:mm");
            calendar.add(Calendar.HOUR_OF_DAY, getRandomInterval());
            calendar.add(Calendar.MINUTE, getRandomInterval());
            patientCells1.add(new MyTable111.Cell(format.format(calendar.getTime()), paint));
            myTable111.addRows(new MyTable111.Row(patientCells1));
        }

        MyBlock111.MyBlock109Builder myBlock110 = new MyBlock111.MyBlock109Builder();
        myBlock110.setTitleOfBlock("Patient Programming events".toUpperCase());
        myBlock110.setBulletin(MyBlock111.Bulletin.SQUARE);
        MyBlock111 instance = myBlock110.build();

        instance.addTables(myTable111);
        return instance;
    }





    private static int getRandomInterval() {

        return (new Random()).nextInt(12);
    }
}
