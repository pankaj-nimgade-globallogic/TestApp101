package system.utils.pack111;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import system.utils.PageConfiguration;

/**
 * Created by pankaj.nimgade on 13-01-2017.
 */

public class PdfPageCreator111 extends AsyncTask<List<MyBlock111>, Void, Void> {

    //
    public int MARGIN_START_CONTENT_Y = 0;
    public int MARGIN_END_CONTENT_Y = 0;
    public int CURRENT_CONTENT_Y;
    private CreatePDF111Activity context;
    private PdfDocument pdfDocument;
    private PdfDocument.Page page;
    private int pageNumber;


    private static final String FILE_NAME = "test_111.pdf";

    public PdfPageCreator111(CreatePDF111Activity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                .DIRECTORY_MOVIES),
                FILE_NAME);
        test.deleteOnExit();
        createFile();
    }

    @Override
    protected Void doInBackground(List<MyBlock111>... params) {

        try {
            File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_MOVIES),
                    FILE_NAME);
            test.createNewFile();

            pdfDocument = new PdfDocument();

            writeContent(params[0]);

            OutputStream outputStream = new FileOutputStream(test);
            pdfDocument.writeTo(outputStream);
            pdfDocument.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try {

            File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_MOVIES),
                    FILE_NAME);
            System.out.println("Created pdf file........");
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(Uri.fromFile(test), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            Intent intent = Intent.createChooser(target, "Open File");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeContent(List<MyBlock111> myBlock109s) {

        int pageNumber = 0;

        pageNumber++;
        page = createPage(pdfDocument, pageNumber);
        for (MyBlock111 myBlock110 : myBlock109s) {
            writeBlock(myBlock110);
        }


        pdfDocument.finishPage(page);
    }

    private void writeBlock(MyBlock111 myBlock109) {
        CURRENT_CONTENT_Y += PageConfiguration.SPACING_BETWEEN_SECTION;
        int left_margin = PageConfiguration.MARGIN_LEFT;
        MyBlock111.Bulletin bulletin = myBlock109.getBulletin();
        if (CURRENT_CONTENT_Y > (MARGIN_END_CONTENT_Y)) {
            pdfDocument.finishPage(page);
            pageNumber = page.getInfo().getPageNumber();
            pageNumber++;
            page = createPage(pdfDocument, pageNumber);
        }
        if (bulletin != null) {
            if (bulletin == MyBlock111.Bulletin.CIRCLE) {
                page.getCanvas().drawCircle(left_margin + 4, CURRENT_CONTENT_Y + 4,
                        PageConfiguration.FONT_SIZE_HIGH,
                        PageConfiguration.getPaintBlueDark());
                left_margin += (int) ((float) PageConfiguration.MARGIN_LEFT / 2);
                CURRENT_CONTENT_Y += 4;

            } else if (bulletin == MyBlock111.Bulletin.SQUARE) {
                Rect rect = new Rect(left_margin, CURRENT_CONTENT_Y, left_margin +
                        PageConfiguration.FONT_SIZE_NORMAL,
                        CURRENT_CONTENT_Y + PageConfiguration.FONT_SIZE_NORMAL);
                page.getCanvas().drawRect(rect,
                        PageConfiguration.getPaintBlueDark());
                left_margin += (int) ((float) PageConfiguration.MARGIN_LEFT / 2);
                CURRENT_CONTENT_Y += PageConfiguration.FONT_SIZE_NORMAL;
            }
        }

        if (myBlock109.getTitleOfBlock() != null && !myBlock109.getTitleOfBlock().equals("")) {
            page.getCanvas().drawText(myBlock109.getTitleOfBlock(), left_margin,
                    CURRENT_CONTENT_Y, myBlock109.getBlockPaint());
        }

        CURRENT_CONTENT_Y += PageConfiguration.SPACING_BETWEEN_SECTION;

        int trackerNumber = 0;
        for (MyTable111 myTable110 : myBlock109.getMyTables()) {
            int writable_Space= PageConfiguration.PAGE_WIDTH - (left_margin+PageConfiguration
                    .MARGIN_RIGHT);
            myTable110.reCalculateColumnWidth(writable_Space);
            for (MyTable111.Row row : myTable110.getRows()) {
                trackerNumber++;
                int height = 0;
                int count_x = left_margin;

                for (int i = 0; i < myTable110.getNumberColumn().size(); i++) {
                    if (CURRENT_CONTENT_Y > (MARGIN_END_CONTENT_Y)) {
                        pdfDocument.finishPage(page);
                        pageNumber = page.getInfo().getPageNumber();
                        pageNumber++;
                        page = createPage(pdfDocument, pageNumber);
                    }
                    Paint textPaint = row.getRecordsCells().get(i).getTextPaint();
                    MyTable111.Cell cell = row.getRecordsCells().get(i);
                    MyTable111.Column111 column109 = myTable110.getNumberColumn().get(i);

                    Rect someRect = new Rect();
                    textPaint.getTextBounds(cell.getCellText(), 0, cell.getCellText().length(),
                            someRect);

                    height = (2 * PageConfiguration.TEXT_MARGIN) + (someRect.bottom - someRect.top);
                    Rect rect = new Rect(count_x,
                            CURRENT_CONTENT_Y,
                            count_x + column109.getWidthCalculated(),
                            CURRENT_CONTENT_Y + height);

                    if (myTable110.isPatternOn()) {
                        if ((trackerNumber%2) == 0) {
                            page.getCanvas().drawRect(rect, PageConfiguration.getPaintBlueLow());
                            page.getCanvas().drawRect(someRect, PageConfiguration.getWhiteSmallPaint());
                        }
                    }

                    if (textPaint.getTextAlign() == Paint.Align.LEFT) {
                        page.getCanvas().drawText(cell.getCellText(), rect.left,
                                (int) ((
                                        (float) rect
                                                .bottom + (float) rect.top) / 2) - ((textPaint
                                        .descent()
                                        + textPaint.ascent()) / 2),
                                textPaint);
                    } else if (textPaint.getTextAlign() == Paint.Align.RIGHT) {
                        page.getCanvas().drawText(cell.getCellText(), rect.right,
                                (int) ((
                                        (float) rect
                                                .bottom + (float) rect.top) / 2) - ((textPaint
                                        .descent()
                                        + textPaint.ascent()) / 2),
                                textPaint);
                    } else if (textPaint.getTextAlign() == Paint.Align.CENTER) {
                        int x= rect.right - rect.left;
                        page.getCanvas().drawText(cell.getCellText(), x/2,
                                (int) ((
                                        (float) rect
                                                .bottom + (float) rect.top) / 2) - ((textPaint
                                        .descent()
                                        + textPaint.ascent()) / 2),
                                textPaint);
                    }


                    count_x += column109.getWidthCalculated();
                }
                CURRENT_CONTENT_Y += height;

            }
        }

        CURRENT_CONTENT_Y += PageConfiguration.SPACING_BETWEEN_SECTION;

    }


    private PdfDocument.Page createPage(PdfDocument pdfDocument, int pageNumber) {
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(PageConfiguration.PAGE_WIDTH,
                        PageConfiguration.PAGE_HEIGHT, pageNumber)
                        .create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas myCanvas = page.getCanvas();
        try {
            writeHeader(myCanvas, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        writeFooter(myCanvas, page);
        return page;
    }

    private void writeHeader(Canvas myCanvas, PdfDocument.Page page) throws Exception {
        int header_X = PageConfiguration.MARGIN_LEFT;
        int header_Y = PageConfiguration.MARGIN_TOP + PageConfiguration
                .SPACING_BETWEEN_LINES_MEDIUM;
        myCanvas.drawText("SYNCHROMED II", header_X, header_Y,
                PageConfiguration
                        .getPaintBoldDarkBlue());
        header_Y += PageConfiguration.SPACING_BETWEEN_LINES_MEDIUM;
        Paint paintBlueMedium = PageConfiguration
                .getPaintBlueMedium();
        paintBlueMedium.setTextSize(15);
        myCanvas.drawText("DOSING REPORT", header_X, header_Y, paintBlueMedium);
        header_Y += PageConfiguration.SPACING_BETWEEN_SECTION;


        int pageNumber = page.getInfo().getPageNumber();

        MARGIN_START_CONTENT_Y = header_Y + PageConfiguration.SPACING_BETWEEN_SECTION;
        CURRENT_CONTENT_Y = MARGIN_START_CONTENT_Y;
        //draw page number
        int page_x = PageConfiguration.MARGIN_LEFT + (int) ((PageConfiguration.PAGE_WIDTH -
                (2 * PageConfiguration.MARGIN_LEFT)) * (4f / 5f));
        int page_y = PageConfiguration.MARGIN_TOP + PageConfiguration.SPACING_BETWEEN_SECTION;
        paintBlueMedium.setFakeBoldText(false);
        paintBlueMedium.setTextSize(PageConfiguration.FONT_SIZE_NORMAL);
        myCanvas.drawText("Page  " + pageNumber + "  of  3", page_x, page_y, paintBlueMedium);

    }


    private void writeFooter(Canvas myCanvas, PdfDocument.Page page) {
        // light color boarder
        int footer_left = PageConfiguration.MARGIN_LEFT;
        int footer_top = PageConfiguration.PAGE_HEIGHT - PageConfiguration.MARGIN_BOTTOM -
                (PageConfiguration.MARGIN_BOTTOM / 2);
        int footer_right = PageConfiguration.PAGE_WIDTH - PageConfiguration.MARGIN_RIGHT;
        int footer_bottom = PageConfiguration.PAGE_HEIGHT - PageConfiguration.MARGIN_BOTTOM;
        Rect rect_first = new Rect(footer_left, footer_top, footer_right, footer_bottom);

        myCanvas.drawRect(rect_first, PageConfiguration.getPaintBlueMedium());

        MARGIN_END_CONTENT_Y = rect_first.top - PageConfiguration.SPACING_BETWEEN_SECTION;

        // dark color boarder
        float width = (rect_first.right - rect_first.left) * (2f / 3f);
        int second_box_footer_left = PageConfiguration.MARGIN_LEFT + (int) width;
        Rect rect_second = new Rect(second_box_footer_left, footer_top, footer_right,
                footer_bottom);
        myCanvas.drawRect(rect_second, PageConfiguration.getPaintBlueHigh());

        // write Medtronic in Dark boarder
        int medtronic_left = rect_second.left + ((rect_second.right - rect_second.left) / 2);
        float medtronic_top = rect_second.top + ((rect_second.bottom - rect_second.top) * (2f /
                3f));
        myCanvas.drawText("Medtronic", medtronic_left, (int) medtronic_top,
                PageConfiguration.getPaintBoldWhite());

        // write Patient information
        int patient_info_left = rect_first.left + PageConfiguration
                .SPACING_BETWEEN_LINES_MEDIUM;
        float patient_info_top = rect_first.top + PageConfiguration.SPACING_BETWEEN_LINES_SMALL;
        myCanvas.drawText("Jhon Reacher", patient_info_left, (int) patient_info_top,
                PageConfiguration.getWhiteSmallPaint());
        // write Patient information
        patient_info_top += PageConfiguration.SPACING_BETWEEN_LINES_SMALL;
        Date date = Calendar.getInstance().getTime();

        myCanvas.drawText(PageConfiguration.simpleDateFormat.format(date), patient_info_left,
                (int) patient_info_top,
                PageConfiguration.getWhiteSmallPaint());

    }

    private File createFile (){
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory("PatientReports"), "something.pdf");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


}
