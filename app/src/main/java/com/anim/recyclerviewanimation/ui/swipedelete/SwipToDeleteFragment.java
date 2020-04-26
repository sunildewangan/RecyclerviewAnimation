package com.anim.recyclerviewanimation.ui.swipedelete;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.anim.recyclerviewanimation.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SwipToDeleteFragment extends Fragment {

    private SwipToDeleteViewModel mViewModel;
    private RecyclerView recyclerView;
    private SwipToDeleteAdapter swipToDeleteAdapter;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ConstraintLayout coordinatorLayout;
    private View rootView;

    public static SwipToDeleteFragment newInstance() {
        return new SwipToDeleteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.swip_to_delete_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SwipToDeleteViewModel.class);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        coordinatorLayout = rootView.findViewById(R.id.constraint_layout);

        populateRecyclerView();
        enableSwipeToDeleteAndUndo();
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipToDeleteCallback swipeToDeleteCallback = new SwipToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final String item = swipToDeleteAdapter.getData().get(position);

                swipToDeleteAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        swipToDeleteAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    private void populateRecyclerView() {
        stringArrayList.add("Item 1");
        stringArrayList.add("Item 2");
        stringArrayList.add("Item 3");
        stringArrayList.add("Item 4");
        stringArrayList.add("Item 5");
        stringArrayList.add("Item 6");
        stringArrayList.add("Item 7");
        stringArrayList.add("Item 8");
        stringArrayList.add("Item 9");
        stringArrayList.add("Item 10");

        swipToDeleteAdapter = new SwipToDeleteAdapter(stringArrayList);
        recyclerView.setAdapter(swipToDeleteAdapter);
    }

}
