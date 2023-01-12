package com.example.quizit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<CategoryModel> cat_List;

    public CategoryAdapter(List<CategoryModel> cat_List) {
        this.cat_List = cat_List;
    }

    @Override
    public int getCount() {
        return cat_List.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView;

        if(view == null){
            myView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cat_item_layout, viewGroup, false);
        }
        else{
            myView = view;
        }

        return myView;
    }
}
