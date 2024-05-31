package com.vk.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HandshakeResponseDto implements Serializable {
    @JsonProperty("server")
    private String server;
    @JsonProperty("key")
    private String key;
    @JsonProperty("ts")
    private Long ts;
    @JsonProperty("pts")
    private Long pts;

}
