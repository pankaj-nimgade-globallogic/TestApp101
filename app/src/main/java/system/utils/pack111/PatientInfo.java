package system.utils.pack111;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import system.utils.PageConfiguration;
import system.utils.pack110.MyBlock110;
import system.utils.pack110.MyTable110;

/**
 * Created by pankaj.nimgade on 17-01-2017.
 */

public class PatientInfo {

    public static MyBlock111 getPatientInfo() throws Exception{
        Calendar calendar = Calendar.getInstance();
        List<MyTable111.Column111> column109s = new ArrayList<>();
        column109s.add(new MyTable111.Column111(50, Paint.Align.RIGHT));
        column109s.add(new MyTable111.Column111(50, Paint.Align.RIGHT));
        MyTable111 myTable110 = new MyTable111(column109s, 70);

        List<MyTable111.Cell> patientCells = new ArrayList<>();
        Paint paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        patientCells.add(new MyTable111.Cell("Patient:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        patientCells.add(new MyTable111.Cell("Reacher, Jack".toUpperCase(), paint));
        myTable110.addRows(new MyTable111.Row(patientCells));

        List<MyTable111.Cell> reportGeneratedDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        reportGeneratedDateCells.add(new MyTable111.Cell("Report Generated:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        reportGeneratedDateCells.add(new MyTable111.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable110.addRows(new MyTable111.Row(reportGeneratedDateCells));

        List<MyTable111.Cell> lastUpdateDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        lastUpdateDateCells.add(new MyTable111.Cell("Last Updated:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -(new Random().nextInt(50)));
        lastUpdateDateCells.add(new MyTable111.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable110.addRows(new MyTable111.Row(lastUpdateDateCells));

        List<MyTable111.Cell> nextRefillDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        nextRefillDateCells.add(new MyTable111.Cell("Next Refill:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, (new Random().nextInt(50)));
        nextRefillDateCells.add(new MyTable111.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable110.addRows(new MyTable111.Row(nextRefillDateCells));
        myTable110.setPatternOn(true);

        MyBlock111.MyBlock109Builder myBlock110 = new MyBlock111.MyBlock109Builder();
        myBlock110.setTitleOfBlock(null);
        MyBlock111 instance = myBlock110.build();
        instance.addTables(myTable110);
        return instance;
    }
}
