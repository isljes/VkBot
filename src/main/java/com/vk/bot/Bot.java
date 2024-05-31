package com.vk.bot;

import com.vk.bot.dto.MessageHistoryDto;
import com.vk.bot.service.BotResponseService;
import com.vk.bot.service.MessageHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Bot {

    private final BotResponseService botResponseService;
    private final MessageHistoryService messageHistoryService;

    @Scheduled(fixedDelay = 3000)
    public void work(){
        List<MessageHistoryDto.Message> messageHistory = messageHistoryService.requestMessageHistory();
        for (var message:messageHistory){
            botResponseService.response(message);
        }
    }

}
