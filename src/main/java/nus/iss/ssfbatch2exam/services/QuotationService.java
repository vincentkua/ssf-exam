package nus.iss.ssfbatch2exam.services;

import java.io.StringReader;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import nus.iss.ssfbatch2exam.models.Quotation;

@Service
public class QuotationService {

    public Quotation getQuotations() {
        JsonArray json = Json.createArrayBuilder()
                .add("apple")
                .add("orange")
                .add("bread")
                .add("cheese")
                .add("chicken")
                .add("instant_noodles")
                .add("mineral_water")
                .build();

        RequestEntity<String> req = RequestEntity
                .post("https://quotation.chuklee.com/quotation")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(json.toString(), String.class);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        System.out.println("Response #################");
        System.out.println(resp);
        String payload = resp.getBody();
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json2 = reader.readObject();

        // System.out.println("JSON #################");
        // System.out.println(json2);


        Quotation quotation = new Quotation();
        String quotationid = json2.getString("quoteId");
        quotation.setQuoteId(quotationid);

        JsonArray quotationarray = json2.getJsonArray("quotations");
        JsonObject jsonobj;
        String item;
        JsonNumber unitPrice;
        Float unitPrice2 = 0f;
        for (int i = 0; i < quotationarray.size(); i++) {
            jsonobj = quotationarray.getJsonObject(i);
            item = jsonobj.getString("item");
            unitPrice = jsonobj.getJsonNumber("unitPrice");
            unitPrice2 = Float.parseFloat(unitPrice.toString());
            quotation.addQuotation(item, unitPrice2);
        }
        System.out.println("Added to Quotation #########################");

        return quotation;

    }

}
