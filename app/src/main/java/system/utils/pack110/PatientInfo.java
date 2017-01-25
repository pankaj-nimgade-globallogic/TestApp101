package system.utils.pack110;

import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import system.utils.PageConfiguration;

/**
 * Created by pankaj.nimgade on 17-01-2017.
 */

public class PatientInfo {

    public static MyBlock110 getPatientInfo() throws Exception{
        Calendar calendar = Calendar.getInstance();
        List<MyTable110.Column110> column109s = new ArrayList<>();
        column109s.add(new MyTable110.Column110(50, Paint.Align.RIGHT));
        column109s.add(new MyTable110.Column110(50, Paint.Align.RIGHT));
        MyTable110 myTable110 = new MyTable110(column109s, 70);

        List<MyTable110.Cell> patientCells = new ArrayList<>();
        Paint paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        patientCells.add(new MyTable110.Cell("Patient:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        patientCells.add(new MyTable110.Cell("Reacher, Jack".toUpperCase(), paint));
        myTable110.addRows(new MyTable110.Row(patientCells));

        List<MyTable110.Cell> reportGeneratedDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        reportGeneratedDateCells.add(new MyTable110.Cell("Report Generated:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        reportGeneratedDateCells.add(new MyTable110.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable110.addRows(new MyTable110.Row(reportGeneratedDateCells));

        List<MyTable110.Cell> lastUpdateDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        lastUpdateDateCells.add(new MyTable110.Cell("Last Updated:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -(new Random().nextInt(50)));
        lastUpdateDateCells.add(new MyTable110.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable110.addRows(new MyTable110.Row(lastUpdateDateCells));

        List<MyTable110.Cell> nextRefillDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        nextRefillDateCells.add(new MyTable110.Cell("Next Refill:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, (new Random().nextInt(50)));
        nextRefillDateCells.add(new MyTable110.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable110.addRows(new MyTable110.Row(nextRefillDateCells));

        MyBlock110.MyBlock109Builder myBlock110 = new MyBlock110.MyBlock109Builder();
        myBlock110.setTitleOfBlock(null);
        myBlock110.setTitleOfBlock("Something to be".toUpperCase());
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        paint.setFakeBoldText(true);
        myBlock110.setBlockPaint(paint);
        myBlock110.setBulletin(MyBlock110.Bulletin.SQUARE);
        MyBlock110 instance = myBlock110.build();
        instance.addTables(myTable110);
        return instance;
    }
}
