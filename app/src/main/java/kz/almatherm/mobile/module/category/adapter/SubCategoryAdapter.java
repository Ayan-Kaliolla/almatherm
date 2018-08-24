package kz.almatherm.mobile.module.category.adapter;

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
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.SubCategory;
import kz.almatherm.mobile.module.service.ServiceActivity;

import static kz.almatherm.mobile.Const.BASE_URL;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private List<SubCategory> categories;
    private Context context;

    public SubCategoryAdapter(List<SubCategory> subCategories, Context context) {
        this.categories = subCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SubCategory subCategory = categories.get(i);
        Picasso.get()
                .load(BASE_URL + subCategory.getImage())
                .resize(160, 160)
                .into(viewHolder.category_image);

        viewHolder.title.setText(subCategory.getName());

        viewHolder.view.setOnClickListener(v -> context.startActivity(new Intent(context, ServiceActivity.class)
                .putExtra("subcategory_id", subCategory.getId())));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.root)
        View view;

        @BindView(R.id.category_image)
        ImageView category_image;

        @BindView(R.id.title)
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
