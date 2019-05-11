package namhenry.com.vn.cuahangonline.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import namhenry.com.vn.cuahangonline.R;

public class LienHeActivity extends AppCompatActivity {
    Toolbar toolbarlienhe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        toolbarlienhe=findViewById(R.id.toolbarlienhe);
        ActionBar();
    }

    private void ActionBar() {
        setSupportActionBar(toolbarlienhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlienhe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void phone(View view) {
        Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:0986508018"));
        startActivity(intent);

    }
}
