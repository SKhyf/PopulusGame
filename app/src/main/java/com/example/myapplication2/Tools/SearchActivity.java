package com.example.myapplication2.Tools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication2.FilterListener;
import com.example.myapplication2.GameinfoActivity;
import com.example.myapplication2.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText et_ss;
    private ListView lsv_ss;
    private List<String> list = new ArrayList<String>();
    boolean isFilter;
    private MyAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main);
        setViews();// 控件初始化
        setData();// 给listView设置adapter
        setListeners();// 设置监听
        ImageView back_btn = (ImageView)findViewById(R.id.search_back);
        back_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
    private void setData() {
        initData();// 初始化数据

        // 这里创建adapter的时候，构造方法参数传了一个接口对象，这很关键，回调接口中的方法来实现对过滤后的数据的获取
        adapter = new MyAdapter(list, this, new FilterListener() {
            // 回调方法获取过滤后的数据
            public void getFilterData(List<String> list) {
                // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
                Log.e("TAG", "接口回调成功");
                Log.e("TAG", list.toString());
                setItemClick(list);
            }
        });
        lsv_ss.setAdapter(adapter);
    }

    /**
     * 给listView添加item的单击事件
     * @param filter_lists  过滤后的数据集
     */
    protected void setItemClick(final List<String> filter_lists) {
        lsv_ss.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 点击对应的item时，弹出toast提示所点击的内容
                Intent intent = new Intent(SearchActivity.this.getBaseContext(), GameinfoActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 简单的list集合添加一些测试数据
     */
    private void initData() {
        list.add("看着飞舞的尘埃   掉下来");
        list.add("没人发现它存在");
        list.add("多自由自在");
        list.add("可世界都爱热热闹闹");
        list.add("容不下   我百无聊赖");
        list.add("不应该   一个人 发呆");
        list.add("只有我   守着安静的沙漠");
        list.add("等待着花开");
        list.add("只有我   看着别人的快乐");
    }

    private void setListeners() {
        // 没有进行搜索的时候，也要添加对listView的item单击监听
        setItemClick(list);

        /**
         * 对编辑框添加文本改变监听，搜索的具体功能在这里实现
         * 很简单，文本该变的时候进行搜索。关键方法是重写的onTextChanged（）方法。
         */
        et_ss.addTextChangedListener(new TextWatcher() {

            /**
             *
             * 编辑框内容改变的时候会执行该方法
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
                if(adapter != null){
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    /**
     * 控件初始化
     */
    private void setViews() {
        et_ss = (EditText) findViewById(R.id.et_ss);// EditText控件
        lsv_ss = (ListView)findViewById(R.id.lsv_ss);// ListView控件
    }
}