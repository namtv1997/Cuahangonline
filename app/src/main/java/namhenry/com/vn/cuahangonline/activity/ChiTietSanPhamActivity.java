package namhenry.com.vn.cuahangonline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import namhenry.com.vn.cuahangonline.R;
import namhenry.com.vn.cuahangonline.model.Giohang;
import namhenry.com.vn.cuahangonline.model.Sanpham;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    Toolbar toolbarchitiet;
    ImageView imgchitiet;
    TextView txtten, txtgia, txtmota;
    Spinner spinner;
    Button btndatmua;
    int id = 0;
    String TenChitiet = "";
    int GiaChitiet = 0;
    String HinhanhChitiet = "";
    String MotaChitiet = "";
    int Idsanpham = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent=new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() > 0) {
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists =false;
                    for (int i=0;i<MainActivity.manggiohang.size();i++){
                        if (MainActivity.manggiohang.get(i).getIdsp()==id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if (MainActivity.manggiohang.get(i).getSoluongsp()>=10){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(GiaChitiet* MainActivity.manggiohang.get(i).getSoluongsp());
                            exists=true;
                        }
                    }
                    if (exists==false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * GiaChitiet;
                        MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                    }

                } else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * GiaChitiet;
                    MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));

                }
                Intent intent=new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayadapter);
    }

    private void GetInformation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getID();
        TenChitiet = sanpham.getTensanpham();
        GiaChitiet = sanpham.getGiasanpham();
        HinhanhChitiet = sanpham.getHinhanhsanpham();
        MotaChitiet = sanpham.getMotasanpham();
        Idsanpham = sanpham.getIDSanpham();
        txtten.setText(TenChitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá :" + decimalFormat.format(GiaChitiet) + "Đ");
        txtmota.setText(MotaChitiet);
        Picasso.with(getApplicationContext()).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.noimage)
                .error(R.drawable.error).into(imgchitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitiet = findViewById(R.id.toolbarchitietsanpham);
        imgchitiet = findViewById(R.id.imageviewchitietsanpham);
        txtten = findViewById(R.id.textviewtenchitietsanpham);
        txtgia = findViewById(R.id.textviewgiachitietsanpham);
        txtmota = findViewById(R.id.textviewmotachitietsanpham);
        btndatmua = findViewById(R.id.buttondatmua);
        spinner = findViewById(R.id.spinner);


    }
}
