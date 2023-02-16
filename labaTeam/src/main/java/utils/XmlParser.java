package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlParser {
    private static final Logger LOGGER = LogManager.getLogger(XmlParser.class);

    public static void marshall(Object o, String filePath) {
        try {
            JAXBContext content = JAXBContext.newInstance(o.getClass());
            Marshaller m = content.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(o, new File(filePath));
        } catch (JAXBException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
