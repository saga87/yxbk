/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package wkrjsystem.webservice.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import wkrjsystem.webservice.service.HelloWorld;


public final class Client {

    private static ClassPathXmlApplicationContext context;

	private Client() {
    }

    public static void main(String args[]) throws Exception {
    	
        context = new ClassPathXmlApplicationContext("webservice/cxf-client.xml");
        HelloWorld client = (HelloWorld)context.getBean("client");

        String response = client.sayHi("Joe123");
        System.out.println("Response: " + response);
        System.exit(0);
    }
}
