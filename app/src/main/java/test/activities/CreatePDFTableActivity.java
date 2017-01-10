package test.activities;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pankajnimgade.pankajtestapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CreatePDFTableActivity extends AppCompatActivity {

    private Button pdf_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdftable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CreatePDFTableActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        pdf_Button = (Button)findViewById(R.id.CreatePDFTableActivity_open_pdf_button);
        pdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePDFTableActivity.this, "Creating table...", Toast.LENGTH_SHORT)
                        .show();
                new CreatePDFTable().execute();

            }
        });
    }

    private class CreatePDFTable extends AsyncTask<Void, Void, Void>{

        private Canvas myCanvas;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_MOVIES),
                    "test_102.pdf");
            test.deleteOnExit();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_MOVIES),
                        "test_102.pdf");
                test.createNewFile();

                PdfDocument pdfDocument = new PdfDocument();
                PdfDocument.PageInfo pageInfo =
                        new PdfDocument.PageInfo.Builder(612, 792, 1).create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                myCanvas = page.getCanvas();
                myCanvas.drawText("SYNCHROMED II®", 40,40, getHeaderPaint());
                myCanvas.drawText("Dosing Report", 40,40+25, getHeaderPaint());
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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_MOVIES),
                        "test_102.pdf");
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
        paint.setColor(Color.GRAY);
        paint.setTextSize(20);
        paint.setFakeBoldText(true);
        return paint;
    }
}
