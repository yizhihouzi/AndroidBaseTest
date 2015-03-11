package com.example.bigbigboy.xmlparser;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.bigbigboy.Adapter.ListViewAdapter;
import com.example.bigbigboy.bean.PersonBean;
import com.example.xml.DOMOperate.DOM_Person;
import com.example.xml.PULLOperate.PULL_Person;
import com.example.xml.SAXOperate.SAX_Person;
import com.example.xml.XmlOperate;

import java.io.InputStream;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
    }

    public void onParseClick(View view) throws Exception {
        //XmlOperate xmlOperate = new SAX_Person();
        //XmlOperate xmlOperate = new DOM_Person();
        XmlOperate xmlOperate = new PULL_Person();
        List<PersonBean> list = xmlOperate.xmlParse(getResources().getAssets().open("persons.xml"));
        listViewAdapter = new ListViewAdapter(this, list);
        listView.setAdapter(listViewAdapter);
    }

    public void onSaveClick(View view) throws Exception {
        XmlOperate xmlOperate = new SAX_Person();
        //XmlOperate xmlOperate = new DOM_Person();
        //XmlOperate xmlOperate = new PULL_Person();
        List<PersonBean> list = xmlOperate.xmlParse(getResources().getAssets().open("persons.xml"));
       Log.d("xmlSerialize",xmlOperate.xmlSerialize(list)) ;
    }
}
