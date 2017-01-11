package test.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

import system.utils.PageConfiguration;

public class CreatePDF103Activity extends AppCompatActivity {

    private Button pdf_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdf103);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CreatePDF103Activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        pdf_Button = (Button) findViewById(R.id.CreatePDF103Activity_open_pdf_button);
        pdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePDF103Activity.this, "Creating pdf...", Toast.LENGTH_SHORT)
                        .show();
                new CreatePDF103().execute();

            }
        });
    }

    private class CreatePDF103 extends AsyncTask<Void, Void, Void> {

        private Canvas myCanvas;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_MOVIES),
                    "test_103.pdf");
            test.deleteOnExit();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_MOVIES),
                        "test_103.pdf");
                test.createNewFile();

                PdfDocument pdfDocument = new PdfDocument();
                PdfDocument.PageInfo pageInfo =
                        new PdfDocument.PageInfo.Builder(PageConfiguration.PAGE_WIDTH,
                                PageConfiguration.PAGE_HEIGHT, 1)
                                .create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                myCanvas = page.getCanvas();
                writeHeader(myCanvas, page);
                writeFooter(myCanvas, page);

                pdfDocument.finishPage(page);
                OutputStream outputStream = new FileOutputStream(test);
                pdfDocument.writeTo(outputStream);
                pdfDocument.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private void writeHeader(Canvas myCanvas, PdfDocument.Page page) {
            myCanvas.drawText("SYNCHROMED II®",
                    PageConfiguration.MARGIN_LEFT, PageConfiguration.MARGIN_TOP,
                    getHeaderPaint());
            myCanvas.drawText("Dosing Report",
                    PageConfiguration.MARGIN_LEFT,
                    PageConfiguration.MARGIN_TOP + PageConfiguration.HEADER_FONT_SIZE,
                    getHeaderPaint());
            int xPos = (myCanvas.getWidth() / 2) + (myCanvas.getWidth() / 3);
            myCanvas.drawText("Page 1 of 1",
                    PageConfiguration.MARGIN_LEFT + xPos,
                    PageConfiguration.MARGIN_TOP + PageConfiguration.HEADER_FONT_SIZE,
                    getPageNumberHeaderPaint());
        }

        private void writeFooter(Canvas myCanvas, PdfDocument.Page page) {
            int rect_xPosition = PageConfiguration.MARGIN_LEFT;
            int rect_yPosition = myCanvas.getHeight() - 120;
            int footerHeight = 80;
            myCanvas.drawRect(rect_xPosition, rect_yPosition, PageConfiguration.PAGE_WIDTH -
                    PageConfiguration.MARGIN_RIGHT, rect_yPosition + footerHeight, getFooterPaint
                    ());


            //write patient information
            int patient_X = PageConfiguration.MARGIN_LEFT + (PageConfiguration.MARGIN_LEFT / 2);
            int patient_Y = rect_yPosition + (PageConfiguration.MARGIN_TOP / 2) + 10;
            myCanvas.drawText("Reacher, Jack", patient_X, patient_Y, getWhiteSmallPaint());
            Date time = Calendar.getInstance().getTime();

            patient_Y += PageConfiguration.SPACING_BETWEEN_LINES_MEDIUM;
            myCanvas.drawText(PageConfiguration.simpleDateFormat.format(time),
                    patient_X,
                    patient_Y,
                    getWhiteSmallPaint());
            patient_Y += PageConfiguration.SPACING_BETWEEN_LINES_MEDIUM;
            myCanvas.drawText("Mobile Serial Number",
                    patient_X,
                    patient_Y,
                    getWhiteSmallPaint());


            // dark footer
            int two_third = PageConfiguration.PAGE_WIDTH - PageConfiguration
                    .MARGIN_RIGHT - PageConfiguration.MARGIN_LEFT;
            two_third /= 3;
            int rect_med_X = (2 * two_third) + PageConfiguration.MARGIN_LEFT;
            int rect_med_Y = myCanvas.getHeight() - 120;
            Paint med_Paint = new Paint();
            med_Paint.setColor(PageConfiguration.COLOR_BLUE_HIGH);
            myCanvas.drawRect(rect_med_X, rect_med_Y, PageConfiguration.PAGE_WIDTH -
                    PageConfiguration.MARGIN_RIGHT, rect_yPosition + footerHeight, med_Paint);

            //write medtronic
            int med_text_X = rect_med_X + 50;
            int med_text_Y = rect_med_Y + (footerHeight / 2);
            myCanvas.drawText("Medtronic", med_text_X, med_text_Y,
                    getWhiteBoldColorPaint());


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_MOVIES),
                        "test_103.pdf");
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
    }

    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        return paint;
    }

    private Paint getHeaderPaint() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_DARK);
        paint.setTextSize(PageConfiguration.HEADER_FONT_SIZE);
        paint.setFakeBoldText(true);
        return paint;
    }

    private Paint getPageNumberHeaderPaint() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_HIGH);
        paint.setTextSize(PageConfiguration.NORMAL_FONT_SIZE);
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setFakeBoldText(true);
        return paint;
    }

    private Paint getFooterPaint() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        return paint;
    }

    private Paint getWhiteSmallPaint() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_WHITE);
        paint.setTextSize(PageConfiguration.SMALL_FONT_SIZE);
        return paint;
    }

    private Paint getWhiteBoldColorPaint() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_WHITE);
        paint.setTextSize(PageConfiguration.NORMAL_FONT_SIZE);
        paint.setFakeBoldText(true);
        return paint;
    }
}
