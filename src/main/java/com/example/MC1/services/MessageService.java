package com.example.MC1.services;

import com.example.MC1.services.dto.SessionInfo;
import com.example.MC1.services.dto.MessageDTO;

public interface MessageService
{

    void saveMessage( MessageDTO messageDTO );

    MessageDTO generateNewMessage(Integer sessionId);

    Integer getGenerateSessionID();

    SessionInfo generateSessionInfo( long interactionTime, Integer sessionId );
}
