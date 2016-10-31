package models;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlType(name = "HelloWorldModel", propOrder = {
        "response",
        "test"
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HelloWorldModel")
public class HelloWorldModel implements Serializable {

    @Getter
    @Setter
    private String response;

    @Getter
    @Setter
    private String test;
}