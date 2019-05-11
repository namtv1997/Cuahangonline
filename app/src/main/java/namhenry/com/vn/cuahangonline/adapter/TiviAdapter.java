package namhenry.com.vn.cuahangonline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import namhenry.com.vn.cuahangonline.R;
import namhenry.com.vn.cuahangonline.model.Sanpham;

public class TiviAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraytivi;

    public TiviAdapter(Context context, ArrayList<Sanpham> arraytivi) {
        this.context = context;
        this.arraytivi = arraytivi;
    }

    @Override
    public int getCount() {
        return arraytivi.size();
    }

    @Override
    public Object getItem(int i) {
        return arraytivi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView txttentivi, txtgiativi, txtmotativi;
        public ImageView imgtivi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_tivi, null);
            viewHolder.txttentivi = view.findViewById(R.id.textviewtentivi);
            viewHolder.txtgiativi = view.findViewById(R.id.textviewgiativi);
            viewHolder.txtmotativi = view.findViewById(R.id.textviewmotativi);
            viewHolder.imgtivi = view.findViewById(R.id.imageviewtivi);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham= (Sanpham) getItem(i);
        viewHolder.txttentivi.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiativi.setText("Giá :" + decimalFormat.format(sanpham.getGiasanpham()) + "Đ");
        viewHolder.txtmotativi.setMaxLines(2);
        viewHolder.txtmotativi.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotativi.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.noimage)
                .error(R.drawable.error).into(viewHolder.imgtivi);
        return view;
    }
}
