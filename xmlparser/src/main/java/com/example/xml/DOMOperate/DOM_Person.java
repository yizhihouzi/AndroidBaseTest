package com.example.xml.DOMOperate;

import com.example.bigbigboy.bean.PersonBean;
import com.example.xml.XmlOperate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by BigBigBoy on 2015/3/11.
 */
public class DOM_Person implements XmlOperate {
    @Override
    public List<PersonBean> xmlParse(InputStream is) throws Exception {
        List<PersonBean> persons = new ArrayList<PersonBean>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  //取得DocumentBuilderFactory实例  
        DocumentBuilder builder = factory.newDocumentBuilder(); //从factory获取DocumentBuilder实例  
        Document doc = builder.parse(is);   //解析输入流 得到Document实例  
        Element rootElement = doc.getDocumentElement();
        NodeList items = rootElement.getElementsByTagName("person");
        for (int i = 0; i < items.getLength(); i++) {
            PersonBean person = new PersonBean();
            Node item = items.item(i);
            NodeList properties = item.getChildNodes();
            for (int j = 0; j < properties.getLength(); j++) {
                Node property = properties.item(j);
                String nodeName = property.getNodeName();
                if (nodeName.equals("id")) {
                    person.setId(Integer.parseInt(property.getFirstChild().getNodeValue()));
                } else if (nodeName.equals("name")) {
                    person.setName(property.getFirstChild().getNodeValue());
                } else if (nodeName.equals("sex")) {
                    person.setSex(property.getFirstChild().getNodeValue());
                }
            }
            persons.add(person);
        }
        return persons;
    }

    @Override
    public String xmlSerialize(List<PersonBean> persons) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();   //由builder创建新文档  

        Element rootElement = doc.createElement("persons");

        for (PersonBean person : persons) {
            Element personElement = doc.createElement("person");
            personElement.setAttribute("id", person.getId() + "");

            Element nameElement = doc.createElement("name");
            nameElement.setTextContent(person.getName());
            personElement.appendChild(nameElement);

            Element priceElement = doc.createElement("sex");
            priceElement.setTextContent(person.getSex() + "");
            personElement.appendChild(priceElement);

            rootElement.appendChild(personElement);
        }

        doc.appendChild(rootElement);

        TransformerFactory transFactory = TransformerFactory.newInstance();//取得TransformerFactory实例
        Transformer transformer = transFactory.newTransformer();    //从transFactory获取Transformer实例
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");            // 设置输出采用的编码方式
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");                // 是否自动添加额外的空白  
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");   // 是否忽略XML声明  

        StringWriter writer = new StringWriter();

        Source source = new DOMSource(doc); //表明文档来源是doc
        Result result = new StreamResult(writer);//表明目标结果为writer
        transformer.transform(source, result);  //开始转换  

        return writer.toString();
    }
}
