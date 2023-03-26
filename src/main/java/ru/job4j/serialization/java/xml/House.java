package ru.job4j.serialization.java.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "house")
@XmlAccessorType(XmlAccessType.FIELD)
public class House {
    @XmlAttribute
    private boolean built;
    @XmlAttribute
    private int rooms;
    @XmlAttribute
    private String description;
    private Materials materials;

    @XmlElementWrapper(name = "specifications")
    @XmlElement(name = "specification")
    private String[] specifications;

    public House() {

    }

    public House(boolean built, int rooms, String description, Materials materials, String... specifications) {
        this.built = built;
        this.rooms = rooms;
        this.description = description;
        this.materials = materials;
        this.specifications = specifications;
    }

    @Override
    public String toString() {
        return "House{"
                + "built=" + built
                + ", rooms=" + rooms
                + ", description='" + description + '\''
                + ", materials=" + materials
                + ", specifications=" + Arrays.toString(specifications)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final House house = new House(true, 3, "capital",
                new Materials("m-300", "soft"), "Reliable", "Enduring");

        JAXBContext context = JAXBContext.newInstance(House.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(house, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
