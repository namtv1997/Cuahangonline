package namhenry.com.vn.cuahangonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import namhenry.com.vn.cuahangonline.R;
import namhenry.com.vn.cuahangonline.model.Loaisp;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListloaisp;
    Context context;
    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;
    }

    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txttenloaisp=view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp=view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Loaisp loaisp= (Loaisp) getItem(i);
        viewHolder.txttenloaisp.setText(loaisp.getTenLoaisp());
        Picasso.with(context).load(loaisp.getHinhanhloaisp()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.imgloaisp);
        return view;

    }
}
