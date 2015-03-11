package com.example.xml.SAXOperate;

import com.example.bigbigboy.bean.PersonBean;
import com.example.xml.XmlOperate;

import org.xml.sax.helpers.AttributesImpl;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by BigBigBoy on 2015/3/11.
 */
public class SAX_Person implements XmlOperate {
    @Override
    public List<PersonBean> xmlParse(InputStream is) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();  //取得SAXParserFactory实例
        SAXParser parser = factory.newSAXParser();                  //从factory获取SAXParser实例
        SAXPersonHandler handler = new SAXPersonHandler();            //实例化自定义Handler
        parser.parse(is, handler);                                  //根据自定义Handler规则解析输入流
        return handler.getPersons();
    }

    @Override
    public String xmlSerialize(List<PersonBean> persons) throws Exception {
        SAXTransformerFactory factory = (SAXTransformerFactory) TransformerFactory.newInstance();//取得SAXTransformerFactory实例  
        TransformerHandler handler = factory.newTransformerHandler();           //从factory获取TransformerHandler实例  
        Transformer transformer = handler.getTransformer();                     //从handler获取Transformer实例  
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");            // 设置输出采用的编码方式  
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");                // 是否自动添加额外的空白  
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");   // 是否忽略XML声明  

        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        handler.setResult(result);

        String uri = "";    //代表命名空间的URI 当URI无值时 须置为空字符串  
        String localName = "";  //命名空间的本地名称(不包含前缀) 当没有进行命名空间处理时 须置为空字符串  

        handler.startDocument();
        handler.startElement(uri, localName, "persons", null);

        AttributesImpl attrs = new AttributesImpl();    //负责存放元素的属性信息  
        char[] ch = null;
        for (PersonBean person : persons) {
            attrs.clear();  //清空属性列表  
            attrs.addAttribute(uri, localName, "id", "string", String.valueOf(person.getId()));//添加一个名为id的属性(type影响不大,这里设为string)  
            handler.startElement(uri, localName, "person", attrs);    //开始一个person元素 关联上面设定的id属性  

            handler.startElement(uri, localName, "name", null); //开始一个name元素 没有属性  
            ch = String.valueOf(person.getName()).toCharArray();
            handler.characters(ch, 0, ch.length);   //设置name元素的文本节点  
            handler.endElement(uri, localName, "name");

            handler.startElement(uri, localName, "sex", null);//开始一个sex元素 没有属性  
            ch = String.valueOf(person.getSex()).toCharArray();
            handler.characters(ch, 0, ch.length);   //设置sex元素的文本节点  
            handler.endElement(uri, localName, "sex");

            handler.endElement(uri, localName, "person");
        }
        handler.endElement(uri, localName, "persons");
        handler.endDocument();

        return writer.toString();
    }
}
