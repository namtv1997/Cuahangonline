package namhenry.com.vn.cuahangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import namhenry.com.vn.cuahangonline.R;
import namhenry.com.vn.cuahangonline.activity.ChiTietSanPhamActivity;
import namhenry.com.vn.cuahangonline.model.Sanpham;
import namhenry.com.vn.cuahangonline.ultil.CheckConnection;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override

    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sanpham sanpham = arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá :" + decimalFormat.format(sanpham.getGiasanpham()) + "Đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imghinhsanpham;
        public TextView txttensanpham, txtgiasanpham;

        public ItemHolder(View itemView) {
            super(itemView);
            imghinhsanpham = itemView.findViewById(R.id.imagesanpham);
            txtgiasanpham = itemView.findViewById(R.id.textviewgiasanpham);
            txttensanpham = itemView.findViewById(R.id.textviewtensanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,ChiTietSanPhamActivity.class);
                    intent.putExtra("thongtinsanpham",arraysanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.showToast_Short(context,arraysanpham.get(getPosition()).getTensanpham());
                    context.startActivity(intent);
                }
            });
        }
    }
}
