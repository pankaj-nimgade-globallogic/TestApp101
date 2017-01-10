package test.activities;

import android.content.Intent;
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

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;

public class OpenPDFActivity extends AppCompatActivity {

    private Button openPDF_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.OpenPDFActivity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        openPDF_Button = (Button) findViewById(R.id.OpenPDFActivity_open_pdf_button);
        openPDF_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OpenPDFActivity.this, "Open PDF", Toast.LENGTH_SHORT).show();

                openPDF_File();
            }
        });
    }

    private void openPDF_File() {
        new CreatePDF().execute();
    }

    private class CreatePDF extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                InputStream open = getAssets().open("text_doc.pdf");
                File test = new File(Environment.getExternalStoragePublicDirectory(Environment
                        .DIRECTORY_MOVIES),
                        "test.pdf");
                test.createNewFile();
                FileUtils.copyInputStreamToFile(open, test);
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
}
