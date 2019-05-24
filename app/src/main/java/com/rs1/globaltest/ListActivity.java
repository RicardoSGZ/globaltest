package com.rs1.globaltest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rec_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        rv.setHasFixedSize(true);
        Context context = this;
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        List<Person> persons = new ArrayList<>();
        initializeData(persons);

        MyAdapter adapter = new MyAdapter(persons);
        rv.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        return true;
    }

    class Person {
        String name;
        String location;

        Person(String name, String location) {
            this.name = name;
            this.location = location;

        }
    }

    private void initializeData(List<Person> persons){
        persons.add(new Person("Ricardo Sanz", "Huesca"));
        persons.add(new Person("Jorge Rodriguez", "Zaragoza"));
        persons.add(new Person("Pablo Escobar", "Zaragoza"));
    }




}
