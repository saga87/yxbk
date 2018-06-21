package org.tempuri;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class TestJaxb {

	
	public static void main(String args[]){
		
		String str="<?xml version=\"1.0\" encoding=\"utf-8\"?><production><OrderNO>S201506231541180581</OrderNO><DealerName>刘莹</DealerName><DealerNO>370783199708094967</DealerNO><OrderTime>2015/6/23 0:00:00</OrderTime><CHPName>啶虫脒</CHPName><CHPCode>NY004910</CHPCode><SaleNum>2.00</SaleNum><guige>200毫升</guige></production>";
		XMLStringToBean(str);
	}
	
	
	public static void XMLStringToBean(String str){
		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><production><age>22</age><classroom><grade>4</grade><id>1</id><name>软件工程</name></classroom><id>101</id><name>张三</name></production>";
		
		try {
			JAXBContext context = JAXBContext.newInstance(Productions.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Productions production = (Productions)unmarshaller.unmarshal(new StringReader(str));
			System.out.println(production.getPros());
			System.out.println(production.getInfo());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}

