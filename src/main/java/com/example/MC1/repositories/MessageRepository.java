package com.example.MC1.repositories;

import com.example.MC1.models.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long>
{
    @Query("SELECT MAX(sessionId) FROM MessageModel ")
    Optional<Integer> retrieveMaxSessionId();

    boolean existsBySessionId( Integer sessionId );

    Integer countBySessionId( Integer sessionId );

}
