package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "HelloWorldModel")
public class HelloWorldModel {

    private String response;
    private String test;

    public HelloWorldModel() {
    }

    public HelloWorldModel(String response, String test) {
        this.response = response;
        this.test = test;
    }

    @XmlElement
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @XmlElement
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}