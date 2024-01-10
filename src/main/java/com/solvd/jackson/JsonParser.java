package com.solvd.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.model.deal.RentalDeal;

import java.io.File;
import java.io.IOException;

import static com.solvd.Main.logger;

public class JsonParser {
    public static void main(String[] args) {
        File file = new File("src/main/resources/RentalDeal.json");

        ObjectMapper objectMapper = new ObjectMapper();
        RentalDeal rentalDeal;
        try {
            rentalDeal = objectMapper.readValue(file, RentalDeal.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info(rentalDeal);
    }
}
