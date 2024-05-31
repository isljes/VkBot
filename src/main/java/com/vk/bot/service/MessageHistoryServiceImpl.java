package com.vk.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.bot.config.VkApiConfigurationProperties;
import com.vk.bot.dto.HandshakeResponseDto;
import com.vk.bot.dto.MessageHistoryDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageHistoryServiceImpl implements MessageHistoryService {

    private final ObjectMapper objectMapper;
    private final VkApiConfigurationProperties vkApiConfigurationProperties;
    private final Requester requester;
    private HandshakeResponseDto handshakeResponseDto;
    private Long pts;

    @PostConstruct
    public void intiFirstConnection(){
        String url="https://api.vk.com/method/messages.getLongPollServer";
        URI requestUri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("access_token", vkApiConfigurationProperties.getAccessToken())
                .queryParam("need_pts", 1)
                .queryParam("group_id", vkApiConfigurationProperties.getGroupId())
                .queryParam("lp_version", vkApiConfigurationProperties.getLpVersion())
                .queryParam("v", vkApiConfigurationProperties.getV()).build().toUri();

        String response = requester.executeRequest(requestUri);
        try {
            JsonNode jsonNode = objectMapper.readTree(response).get("response");
            handshakeResponseDto =objectMapper.treeToValue(jsonNode, HandshakeResponseDto.class);
            pts= handshakeResponseDto.getPts();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public List<MessageHistoryDto.Message> requestMessageHistory() {
        String url="https://api.vk.com/method/messages.getLongPollHistory";
        URI requestUri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("access_token", vkApiConfigurationProperties.getAccessToken())
                .queryParam("ts", handshakeResponseDto.getTs())
                .queryParam("pts", pts)
                .queryParam("group_id", vkApiConfigurationProperties.getGroupId())
                .queryParam("lp_version", vkApiConfigurationProperties.getLpVersion())
                .queryParam("v", vkApiConfigurationProperties.getV()).build().toUri();
        String historyJson = requester.executeRequest(requestUri);
        MessageHistoryDto messageHistoryDto = null;
        try {
            messageHistoryDto = objectMapper.readValue(historyJson, MessageHistoryDto.class);
            pts=messageHistoryDto.getPts();
            return messageHistoryDto.getMessageList();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }




}
