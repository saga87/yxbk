package wkrjsystem.webservice.service.impl;

import javax.jws.WebService;

import wkrjsystem.webservice.service.HelloWorld;

@SuppressWarnings("restriction")
@WebService(targetNamespace="zxh")
public class HelloWorldImpl implements HelloWorld{

	
	public HelloWorldImpl(){
		System.out.println("启动了Webservice");
	}
	
	@Override
	public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
	
}
