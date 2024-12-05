package com.mini_project.productservice.productservice.util;

import com.mini_project.productservice.productservice.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public List<Product> parseCsvFile(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        List<Product> products = new ArrayList<>();

        String header = reader.readLine();
        if (header == null) {
            return products;
        }

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            String status = fields[7].trim();

            if ("APPROVED".equalsIgnoreCase(status)) {
                Product product = new Product();
                product.setProductId(fields[1].trim());
                product.setSupplierId(fields[2].trim());
                product.setProductDescription(fields[3].trim());
                product.setImageUrl(fields[4].trim());

                try {
                    product.setStock(Integer.parseInt(fields[5].trim()));
                } catch (NumberFormatException e) {
                    continue;
                }

                try {
                    product.setPrice(BigDecimal.valueOf(Double.parseDouble(fields[6].trim())));
                } catch (NumberFormatException e) {
                    continue;
                }

                products.add(product);
            }
        }

        for (Product product : products) {
            System.out.println(product.toString());
        }

        return products;
    }



}
