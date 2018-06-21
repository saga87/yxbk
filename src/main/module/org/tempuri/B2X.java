/**
 * 
 */
package org.tempuri;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;

/**
 * @author 周权 Mar 23, 2012 - 1:43:55 PM Package Name: testbetwixt Project Name:
 *         eyz
 * 
 */
public class B2X {
	public String bean2Xml(Object obj) throws Exception {
		// 创建一个输出流，将用来输出Java转换的XML文件
		StringWriter outputWriter = new StringWriter();

		// 输出XML的文件头 ͷ
		outputWriter.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		BeanWriter beanWriter = new BeanWriter(outputWriter);
		beanWriter.getXMLIntrospector().getConfiguration()
				.setAttributesForPrimitives(false);
		beanWriter.getBindingConfiguration().setMapIDs(false);
		beanWriter.enablePrettyPrint();
		beanWriter.setEndTagForEmptyElement(true);
		beanWriter.setIndent(" ");
		beanWriter.write(obj);
		return outputWriter.toString();
	}

	public Object xml2Bean(String reslutXml, String node, Class classes) {
		Object obj = null;
		StringReader xmlReader = new StringReader(reslutXml);
		// 创建一个BeanReader实例，相当于转换器
		BeanReader beanReader = new BeanReader();
		beanReader.getXMLIntrospector().getConfiguration()
				.setAttributesForPrimitives(false);
		beanReader.getBindingConfiguration().setMapIDs(false);

		// 注册要转换对象的类，并指定根节点名称
		try {
			beanReader.registerBeanClass(node, classes);
			obj = beanReader.parse(xmlReader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return obj;
		}

	}

	public String readXml(String filepath) {
		java.util.Scanner scanner = null;
		StringBuffer str = new StringBuffer("");
		try {
			scanner = new java.util.Scanner(new File(filepath));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				str.append(line);
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public static void main(String args[]) throws Exception {
		B2X b2x=new B2X();
		QueryResult qr=new QueryResult();
		qr.getBaseInfo().setCorpcode("370783300004625");
		qr.getBaseInfo().setCorpname("瑞胜农资高家店");
		Production p1=new Production();
		p1.setOrderno("S201506231541180581");
		p1.setDealername("刘莹");
		p1.setDealerno("370783199708094967");
		p1.setOrdertime("2015/6/23 0:00:00");
		p1.setChpname("啶虫脒");
		p1.setGuige("200毫升");
		p1.setChpcode("NY004910");
		p1.setSalenum("2.00");
		Production p2=new Production();
		p2.setOrderno("S201508121532570581");
		p2.setDealername("刘莹");
		p2.setDealerno("370783199708094967");
		p2.setOrdertime("2015/8/12 0:00:00");
		p2.setChpname("百可得");
		p2.setGuige("200毫升");
		p2.setChpcode("QT000001");
		p1.setSalenum("1.00");
		qr.addProduction(p1);
		qr.addProduction(p2);
		String xml=b2x.bean2Xml(qr);
		System.out.println(xml);
		QueryResult qr1=(QueryResult)b2x.xml2Bean(xml, "QueryResult", QueryResult.class);
		if(qr1!=null){
			System.out.println("baseinfo:");
			System.out.println("baseinfo.corpname:"+qr1.getBaseInfo().getCorpname());
			System.out.println("baseinfo.corpcode:"+qr1.getBaseInfo().getCorpcode());
			ArrayList<Production> productions=qr1.getProductions();
			if(productions!=null){
				int i=0;
				for(Production production:productions){
					i++;
					System.out.println("production:"+i);
					System.out.println("production.orderno:"+production.getOrderno());
					System.out.println("production.dealername:"+production.getDealername());
					System.out.println("production.dealerno:"+production.getDealerno());
					System.out.println("production.ordertime:"+production.getOrdertime());
					System.out.println("production.chpname:"+production.getChpname());
					System.out.println("production.chpcode:"+production.getChpcode());
					System.out.println("production.salenum:"+production.getSalenum());
					System.out.println("production.guige:"+production.getGuige());
					
				}
			}
			
		}
		
	}
}
