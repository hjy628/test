package test;

import java.util.Enumeration;
import java.util.Properties;

public class HelloWorld{
	 
	public static void main(String[] args) {
		Properties props=System.getProperties();
		Enumeration<Object> enums = props.keys();
		    while(enums.hasMoreElements())
		    {
		      String key=(String)enums.nextElement();
		      System.out.println(key+":"+System.getProperty(key));
		    }
	}
	}