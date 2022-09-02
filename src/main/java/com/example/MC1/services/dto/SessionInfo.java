package com.example.MC1.services.dto;

/**
 *@param interactionTime Время взаимодействия.
 *@param totalMessages Количество сообщений, сгенерированных во время взаимодействия.
 */
public record SessionInfo( long interactionTime, long totalMessages )
{
    @Override
    public String toString()
    {
        return String.format(
                "Время взаимодействия: %s секунд.\nКоличество сообщений, сгенерированных во время взаимодействия: %s\n",
                interactionTime,
                totalMessages );
    }
}
