package system.utils.pack110;

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
import system.utils.pack109.PdfPageCreator109;
import test.activities.CreatePDF109Activity;

public class CreatePDF110Activity extends AppCompatActivity {

    private Button pdf_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdf110);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CreatePDF110Activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        pdf_Button = (Button) findViewById(R.id.CreatePDF110Activity_open_pdf_button);
        pdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePDF110Activity.this, "Creating pdf...", Toast.LENGTH_SHORT)
                        .show();
                try {
                    new PdfPageCreator110(CreatePDF110Activity.this).execute(MyBlock110List
                                .getList());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
