package com.vk.bot.service;

import com.vk.bot.config.VkApiConfigurationProperties;
import com.vk.bot.dto.MessageHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class QuoteResponseService implements BotResponseService {

    private final Requester requester;
    private final VkApiConfigurationProperties vkApiConfigurationProperties;

    public void response(MessageHistoryDto.Message message) {

        String responseText = "Вы сказали: " + message.getText();
        String url = "https://api.vk.com/method/messages.send";
        URI sendMessage = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("access_token", vkApiConfigurationProperties.getAccessToken())
                .queryParam("v", vkApiConfigurationProperties.getV())
                .queryParam("user_id", message.getFromId())
                .queryParam("random_id", 0)
                .queryParam("message", responseText).build().toUri();

        requester.executeRequest(sendMessage);
    }
}


