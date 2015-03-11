package com.example.xml.SAXOperate;

import com.example.bigbigboy.bean.PersonBean;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigBigBoy on 2015/3/11.
 */
public class SAXPersonHandler extends DefaultHandler {

    private List<PersonBean> persons;
    private PersonBean person;
    private StringBuilder builder;

    //返回解析后得到的Person对象集合  
    public List<PersonBean> getPersons() {
        return persons;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        persons = new ArrayList<PersonBean>();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (localName.equals("person")) {
            person = new PersonBean();
        }
        builder.setLength(0);   //将字符长度设置为0 以便重新开始读取元素内的字符节点  
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);  //将读取的字符数组追加到builder中  
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (localName.equals("id")) {
            person.setId(Integer.parseInt(builder.toString()));
        } else if (localName.equals("name")) {
            person.setName(builder.toString());
        } else if (localName.equals("sex")) {
            person.setSex(builder.toString());
        } else if (localName.equals("person")) {
            persons.add(person);
        }
    }
} 
