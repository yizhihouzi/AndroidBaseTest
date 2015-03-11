package com.example.xml;

import com.example.bigbigboy.bean.PersonBean;
import java.io.InputStream;
import java.util.List;

/**
 * Created by BigBigBoy on 2015/3/11.
 */
public interface  XmlOperate {

    /**
     * 解析输入流 得到PersonBean对象集合
     * @param is
     * @return
     * @throws Exception
     */
    public List<PersonBean> xmlParse(InputStream is) throws Exception;

    /**
     * 序列化PersonBean对象集合 得到XML形式的字符串
     * @param persons
     * @return
     * @throws Exception
     */
    public String xmlSerialize(List<PersonBean> persons) throws Exception;


}
