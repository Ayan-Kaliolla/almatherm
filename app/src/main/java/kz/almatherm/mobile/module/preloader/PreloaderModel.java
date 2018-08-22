package kz.almatherm.mobile.module.preloader;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kz.almatherm.mobile.model.Category;

public class PreloaderModel {

    private static final String TAG = "PreloaderModel";
    private PreloaderPresenter presenter;

    PreloaderModel(PreloaderPresenter preloaderPresenter) {
        presenter = preloaderPresenter;
    }


    public void loadData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child("categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Category> categories = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Category category = snapshot.getValue(Category.class);
                    categories.add(category);
                }
                presenter.onDataLoaded(categories);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails());
            }
        });
    }
}
