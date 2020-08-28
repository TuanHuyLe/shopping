package com.javaweb.shopping.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.regex.Pattern;

@Component
public class SlugifyUtils {
    public String slugify(String name) {
        try {
            String temp = Normalizer.normalize(name, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp)
                    .replaceAll("").toLowerCase()
                    .replaceAll(" ", "-")
                    .replaceAll("đ", "d");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
