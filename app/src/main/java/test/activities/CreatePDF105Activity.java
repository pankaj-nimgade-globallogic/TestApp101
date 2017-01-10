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

import system.utils.PageConfiguration;

public class CreatePDF105Activity extends AppCompatActivity {

    private Button pdf_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdf105);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CreatePDF105Activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        pdf_Button = (Button) findViewById(R.id.CreatePDF105Activity_open_pdf_button);
        pdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePDF105Activity.this, "Creating pdf...", Toast.LENGTH_SHORT)
                        .show();
                new CreatePDF105().execute();
            }
        });
    }

    private class CreatePDF105 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_MOVIES),
                    "test_105.pdf");
            test.deleteOnExit();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_MOVIES),
                        "test_105.pdf");
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
                        "test_105.pdf");
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

    }

    private void writeFooter(Canvas myCanvas, PdfDocument.Page page) {
        int footer_left = PageConfiguration.MARGIN_LEFT;
        int footer_top = PageConfiguration.PAGE_HEIGHT - PageConfiguration.MARGIN_BOTTOM -
                (PageConfiguration.MARGIN_BOTTOM / 2);
        int footer_right = PageConfiguration.PAGE_WIDTH - PageConfiguration.MARGIN_RIGHT;
        int footer_bottom = PageConfiguration.PAGE_HEIGHT - PageConfiguration.MARGIN_BOTTOM;
        Rect rect_first = new Rect(footer_left, footer_top, footer_right, footer_bottom);

        // light color
        myCanvas.drawRect(rect_first, getFooterPaintMediumColor());

        int width = (rect_first.right - rect_first.left) * (2 / 3);
        footer_left = PageConfiguration.MARGIN_LEFT + width;
        Rect rect_second = new Rect(footer_left, footer_top, footer_right, footer_bottom);

        myCanvas.drawRect(rect_second, getFooterPaintHighColor());


    }


    private Paint getFooterPaintMediumColor() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_MEDIUM);
        return paint;
    }

    private Paint getFooterPaintHighColor() {
        Paint paint = new Paint();
        paint.setColor(PageConfiguration.COLOR_BLUE_HIGH);
        return paint;
    }
}
