package system.utils.pack111;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pankajnimgade.pankajtestapp.R;

import system.utils.pack110.CreatePDF110Activity;
import system.utils.pack110.MyBlock110List;
import system.utils.pack110.PdfPageCreator110;

public class CreatePDF111Activity extends AppCompatActivity {

    private Button pdf_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pdf111);
        Toolbar toolbar = (Toolbar) findViewById(R.id.CreatePDF111Activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initializeUI();
    }

    private void initializeUI() {
        pdf_Button = (Button) findViewById(R.id.CreatePDF111Activity_open_pdf_button);
        pdf_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreatePDF111Activity.this, "Creating pdf...", Toast.LENGTH_SHORT)
                        .show();
                try {
                    new PdfPageCreator111(CreatePDF111Activity.this).execute(MyBlock111List.getList());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
