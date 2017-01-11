package test.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pankajnimgade.pankajtestapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import system.utils.PageConfiguration;

public class CreatePDF107Activity extends AppCompatActivity {

    private Button pdf_Button;
    private static final String FILE_NAME = "test_107.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdf107);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CreatePDF107Activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        pdf_Button = (Button) findViewById(R.id.CreatePDF107Activity_open_pdf_button);
        pdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePDF107Activity.this, "Creating pdf...", Toast.LENGTH_SHORT)
                        .show();
                new CreatePDF107().execute();
            }
        });
    }

    private class CreatePDF107 extends AsyncTask<Void, Void, Void> {

        //
        public int MARGIN_START_CONTENT_Y = 0;
        public int MARGIN_END_CONTENT_Y = 0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_MOVIES),
                    FILE_NAME);
            test.deleteOnExit();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_MOVIES),
                        FILE_NAME);
                test.createNewFile();

                PdfDocument pdfDocument = new PdfDocument();

                int pageNumber = 0;
                do {
                    pageNumber++;
                    PdfDocument.Page page = createPage(pdfDocument, pageNumber);
                    pdfDocument.finishPage(page);
                } while (pageNumber < 3);


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
                Toast.makeText(getApplicationContext(), "" + test.getAbsolutePath(), Toast
                        .LENGTH_SHORT).show();
                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(Uri.fromFile(test), "application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                Intent intent = Intent.createChooser(target, "Open File");
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            myCanvas.drawText(getString(R.string.sm2_device_name), header_X, header_Y,
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
                paintBlueMedium.setTextSize(PageConfiguration.NORMAL_FONT_SIZE);
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

            //draw page number
            int page_x = PageConfiguration.MARGIN_LEFT + (int) ((PageConfiguration.PAGE_WIDTH -
                    (2 * PageConfiguration.MARGIN_LEFT)) * (4f / 5f));
            int page_y = PageConfiguration.MARGIN_TOP + PageConfiguration.SPACING_BETWEEN_SECTION;
            paintBlueMedium.setFakeBoldText(false);
            paintBlueMedium.setTextSize(PageConfiguration.NORMAL_FONT_SIZE);
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


}
