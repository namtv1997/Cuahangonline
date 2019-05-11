package namhenry.com.vn.cuahangonline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

import namhenry.com.vn.cuahangonline.R;
import namhenry.com.vn.cuahangonline.adapter.GiohangAdapter;
import namhenry.com.vn.cuahangonline.ultil.CheckConnection;

public class GiohangActivity extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan, btntieptucmua;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();
        CheckData();
        EvenUltil();
        CatchOnItemListView();
        EventButton();
    }

    private void Anhxa() {
        lvgiohang =  findViewById(R.id.listviewgiohang);
        txtthongbao =  findViewById(R.id.textviewthongbao);
        txttongtien = findViewById(R.id.textviewtongtien);
        btnthanhtoan =  findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmua =  findViewById(R.id.buttontieptucmuahang);
        toolbargiohang =  findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this,MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }
    public static void EvenUltil() {
        long tongtien = 0;
        for (int i = 0; i<MainActivity.manggiohang.size();i++){
            tongtien +=MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien) + "Đ");
    }
    private void EventButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() >0){
                    Intent intent = new Intent(getApplicationContext(),ThongTinKhachHangActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.showToast_Short(getApplicationContext(),"Giỏ hàng chưa có sản phẩm");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                builder.setTitle("Xóa sản phẩm");
                builder.setMessage("Bạn có muốn xóa sản phẩm");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MainActivity.manggiohang.size() <= 0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if (MainActivity.manggiohang.size() <=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void CheckData() {
        if (MainActivity.manggiohang.size() <= 0){
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
