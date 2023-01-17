package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private RecyclerView testView;
    private Toolbar toolbar;
    private List<TestModel> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        testView = findViewById(R.id.test_recycler_view);

        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar (toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        int cat_index = getIntent().getIntExtra("CAT INDEX", 0);

        getSupportActionBar().setTitle(CategoryFragment.catList.get(cat_index).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        testView.setLayoutManager(layoutManager);


        loadTestData();



    }

    private void loadTestData(){

        testList = new ArrayList<>();

        testList.add(new TestModel("1", 50, 20));
        testList.add(new TestModel("2", 80, 25));
        testList.add(new TestModel("3", 0, 20));
        testList.add(new TestModel("4", 10, 40));

    }
}