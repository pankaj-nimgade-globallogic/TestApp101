package test.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pankajnimgade.pankajtestapp.R;

import java.util.ArrayList;
import java.util.List;

import system.utils.pack109.MyBlock109;
import system.utils.pack109.MyTable109;
import system.utils.pack109.PdfPageCreator109;

public class CreatePDF109Activity extends AppCompatActivity {

    private Button pdf_Button;
    private static final String FILE_NAME = "test_109.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdf109);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CreatePDF109Activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        pdf_Button = (Button) findViewById(R.id.CreatePDF109Activity_open_pdf_button);
        pdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePDF109Activity.this, "Creating pdf...", Toast.LENGTH_SHORT)
                        .show();
                List<MyBlock109> myBlock109s = new ArrayList<MyBlock109>();


                new PdfPageCreator109(CreatePDF109Activity.this).execute(myBlock109s);
            }
        });
    }
}
