package wkrjsystem.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@SuppressWarnings("restriction")
@WebService(targetNamespace="zxh")
public interface HelloWorld {
	
	@WebMethod
	String sayHi(@WebParam(name="text") String text);
}
