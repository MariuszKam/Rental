package com.solvd;


import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

import static com.solvd.Main.logger;

public class XMLValidator {
    public static void main(String[] args) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource("src/main/resources/RentalDeal.xsd");
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            logger.info(isValid(validator));
        } catch (Exception e) {
            logger.warn(e);
        }
    }

    private static boolean isValid(Validator validator) throws IOException {
        try {
            validator.validate(new StreamSource("src/main/resources/RentalDeal.xml"));
            return true;
        } catch (SAXException e) {
            return false;
        }
    }
}
