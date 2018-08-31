package kz.almatherm.mobile.module.preloader;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kz.almatherm.mobile.model.Category;
import kz.almatherm.mobile.model.MapItem;
import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.SubCategory;
import kz.almatherm.mobile.model.SubService;

public class PreloaderModel {

    private static final String TAG = "PreloaderModel";
    private PreloaderPresenter presenter;

    PreloaderModel(PreloaderPresenter preloaderPresenter) {
        presenter = preloaderPresenter;
    }


    public void loadData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        DatabaseReference reference = database.getReference();
        reference.child("categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Category> categories = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Category category = snapshot.getValue(Category.class);
                    categories.add(category);
//                    Log.d("Category", category.getName());
                    Log.d("Category", category.getId() + "");
                    for (SubCategory subCategory : category.getSubCatalogs()) {
                        subCategory.setParentId(category.getId());
//                        Log.d("SubCategory", subCategory.getName());
//                        Log.d("SubCategory", subCategory.getId() + "");
                        if (subCategory.getServices() != null)
                            for (Service service : subCategory.getServices()) {
                                service.setParentId(subCategory.getId());
//                                Log.d("Service", service.getName());
//                            Log.d("Service", service.getId() + "");
                                if (service.getSubServices() != null)
                                    for (SubService subService : service.getSubServices()) {
//                                    Log.d("SubService", subService.getTitle());
//                                    Log.d("SubService", subService.getId() + "");
                                        subService.setParentId(service.getId());
                                    }
                            }
                    }
                }
                presenter.onCategoryLoaded(categories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails());
            }
        });

        reference.child("map_items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MapItem> mapItems = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MapItem mapItem= snapshot.getValue(MapItem.class);
                    mapItems.add(mapItem);
                }
                presenter.onMapItemsLoaded(mapItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails());
            }
        });
    }
}
