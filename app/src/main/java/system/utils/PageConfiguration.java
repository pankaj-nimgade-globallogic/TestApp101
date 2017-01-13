package system.utils;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;

import java.text.SimpleDateFormat;

/**
 * Created by pankaj.nimgade on 05-01-2017.
 */

public class PageConfiguration {

    //Page configuration
    public static final int PAGE_WIDTH = 612;
    public static final int PAGE_HEIGHT = 792;


    //font
    public static final int HEADER_FONT_SIZE = 20;
    public static final int NORMAL_FONT_SIZE = 10;
    public static final int SMALL_FONT_SIZE = 8;
    //margin
    public static final int MARGIN_TOP = PAGE_HEIGHT/12;
    public static final int MARGIN_RIGHT = PAGE_WIDTH/12;
    public static final int MARGIN_BOTTOM = PAGE_HEIGHT/12;
    public static final int MARGIN_LEFT = PAGE_WIDTH/12;

    //spacing
    public static final int SPACING_BETWEEN_LINES_SMALL = 10;
    public static final int SPACING_BETWEEN_LINES_MEDIUM = 15;
    public static final int SPACING_BETWEEN_SECTION = 20;
    //color
    public static final int COLOR_WHITE = 0xFFFFFFFF;
    public static final int COLOR_BLUE_DARK = 0xFF001936;
    public static final int COLOR_BLUE_HIGH = 0xFF004B87;
    public static final int COLOR_BLUE_MEDIUM = 0xFF0085CA;
    public static final int COLOR_BLUE_LIGHT = 0xFFB9D9EB;

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy - " +
            "hh mm aa");
    public static final SimpleDateFormat simpleDate = new SimpleDateFormat("MMM d, yyyy");

    public static Paint getPaintBoldDarkBlue() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        paint.setTextSize(PageConfiguration.HEADER_FONT_SIZE);
        paint.setFakeBoldText(true);
        return paint;
    }

    public static Paint getPaintBoldWhite() {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        return paint;
    }

    public static Paint getPaintBlueDark() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        paint.setTextSize(PageConfiguration.HEADER_FONT_SIZE);
        return paint;
    }

    public static Paint getPaintBlueMedium() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        return paint;
    }

    public static Paint getPaintBlueLow() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_LIGHT);
        return paint;
    }

    public static Paint getPaintBlueHigh() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_HIGH);
        return paint;
    }

    public static Paint getWhiteSmallPaint() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_WHITE);
        paint.setTextSize(PageConfiguration.SMALL_FONT_SIZE);
        return paint;
    }

    public static Paint getWhiteBoldColorPaint() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_WHITE);
        paint.setTextSize(PageConfiguration.NORMAL_FONT_SIZE);
        paint.setFakeBoldText(true);
        return paint;
    }
}
