package com.project.inftrastructure.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXB;

public class CustomUtils {

    //For Report
    public static String jaxbObjectToXML(Object customer) {
        StringWriter sw = new StringWriter();
        JAXB.marshal(customer, sw);
        return sw.toString();
    }

    public static <T> String jaxbObjectListToXML(List<T> pojoList) {
        StringWriter sw = new StringWriter();
        for (T obj : pojoList) {
            JAXB.marshal(obj, sw);
        }

        return sw.toString();
    }

    public static ByteArrayOutputStream getByteArray(String data) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArrayOutputStream);
        try {
            out.write(data.getBytes());
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream;
    }
}
