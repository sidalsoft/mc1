package com.example.MC1.controllers;

import com.example.MC1.services.MessageService;
import com.example.MC1.services.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import java.time.Duration;
import java.util.Date;

@RestController
public class MessageController implements MessagePort
{
    Logger logger = LoggerFactory.getLogger( MessageController.class);

    @Value("${my.interaction-interval}")
    int interactionInterval;

    @Value("${my.web-socket.topic}")
    String destination;

    MessageService messageService;

    private final SimpMessagingTemplate template;

    private boolean processIsStopped = true;

    private int latsSessionId;

    private long spentTimeMillis = 0;

    @Autowired
    MessageController( MessageService aMessageService, SimpMessagingTemplate aTemplate )
    {
        messageService = aMessageService;
        template = aTemplate;
    }

    public ResponseEntity<String> start()
    {
        if ( processIsStopped )
        {
            processIsStopped = false;
            latsSessionId = messageService.getGenerateSessionID();
            createAndSendMessageToMC2();
            return ResponseEntity.ok( String.format( "Session %s is started!", latsSessionId ) );
        }

        return ResponseEntity.badRequest().body( "Session already started!!!" );
    }

    public ResponseEntity<String> stop()
    {
        if ( !processIsStopped )
        {
            processIsStopped = true;
            return ResponseEntity.ok( String.format( "Session %s is stopped!", latsSessionId ) );
        }

        return ResponseEntity.badRequest().body( "Session not found!!!" );

    }

    public String saveMessage( final MessageDTO messageDTO )
    {
        messageDTO.setEndTimestamp( new Date() );
        messageService.saveMessage( messageDTO );

        spentTimeMillis += Duration.between( messageDTO.getMC1Timestamp().toInstant(),
                                             messageDTO.getEndTimestamp().toInstant() ).toMillis();

        if ( ( spentTimeMillis / 1000 ) >= interactionInterval || processIsStopped )
        {
            printLogAndReset();
        } else
        {
            createAndSendMessageToMC2();
        }

        return HttpStatus.OK.toString();
    }


    private void createAndSendMessageToMC2()
    {
        var message = messageService.generateNewMessage( latsSessionId );
        template.convertAndSend( destination, message );
    }

    private void printLogAndReset()
    {
        logger.info( messageService.generateSessionInfo( spentTimeMillis / 1000, latsSessionId ).toString() );
        latsSessionId = 0;
        processIsStopped = true;
        spentTimeMillis = 0;
    }
}
