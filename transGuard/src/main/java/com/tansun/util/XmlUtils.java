package com.tansun.util;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlUtils {
	
	public static Document getDoc(){
		File file = new File(System.getProperty("user.dir")+File.separator+"config"+File.separator+"msgTemplate.xml");
        SAXReader reader=new SAXReader();
        //读取xml文件到Document中
        Document doc = null;
		try {
			doc = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	public static Document getDoc(String fileName){
		File file = new File(System.getProperty("user.dir")+File.separator+"config"+File.separator+fileName);
        SAXReader reader=new SAXReader();
        //读取xml文件到Document中
        Document doc = null;
		try {
			doc = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	
	public static void main(String args[]){

	}
	
}
