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
import kz.almatherm.mobile.Const;
import kz.almatherm.mobile.R;
import kz.almatherm.mobile.model.Category;
import kz.almatherm.mobile.module.category.SubCategoryActivity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;
    private Context context;

    public CategoryAdapter(List<Category> categories, Context context) {
        this.categories = categories;
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
        Category category = categories.get(i);
        Picasso.get()
                .load(Const.BASE_URL + category.getImage())
                .resize(160, 160)
                .into(viewHolder.category_image);

        viewHolder.title.setText(category.getName());

        viewHolder.view.setOnClickListener(v -> context.startActivity(new Intent(context, SubCategoryActivity.class)
                .putExtra("category_id", category.getId())));
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
