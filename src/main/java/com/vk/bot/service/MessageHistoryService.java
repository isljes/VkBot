package com.vk.bot.service;

import com.vk.bot.dto.MessageHistoryDto;

import java.util.List;

public interface MessageHistoryService {
    List<MessageHistoryDto.Message> requestMessageHistory();
}
