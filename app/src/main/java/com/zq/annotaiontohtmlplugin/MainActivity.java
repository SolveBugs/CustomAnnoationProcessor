package com.zq.annotaiontohtmlplugin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zq.annoation.DescAnnotation;

public class MainActivity extends AppCompatActivity {

    @DescAnnotation(title = "test_str_one", desc = "我是第一个变量的描述")
    private String testStrOne = "test_str_one";
    @DescAnnotation(title = "test_str_two", desc = "我是第二个变量的描述")
    private String testStrTwo = "test_str_two";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}