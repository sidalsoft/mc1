package com.example.MC1.services.dto;

import com.example.MC1.models.MessageModel;
import org.springframework.core.convert.converter.Converter;

public class MessageDTOToModelConvertor implements Converter<MessageDTO, MessageModel>
{
    @Override
    public MessageModel convert( MessageDTO source )
    {
        var model = new MessageModel();
        model.setSessionId( source.getSessionId() );
        model.setMC1Timestamp( source.getMC1Timestamp() );
        model.setMC2Timestamp( source.getMC2Timestamp() );
        model.setMC3Timestamp( source.getMC3Timestamp() );
        model.setEndTimestamp( source.getEndTimestamp() );
        return model;
    }
}
