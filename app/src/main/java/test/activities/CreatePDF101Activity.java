package test.activities;

import android.content.Intent;
import android.graphics.Canvas;
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

public class CreatePDF101Activity extends AppCompatActivity {

    private Button createPDF_Button;
    private Canvas myCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdf101);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CreatePDF101Activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        createPDF_Button = (Button) findViewById(R.id.CreatePDF101Activity_open_pdf_button);
        createPDF_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePDF101Activity.this, "Creating...", Toast.LENGTH_SHORT)
                        .show();
                new CreatePDF101().execute();
            }
        });
    }

    private class CreatePDF101 extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                    .DIRECTORY_MOVIES),
                    "test_101.pdf");
            test.deleteOnExit();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_MOVIES),
                        "test_101.pdf");
                test.createNewFile();

                PdfDocument pdfDocument = new PdfDocument();
                PdfDocument.PageInfo pageInfo =
                        new PdfDocument.PageInfo.Builder(612, 792, 1).create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);
                myCanvas = page.getCanvas();
                myCanvas.drawText("SYNCHROMED II®", 50, 40, getHeaderPaint());
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
                        "test_101.pdf");
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
        return new Paint();
    }

    private Paint getHeaderPaint() {
        Paint paint = new Paint();
        paint.setColor(0xFF2B3A52);
        paint.setTextSize(20);
        paint.setFakeBoldText(true);
        return paint;
    }
}
