package com.vk.bot.service;

import com.vk.bot.dto.MessageHistoryDto;

public interface BotResponseService {
    void response(MessageHistoryDto.Message message);
}
