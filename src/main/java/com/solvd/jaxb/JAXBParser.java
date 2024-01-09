package com.solvd.jaxb;

import com.solvd.model.deal.RentalDeal;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JAXBParser {

    public static void main(String[] args) {
        RentalDeal rentalDeal;
        try {
            File file = new File("src/main/resources/RentalDeal.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(RentalDeal.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            rentalDeal = (RentalDeal) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        System.out.println(rentalDeal);
    }
}
