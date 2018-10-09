package kz.almatherm.mobile.module.service;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.almatherm.mobile.Const;
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.module.sub_service.SubServiceActivity;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    List<Service> services;
    Context context;

    public ServiceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Service service = services.get(i);
        Picasso.get()
                .load(Const.BASE_URL + service.getImage())
                .resize(160, 160)
                .into(viewHolder.image);

        viewHolder.title.setText(service.getName());

        viewHolder.view.setOnClickListener(v -> context.startActivity(new Intent(context, SubServiceActivity.class)
                .putExtra("service_id", service.getId())));
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        View view;
        @BindView(R.id.category_image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
