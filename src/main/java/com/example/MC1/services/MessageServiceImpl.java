package com.example.MC1.services;

import com.example.MC1.services.dto.MessageDTO;
import com.example.MC1.models.MessageModel;
import com.example.MC1.repositories.MessageRepository;
import com.example.MC1.services.dto.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Random;

@Service
public class MessageServiceImpl implements MessageService
{

    private final MessageRepository messageRepository;

    protected final ConversionService converter;

    @Autowired
    public MessageServiceImpl( @Qualifier("myConverter") ConversionService aConverter,
                               MessageRepository aMessageRepository )
    {
        messageRepository = aMessageRepository;
        converter = aConverter;
    }

    @Override
    public void saveMessage( MessageDTO messageDTO )
    {
        var model = converter.convert( messageDTO, MessageModel.class );
        assert model != null;
        messageRepository.save( model );
    }

    @Override
    public MessageDTO generateNewMessage( Integer sessionId )
    {
        return new MessageDTO( sessionId, new Date() );
    }

    public Integer getGenerateSessionID()
    {
        return messageRepository.retrieveMaxSessionId().orElse( 0 ) + 1;
    }

    public SessionInfo generateSessionInfo( long interactionTime, Integer sessionId )
    {
        var totalMessages = messageRepository.countBySessionId( sessionId );
        return new SessionInfo( interactionTime, totalMessages );
    }

    public Integer getGenerateSessionIDV2()
    {
        var rand = new Random();
        var sessionID = rand.nextInt();
        while ( messageRepository.existsBySessionId( sessionID ) )
        {
            sessionID = rand.nextInt();
        }
        return sessionID;
    }
}
