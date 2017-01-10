package system.utils;

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
    public static final int SPACING_BETWEEN_LINES = 8;
    public static final int SPACING_BETWEEN_SECTION = 15;
    //color
    public static final int COLOR_WHITE = 0xFFFFFFFF;
    public static final int COLOR_BLUE_DARK = 0xFF001936;
    public static final int COLOR_BLUE_HIGH = 0xFF004B87;
    public static final int COLOR_BLUE_MEDIUM = 0xFF0085CA;
    public static final int COLOR_BLUE_LIGHT = 0xFFB9D9EB;

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy - " +
            "hh mm aa");
}
