package com.example.xml.PULLOperate;

import android.util.Xml;

import com.example.bigbigboy.bean.PersonBean;
import com.example.xml.XmlOperate;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigBigBoy on 2015/3/11.
 */
public class PULL_Person implements XmlOperate {
    @Override
    public List<PersonBean> xmlParse(InputStream is) throws Exception {
        List<PersonBean> persons = null;
        PersonBean person = null;

        //XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        //XmlPullParser parser = factory.newPullParser();

        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    persons = new ArrayList<PersonBean>();
                    break;
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("person")) {
                        person = new PersonBean();
                    } else if (parser.getName().equals("id")) {
                        eventType = parser.next();
                        person.setId(Integer.parseInt(parser.getText()));
                    } else if (parser.getName().equals("name")) {
                        eventType = parser.next();
                        person.setName(parser.getText());
                    } else if (parser.getName().equals("sex")) {
                        eventType = parser.next();
                        person.setSex(parser.getText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("person")) {
                        persons.add(person);
                        person = null;
                    }
                    break;
            }
            eventType = parser.next();
        }
        return persons;
    }

    @Override
    public String xmlSerialize(List<PersonBean> persons) throws Exception {
        //XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        //XmlSerializer serializer = factory.newSerializer();

        XmlSerializer serializer = Xml.newSerializer(); //由android.util.Xml创建一个XmlSerializer实例
        StringWriter writer = new StringWriter();
        serializer.setOutput(writer);   //设置输出方向为writer  
        serializer.startDocument("UTF-8", true);
        serializer.startTag("", "persons");
        for (PersonBean person : persons) {
            serializer.startTag("", "person");
            serializer.attribute("", "id", person.getId() + "");

            serializer.startTag("", "name");
            serializer.text(person.getName());
            serializer.endTag("", "name");

            serializer.startTag("", "sex");
            serializer.text(person.getSex() + "");
            serializer.endTag("", "sex");

            serializer.endTag("", "person");
        }
        serializer.endTag("", "persons");
        serializer.endDocument();

        return writer.toString();
    }
}
