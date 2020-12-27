package com.sotheareth.kamel.route;

import java.util.StringTokenizer;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.sotheareth.kamel.json.POJOToJsonConverter;
import com.sotheareth.kamel.model.Employee;

@Component
public class MyRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:G:/source")
		.process(new Processor() {

			public void process(Exchange exchange) throws Exception {
				//read input message given by source
				Message input = exchange.getIn();
				
				//read body as string from in put message
				String data = input.getBody(String.class);
				
				//operation
				StringTokenizer str = new StringTokenizer(data, ",");
				String eid = str.nextToken();
				String ename = str.nextToken();
				String esal = str.nextToken();
				
				//output data
				//String json = String.format("{\"eid\":%s}",) "{eid:" + eid + ",ename:" + ename + ",esal:" + esal + "}";
				Employee employee = new Employee();
				employee.setEmployeeId(eid);
				employee.setName(ename);
				employee.setSalary(Double.parseDouble(esal));
				String json = POJOToJsonConverter.convert(employee);
				
				//read output message reference
				Message output = exchange.getMessage();
				
				//set data to output
				output.setBody(json);
				
			}
			
		})
		.to("file:G:/destination?filename=emp.json");
	}

}
