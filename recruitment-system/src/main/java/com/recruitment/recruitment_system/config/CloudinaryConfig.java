package com.recruitment.recruitment_system.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Value("923165148918649")
    private String apiKey;
    @Value("2JKyIVERGUEU-uNRutEoP_zQSyI")
    private String apiSecret;
    @Value("dqmirkopr")
    private String cloudinaryUrl;
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", cloudinaryUrl,
                "api_secret",apiSecret,
                "api_key", apiKey
        ));
    }
}