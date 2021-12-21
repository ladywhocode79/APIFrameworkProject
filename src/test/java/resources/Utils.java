package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {

        //spec builder
        /* req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                addQueryParam("key","qaclick123").
                 addFilter(RequestLoggingFilter.logRequestTo(log)).
                 addFilter(ResponseLoggingFilter.logResponseTo(log)).
                setContentType(ContentType.JSON).build();*/
        if (req==null){
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).
                    addQueryParam("key","qaclick123").
                    addFilter(RequestLoggingFilter.logRequestTo(log)).
                    addFilter(ResponseLoggingFilter.logResponseTo(log)).
                    setContentType(ContentType.JSON).build();
        return req;
        }
        return req;
    }
    //to get values from global properties
    public  String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);



    }
}
