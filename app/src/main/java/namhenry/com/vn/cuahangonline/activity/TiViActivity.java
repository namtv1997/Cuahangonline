package namhenry.com.vn.cuahangonline.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import namhenry.com.vn.cuahangonline.R;
import namhenry.com.vn.cuahangonline.adapter.TiviAdapter;
import namhenry.com.vn.cuahangonline.model.Sanpham;
import namhenry.com.vn.cuahangonline.ultil.CheckConnection;
import namhenry.com.vn.cuahangonline.ultil.Server;

public class TiViActivity extends AppCompatActivity {
    Toolbar toolbartivi;
    ListView lvtivi;
    TiviAdapter tiviAdapter;
    ArrayList<Sanpham> mangtivi;
    int idtivi = 0;
    int page = 1;
    View footerview;
    boolean isLoading = false;
    mHandler mHandler;
    boolean limitData = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_vi);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {

            GetIdLoaisp();
            ActionToolBar();
            GetData(page);
            LoadMoredata();
        } else {
            CheckConnection.showToast_Short(getApplicationContext(), "ban hay kiam tra lai ket noi");
            finish();
        }
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

    private void LoadMoredata() {
        lvtivi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham", mangtivi.get(i));
                startActivity(intent);
            }
        });
        lvtivi.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if (FirstItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitData == false) {
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();

                }

            }
        });
    }

    private void Anhxa() {
        toolbartivi = findViewById(R.id.toolbartivi);
        lvtivi = findViewById(R.id.listviewtivi);
        mangtivi = new ArrayList<>();
        tiviAdapter = new TiviAdapter(getApplicationContext(), mangtivi);
        lvtivi.setAdapter(tiviAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHandler = new mHandler();
    }

    private void GetIdLoaisp() {
        idtivi = getIntent().getIntExtra("idloaisanpham1", -1);

    }

    private void ActionToolBar() {
        setSupportActionBar(toolbartivi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbartivi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdantivi + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tentivi = "";
                int Giativi = 0;
                String Hinhanhtivi = "";
                String Motativi = "";
                int Idsptivi = 0;
                if (response != null && response.length() != 2) {
                    lvtivi.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tentivi = jsonObject.getString("tensp");
                            Giativi = jsonObject.getInt("giasp");
                            Hinhanhtivi = jsonObject.getString("hinhanhsp");
                            Motativi = jsonObject.getString("motasp");
                            Idsptivi = jsonObject.getInt("idsanpham");
                            mangtivi.add(new Sanpham(id, Tentivi, Giativi, Hinhanhtivi, Motativi, Idsptivi));
                            tiviAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitData = true;
                    lvtivi.removeFooterView(footerview);
                    CheckConnection.showToast_Short(getApplicationContext(), "da het du lieu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(idtivi));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    lvtivi.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}
