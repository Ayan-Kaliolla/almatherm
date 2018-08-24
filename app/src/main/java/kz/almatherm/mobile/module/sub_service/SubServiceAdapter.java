package kz.almatherm.mobile.module.sub_service;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.almatherm.mobile.Const;
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.SubService;

public class SubServiceAdapter extends RecyclerView.Adapter<SubServiceAdapter.ViewHolder> {

    private List<SubService> subServices;
    private Context context;

    public SubServiceAdapter(Context context) {
        this.context = context;
        subServices = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sub_service, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SubService subService = this.subServices.get(i);
        Picasso.get()
                .load(Const.BASE_URL + subService.getImage())
                .resize(160, 160)
                .into(viewHolder.image);

        viewHolder.title.setText(subService.getTitle());

//        viewHolder.view.setOnClickListener(v -> context.startActivity(new Intent(context, SubServiceActivity.class)
//                .putExtra("service_id", subService.getId())));

        viewHolder.price.setText(subService.getPrice().replaceAll("\\s+", ""));
        viewHolder.stock.setText(subService.getStock().replaceAll("\\s+", ""));
    }

    @Override
    public int getItemCount() {
        return subServices.size();
    }

    public void setSubServices(List<SubService> subServices) {
        this.subServices = subServices;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        View view;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.stock)
        TextView stock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
