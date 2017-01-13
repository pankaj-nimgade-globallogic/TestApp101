package system.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.List;

/**
 * Created by pankaj.nimgade on 12-01-2017.
 */

public class MyTable {

    private Paint tablePaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint backgroundPaint = new Paint();
    private boolean omitBoarders;
    private Canvas myCanvas;

    /**
     * This defines number of column with
     */
    private List<Column> numberColumn;
    /**
     * Percentage of the width of the table against a space
     * ({@link android.graphics.pdf.PdfDocument.Page} in my case as I have nothing
     * else to do with it)
     */
    private final int tablesWidth;

    public MyTable(List<Column> numberColumn, int tablesWidth, Canvas canvas) throws
            SymmetryException {
        this(tablesWidth);
        this.numberColumn = numberColumn;
        checkSymmetry();
        this.myCanvas = canvas;
    }

    private void checkSymmetry() throws SymmetryException {
        int percentage_100 = 0;
        for (Column column : this.numberColumn) {
            percentage_100 += column.widthPercentage;
            float columnWidth = ( ((float) this.tablesWidth/100) *(float) column.widthPercentage);
            column.widthCalculated = (int) columnWidth;
        }
        if (percentage_100 != 100) {
            new SymmetryException("width summation of columns is not equal to 100");
        }
    }

    private MyTable(int tablesWidth) {
        tablePaint.setColor(Color.BLACK);
        textPaint.setColor(Color.BLACK);
        backgroundPaint.setColor(Color.WHITE);
        omitBoarders = true;
        this.tablesWidth = tablesWidth;
    }

    public Paint getTablePaint() {
        return tablePaint;
    }

    public Paint getTextPaint() {
        return textPaint;
    }

    public Paint getBackgroundPaint() {
        return backgroundPaint;
    }

    public boolean isOmitBoarders() {
        return omitBoarders;
    }

    public void setOmitBoarders(boolean omitBoarders) {
        this.omitBoarders = omitBoarders;
    }

    public int getTablesWidth() {
        return tablesWidth;
    }

    public int drawLine(int current_content_y_coordinate) {

        myCanvas.drawLine(PageConfiguration.MARGIN_LEFT, current_content_y_coordinate,
                PageConfiguration.MARGIN_LEFT + tablesWidth, ++current_content_y_coordinate,
                PageConfiguration.getPaintBlueDark());
        return current_content_y_coordinate;
    }

    /**
     * Writes a record (of course horizontally) in a row of table
     *
     * @param stringArray it's size should be same as size of{@link numberColumn}
     */
    public int writeRecord(List<String> stringArray, int current_content_y_coordinate) {
        int count_x = PageConfiguration.MARGIN_LEFT;
        String[] strings = stringArray.toArray(new String[stringArray.size()]);

        for (int i = 0; i < numberColumn.size(); i++) {
            Column column = numberColumn.get(i);
            Rect rect = new Rect(count_x,
                    current_content_y_coordinate,
                    count_x + column.widthCalculated,
                    current_content_y_coordinate + PageConfiguration.SPACING_BETWEEN_LINES_MEDIUM);



            count_x += column.widthCalculated;
            myCanvas.drawRect(rect, PageConfiguration.getPaintBlueLow());
            myCanvas.drawText(""+strings[i], rect.left,rect.top,PageConfiguration
                    .getPaintBlueLow());
        }

        return current_content_y_coordinate;
    }

    public static class Column {
        /**
         * This tells the percentage of width out of 100
         */
        private int widthPercentage;
        /**
         * Width calculated from widthPercentage of table width.
         * <pre>{@code widthCalculated = (widthPercentage/tablesWidth)100}<pre/>
         */
        private int widthCalculated;
        /**
         * this tells the alignment of the column
         */
        private Paint.Align alignment;

        public Column(int widthPercentage, Paint.Align alignment) {
            this.widthPercentage = widthPercentage;
            this.alignment = alignment;
        }

        public int getWidthPercentage() {
            return widthPercentage;
        }

        public Paint.Align getAlignment() {
            return alignment;
        }
    }

    public static class SymmetryException extends Exception {

        public SymmetryException(String message) {
            super(message);
        }
    }
}
