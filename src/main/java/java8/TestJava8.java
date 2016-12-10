package java8;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by FengSi on 2016/11/01 at 15:56.
 */
public class TestJava8 {
    public static void main(String[] args) {
//        try {
//            String content = "2014-08-04 06:10:19.860";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//            Date date = simpleDateFormat.parse(content);
//            System.out.println(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read("root@master:/opt/hy0201.txt");
            Element root = doc.getRootElement();
            System.out.println(root.getName());
            List<Element> param = root.elements();
            Map<String, String> map = new HashMap<>();
            for (Element element : param)
                map.put(element.attributeValue("Code"), element.attributeValue("ID"));
            for (String s : map.keySet())
                System.out.println(s + ": " + map.get(s));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static double average(double a, double... values) {
        double res = a;
        for (double value : values) {
            res += value;
        }
        return res / (values.length + 1);
    }
}