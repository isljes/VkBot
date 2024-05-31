package com.vk.bot.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vk.bot.service.MessageHistoryDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Builder
@Getter
@Setter
@JsonDeserialize(using = MessageHistoryDeserializer.class)
public class MessageHistoryDto implements Serializable {

    private List<Message> messageList;

    private Long pts;
    @Builder
    @Getter
    public static class Message implements Serializable{
        private Long fromId;
        private String text;
    }
}
