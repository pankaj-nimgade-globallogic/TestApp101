package system.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import system.utils.pack109.MyBlock109;

/**
 * Created by pankaj.nimgade on 13-01-2017.
 */

public class PdfPageCreator extends AsyncTask<List<MyBlock109>, Void, Void> {

    //
    public int MARGIN_START_CONTENT_Y = 0;
    public int MARGIN_END_CONTENT_Y = 0;
    public int CURRENT_CONTENT_Y;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(List<MyBlock109>... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    private PdfDocument.Page createPage(PdfDocument pdfDocument, int paageNumber) {
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(PageConfiguration.PAGE_WIDTH,
                        PageConfiguration.PAGE_HEIGHT, paageNumber)
                        .create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas myCanvas = page.getCanvas();
        writeHeader(myCanvas, page);
        writeFooter(myCanvas, page);
        return page;
    }

    private void writeHeader(Canvas myCanvas, PdfDocument.Page page) {
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

            int deviation_x = PageConfiguration.MARGIN_LEFT + (int) ((PageConfiguration
                    .PAGE_WIDTH -
                    (2 * PageConfiguration.MARGIN_LEFT)) * (1f / 5f));
            int patient_data_X = header_X + deviation_x;
            // print patient information
            paintBlueMedium.setColor(PageConfiguration.COLOR_BLUE_HIGH);
            paintBlueMedium.setTextSize(PageConfiguration.FONT_SIZE_NORMAL);
            paintBlueMedium.setFakeBoldText(true);
            header_Y += PageConfiguration.SPACING_BETWEEN_SECTION;
            myCanvas.drawText("PATIENT:", header_X, header_Y, paintBlueMedium);
            myCanvas.drawText("Jack Reacher", patient_data_X, header_Y, paintBlueMedium);
            header_Y += PageConfiguration.SPACING_BETWEEN_LINES_MEDIUM;
            myCanvas.drawText("REPORT GENERATED:", header_X, header_Y, paintBlueMedium);
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            myCanvas.drawText(PageConfiguration.simpleDate.format(date), patient_data_X,
                    header_Y, paintBlueMedium);
            header_Y += PageConfiguration.SPACING_BETWEEN_LINES_MEDIUM;
            myCanvas.drawText("LAST UPDATE:", header_X, header_Y, paintBlueMedium);
            calendar.add(Calendar.DAY_OF_MONTH, -(new Random()).nextInt(45));
            myCanvas.drawText(PageConfiguration.simpleDate.format(calendar.getTime()),
                    patient_data_X, header_Y, paintBlueMedium);
            header_Y += PageConfiguration.SPACING_BETWEEN_LINES_MEDIUM;
            myCanvas.drawText("EXPECTED REFILL DATE:", header_X, header_Y, paintBlueMedium);
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, (new Random()).nextInt(45));
            myCanvas.drawText(PageConfiguration.simpleDate.format(calendar.getTime()),
                    patient_data_X, header_Y, paintBlueMedium);
            header_Y += PageConfiguration.SPACING_BETWEEN_SECTION;
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
