package java8;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FengSi on 2016/11/01 at 15:56.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("66062A08", 16));

//        SAXReader reader = new SAXReader();
//        try {
//            Document doc = reader.read("D:\\Data\\HJ010C\\hj010c.txt");
//            Element root = doc.getRootElement();
//            System.out.println(root.getName());
//            List<Element> param = root.elements();
//            Map<String, String> map = new HashMap<>();
//            for (Element element : param)
//                map.put(element.attributeValue("Code"), element.attributeValue("ID"));
//            for (String s : map.keySet())
//                System.out.println(s + ": " + map.get(s));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private static double average(double a, double... values) {
        double res = a;
        for (double value : values) {
            res += value;
        }
        return res / (values.length + 1);
    }
}