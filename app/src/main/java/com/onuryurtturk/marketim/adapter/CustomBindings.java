package com.onuryurtturk.marketim.adapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import net.cachapa.expandablelayout.ExpandableLayout;

public class CustomBindings {

    //Includes custom binder methods for custom attributes
    /**
     * recyclerview custom binding method to set adapter
     * @param recyclerView view reference
     * @param adapter created adapter
     */
    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    /**
     * expandable layout custom binding method to change expantion state
     * @param expandableLayout view reference
     * @param expandState expantion state reference
     */
    @BindingAdapter("el_expanded")
    public static void setExpanded(ExpandableLayout expandableLayout, boolean expandState) {
        expandableLayout.setExpanded(expandState,false);
    }

}

