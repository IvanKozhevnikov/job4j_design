package ru.job4j.serialization.java.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "materials")
public class Materials {
    @XmlAttribute
    public String concrete;
    @XmlAttribute
    public String shingles;

    public Materials() {

    }

    public Materials(String concrete, String shingles) {
        this.concrete = concrete;
        this.shingles = shingles;
    }

    @Override
    public String toString() {
        return "Materials{"
                + "reliable='" + concrete + '\''
                + ", enduring='" + shingles + '\''
                + '}';
    }
}
