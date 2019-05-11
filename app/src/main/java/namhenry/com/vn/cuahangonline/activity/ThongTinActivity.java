package namhenry.com.vn.cuahangonline.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import namhenry.com.vn.cuahangonline.R;

public class ThongTinActivity extends AppCompatActivity {
TextView txtlienhe;
Toolbar toolbarthongtin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        txtlienhe=findViewById(R.id.textviewlienhe);
        toolbarthongtin=findViewById(R.id.toolbarthongtin);
        setSupportActionBar(toolbarthongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void lienhefb(View view) {
        Intent intent=new Intent(getApplicationContext(),WebviewActivity.class);
        startActivity(intent);
    }
}
