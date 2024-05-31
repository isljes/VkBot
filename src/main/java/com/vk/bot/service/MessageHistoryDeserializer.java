package com.vk.bot.service;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vk.bot.dto.MessageHistoryDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageHistoryDeserializer extends StdDeserializer<MessageHistoryDto> {
    public MessageHistoryDeserializer() {
        this(null);
    }

    public MessageHistoryDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public MessageHistoryDto deserialize(JsonParser jsonParser,
                                         DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemsArrayNode = rootNode.get("response").get("messages").get("items");
        List<MessageHistoryDto.Message> messageHistoryList=new ArrayList<>();
        if(itemsArrayNode.isArray()){
            for(JsonNode node:itemsArrayNode){
                var message=MessageHistoryDto.Message.builder()
                        .fromId(node.get("from_id").longValue())
                        .text(node.get("text").asText())
                        .build();
                messageHistoryList.add(message);
            }
        }
        return MessageHistoryDto.builder()
                .pts(rootNode.get("response").get("new_pts").longValue())
                .messageList(messageHistoryList).build();
    }
}
