package system.utils.pack111;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import system.utils.PageConfiguration;

/**
 * Created by pankaj.nimgade on 13-01-2017.
 */

public class MyTable111 {

    private Paint tablePaint = new Paint();
    private boolean omitBoarders;
    private Canvas myCanvas;
    private int tableRowTracker = 0;
    private boolean isPatternOn;


    private List<Row> rows = new ArrayList<>();

    /**
     * This defines number of column with
     */
    private List<Column111> numberColumn;
    /**
     * Percentage of Writable space in page, which will be used to create table width
     * ({@link android.graphics.pdf.PdfDocument.Page} in my case as I have nothing
     * else to do with it)
     */
    private final int widthPercentage;

    /**
     * Width of th table this is calculated on premise of "widthPercentage"
     */
    private int tablesWidth;

    private boolean isColumnTitleWritten;

    public MyTable111(List<Column111> numberColumn, int widthPercentage) throws
            SymmetryException {
        this(widthPercentage);
        this.numberColumn = numberColumn;
        checkSymmetry();
        this.isPatternOn = false;
    }

    private void checkSymmetry() throws SymmetryException {
        int percentage_100 = 0;
        for (Column111 column : this.numberColumn) {
            percentage_100 += column.widthPercentage;
            float columnWidth = (((float) this.tablesWidth ) * ((float) column
                    .widthPercentage/100));
            column.widthCalculated = (int) columnWidth;
        }
        if (percentage_100 != 100) {
            new system.utils.MyTable.SymmetryException("width summation of columns is not equal to 100");
        }
    }

    public void reCalculateColumnWidth(int writableSpace){
        int currentTableWidth = (int)(writableSpace*((float)widthPercentage/100));
        this.tablesWidth = currentTableWidth;
        for (Column111 column : this.numberColumn) {
            float columnWidth = (((float) this.tablesWidth ) * ((float) column.widthPercentage/100));
            column.widthCalculated = (int) columnWidth;
        }
    }

    public void addRows(MyTable111.Row row){
        rows.add(row);
    }

    private MyTable111(int widthPercentage) {
        this.widthPercentage = widthPercentage;
        int writableSpace = PageConfiguration.PAGE_WIDTH -(PageConfiguration.MARGIN_LEFT +
                PageConfiguration
                        .MARGIN_RIGHT);

        int currentTableWidth = (int)(writableSpace*((float)widthPercentage/100));
        tablePaint.setColor(Color.BLACK);
        omitBoarders = true;
        this.tablesWidth = currentTableWidth;
    }

    public void setPatternOn(boolean patternOn) {
        isPatternOn = patternOn;
    }

    public boolean isPatternOn() {
        return isPatternOn;
    }

    public int getTablesWidth() {
        return tablesWidth;
    }

    public List<Column111> getNumberColumn() {
        return numberColumn;
    }

    public List<Row> getRows() {
        return rows;
    }



    public static class Column111 {
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

        private String titleOfColumn;

        /**
         * Use this when you require column title
         *
         * @param widthPercentage
         * @param alignment
         * @param titleOfColumn
         */
        public Column111(int widthPercentage, Paint.Align alignment, String titleOfColumn) {
            this.widthPercentage = widthPercentage;
            this.alignment = alignment;
            this.titleOfColumn = titleOfColumn;
        }

        /**
         * Use this constructor when you do not require column title
         *
         * @param widthPercentage
         * @param alignment
         */
        public Column111(int widthPercentage, Paint.Align alignment) {
            this.widthPercentage = widthPercentage;
            this.alignment = alignment;
        }

        public int getWidthPercentage() {
            return widthPercentage;
        }

        public Paint.Align getAlignment() {
            return alignment;
        }

        public int getWidthCalculated() {
            return widthCalculated;
        }
    }

    public static class Cell {

        private String cellText;
        private Paint textPaint = new Paint();
        private Paint backgroundPaint = new Paint();

        public Cell(String cellText, Paint textPaint, Paint backgroundPaint) {
            this.cellText = cellText;
            this.textPaint = textPaint;
            this.backgroundPaint = backgroundPaint;
        }

        public Cell(String cellText, Paint textPaint) {
            this.cellText = cellText;
            this.textPaint = textPaint;
        }

        public String getCellText() {
            return cellText;
        }

        public Paint getTextPaint() {
            return textPaint;
        }

        public void setTextPaint(Paint textPaint) {
            this.textPaint = textPaint;
        }

        public Paint getBackgroundPaint() {
            return backgroundPaint;
        }

        public void setBackgroundPaint(Paint backgroundPaint) {
            this.backgroundPaint = backgroundPaint;
        }
    }


    public static class Row{

        private List<Cell> recordsCells = new ArrayList<>();


        public Row(List<Cell> recordsCells) {
            this.recordsCells = recordsCells;
        }

        public List<Cell> getRecordsCells() {
            return recordsCells;
        }
    }

    public static class SymmetryException extends Exception {

        public SymmetryException(String message) {
            super(message);
        }
    }


}
