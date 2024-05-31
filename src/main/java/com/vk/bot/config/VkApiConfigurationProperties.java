package com.vk.bot.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:vk.properties")
@ConfigurationProperties("vk")
@Getter
@Setter
public class VkApiConfigurationProperties {
    @NotBlank
    private String accessToken;
    @NotBlank
    private Double v;
    @NotBlank
    private Long groupId;
    @NotBlank
    private Double lpVersion;
}
