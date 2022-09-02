package com.example.MC1.services.dto;

import com.example.MC1.models.MessageModel;
import org.springframework.core.convert.converter.Converter;

public class MessageModelToDTOConvertor implements Converter<MessageModel, MessageDTO>
{
    @Override
    public MessageDTO convert( MessageModel source )
    {
        var messageDTO = new MessageDTO();
        messageDTO.setSessionId( source.getSessionId() );
        messageDTO.setMC1Timestamp( source.getMC1Timestamp() );
        messageDTO.setMC2Timestamp( source.getMC2Timestamp() );
        messageDTO.setMC3Timestamp( source.getMC3Timestamp() );
        messageDTO.setEndTimestamp( source.getEndTimestamp() );
        return messageDTO;
    }
}
