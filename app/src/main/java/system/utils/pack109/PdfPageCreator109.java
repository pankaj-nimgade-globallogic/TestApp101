package system.utils.pack109;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import system.utils.PageConfiguration;
import test.activities.CreatePDF109Activity;

/**
 * Created by pankaj.nimgade on 13-01-2017.
 */

public class PdfPageCreator109 extends AsyncTask<List<MyBlock109>, Void, Void> {

    //
    public int MARGIN_START_CONTENT_Y = 0;
    public int MARGIN_END_CONTENT_Y = 0;
    public int CURRENT_CONTENT_Y;
    private CreatePDF109Activity context;


    private static final String FILE_NAME = "test_109.pdf";

    public PdfPageCreator109(CreatePDF109Activity context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                .DIRECTORY_MOVIES),
                FILE_NAME);
        test.deleteOnExit();
    }

    @Override
    protected Void doInBackground(List<MyBlock109>... params) {

        try {
            File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_MOVIES),
                    FILE_NAME);
            test.createNewFile();

            PdfDocument pdfDocument = new PdfDocument();

            writeContent(pdfDocument, params[0]);

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

    private void writeContent(PdfDocument pdfDocument, List<MyBlock109> myBlock109s) {

        int pageNumber = 0;

        pageNumber++;
        PdfDocument.Page page = createPage(pdfDocument, pageNumber);
        pdfDocument.finishPage(page);
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
        if (pageNumber == 1) {

            MARGIN_START_CONTENT_Y = header_Y;
            CURRENT_CONTENT_Y = MARGIN_START_CONTENT_Y;
            writePatientInfo(myCanvas, CURRENT_CONTENT_Y);


        }

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

    private void writePatientInfo(Canvas canvas, int current_content_y) throws MyTable109
            .SymmetryException {
        Calendar calendar = Calendar.getInstance();
        List<MyTable109.Column109> column109s = new ArrayList<>();
        column109s.add(new MyTable109.Column109(50, Paint.Align.RIGHT));
        column109s.add(new MyTable109.Column109(50, Paint.Align.RIGHT));
        MyTable109 myTable109 = new MyTable109(column109s, 70);

        List<MyTable109.Cell> patientCells = new ArrayList<>();
        Paint paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        patientCells.add(new MyTable109.Cell("Patient:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        patientCells.add(new MyTable109.Cell("Reacher, Jack".toUpperCase(), paint));
        myTable109.addRows(new MyTable109.Row(patientCells));

        List<MyTable109.Cell> reportGeneratedDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        reportGeneratedDateCells.add(new MyTable109.Cell("Report Generated:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        reportGeneratedDateCells.add(new MyTable109.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable109.addRows(new MyTable109.Row(reportGeneratedDateCells));

        List<MyTable109.Cell> lastUpdateDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        lastUpdateDateCells.add(new MyTable109.Cell("Last Updated:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -(new Random().nextInt(50)));
        lastUpdateDateCells.add(new MyTable109.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable109.addRows(new MyTable109.Row(lastUpdateDateCells));

        List<MyTable109.Cell> nextRefillDateCells = new ArrayList<>();
        paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        nextRefillDateCells.add(new MyTable109.Cell("Next Refill:".toUpperCase(), paint));
        paint = new Paint();
        paint.setTextSize(PageConfiguration.FONT_SIZE_HIGH);
        paint.setFakeBoldText(false);
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, (new Random().nextInt(50)));
        nextRefillDateCells.add(new MyTable109.Cell(PageConfiguration
                .simpleDate.format(calendar.getTime()), paint));
        myTable109.addRows(new MyTable109.Row(nextRefillDateCells));

/*
        rows.add(new MyTable109.Row(Arrays.asList(new MyTable109.Cell(), new MyTable109.Cell())));
        rows.add(new MyTable109.Row(Arrays.asList("Report Generated: ",PageConfiguration
                .simpleDate.format(calendar.getTime()))));
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -(new Random()).nextInt(50));
        rows.add(new MyTable109.Row(Arrays.asList("Last Updated: ",PageConfiguration
                .simpleDate.format(calendar.getTime()))));
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, (new Random()).nextInt(50));
        rows.add(new MyTable109.Row(Arrays.asList("Last Updated: ",PageConfiguration
                .simpleDate.format(calendar.getTime()))));
*/


        createTable(canvas, myTable109);
    }

    private void createTable(Canvas canvas, MyTable109 myTable109) {
        CURRENT_CONTENT_Y += PageConfiguration.SPACING_BETWEEN_SECTION;

        for (MyTable109.Row row : myTable109.getRows()) {
            int height = 0;
            int count_x = PageConfiguration.MARGIN_LEFT;

            for (int i = 0; i < myTable109.getNumberColumn().size(); i++) {
                Paint textPaint = row.getRecordsCells().get(i).getTextPaint();
                MyTable109.Cell cell = row.getRecordsCells().get(i);

                MyTable109.Column109 column109 = myTable109.getNumberColumn().get(i);
                Rect someRect = new Rect();
                textPaint.getTextBounds(cell.getCellText(), 0, cell.getCellText().length(),someRect);

                 height = (2*PageConfiguration.TEXT_MARGIN)+(someRect.bottom - someRect.top);
                Rect rect = new Rect(count_x,
                        CURRENT_CONTENT_Y,
                        count_x + column109.getWidthCalculated(),
                        CURRENT_CONTENT_Y+ height);
//                canvas.drawRect(rect, PageConfiguration.getPaintBlueLow());
                canvas.drawText(cell.getCellText(), rect.left,

                        (int)((
                        (float) rect
                        .bottom+ (float)rect.top)/2) - ((textPaint.descent()+textPaint.ascent())/2),
                        textPaint);


                count_x += column109.getWidthCalculated();
            }
            CURRENT_CONTENT_Y += height;

        }

        CURRENT_CONTENT_Y += PageConfiguration.SPACING_BETWEEN_LINES_MEDIUM;
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


}
