package com.epam.mentoring.service.facade;

import com.epam.mentoring.dto.*;
import com.epam.mentoring.entity.Message;
import com.epam.mentoring.service.MessageServiceFacade;
import com.epam.mentoring.service.ServiceStorage;
import com.epam.mentoring.service.mapper.MapperStorage;
import com.epam.mentoring.service.status.StatusResponse;
import com.epam.mentoring.service.status.StatusResponseFiller;
import com.epam.mentoring.service.validator.CommonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageServiceFacadeImpl implements MessageServiceFacade {
    private ServiceStorage serviceStorage;
    private StatusResponseFiller statusResponseFiller;
    private CommonValidator commonValidator;
    private MapperStorage mapperStorage;

    @Autowired
    public MessageServiceFacadeImpl(ServiceStorage serviceStorage,
                                    StatusResponseFiller statusResponseFiller,
                                    CommonValidator commonValidator,
                                    MapperStorage mapperStorage) {
        this.serviceStorage = serviceStorage;
        this.statusResponseFiller = statusResponseFiller;
        this.commonValidator = commonValidator;
        this.mapperStorage = mapperStorage;
    }

    @Override
    public MessageHistoryResponseDto getChatHistory(MessageHistoryRequestDto messageHistoryRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(messageHistoryRequestDto);

        List<Message> history = null;

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            if (messageHistoryRequestDto.getTill() == null) {
                history = serviceStorage.getMessageService()
                        .getChatHistory(messageHistoryRequestDto.getChatId(), messageHistoryRequestDto.getFrom());
            } else {
                history = serviceStorage.getMessageService()
                        .getChatHistory(messageHistoryRequestDto.getChatId(),
                                        messageHistoryRequestDto.getFrom(),
                                        messageHistoryRequestDto.getTill());
            }

            if (history == null || history.size() == 0) {
                statusResponseFiller.setEmptyResponseStatus(statusResponseDto);
                history = null;
            }
        }

        return MessageHistoryResponseDto.builder()
                .history(mapperStorage.messageListToMessageDtoList()
                                 .handle(history))
                .status(statusResponseDto)
                .build();
    }

    @Override
    public MessageResponseDto createMessage(MessageCreateRequestDto messageCreateRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(messageCreateRequestDto);

        Message savedMessage = null;

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            Message notSavedMessage = Message.builder()
                    .text(messageCreateRequestDto.getText())
                    .user(serviceStorage.getUserService()
                                  .getById(messageCreateRequestDto.getUserId()))
                    .chat(serviceStorage.getChatService()
                                  .getChatInfo(messageCreateRequestDto.getChatId()))
                    .build();

            savedMessage = serviceStorage.getMessageService()
                    .createMessage(notSavedMessage);
        }

        return MessageResponseDto.builder()
                .status(statusResponseDto)
                .message(mapperStorage.messageToMessageDto()
                                 .handle(savedMessage))
                .build();
    }

    @Override
    public MessageResponseDto updateMessage(MessageUpdateRequestDto messageUpdateRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(messageUpdateRequestDto);

        Message updatedMessage = null;

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            Message notUpdatedMessage = serviceStorage.getMessageService()
                    .getById(messageUpdateRequestDto.getMessageId());

            notUpdatedMessage.setText(messageUpdateRequestDto.getText());

            updatedMessage = serviceStorage.getMessageService()
                    .updateMessage(notUpdatedMessage);
        }

        return MessageResponseDto.builder()
                .status(statusResponseDto)
                .message(mapperStorage.messageToMessageDto()
                                 .handle(updatedMessage))
                .build();
    }

    @Override
    public StatusResponseDto deleteMessage(MessageDeleteRequestDto messageDeleteRequestDto) {
        ServiceStatusResponseDto statusResponseDto = commonValidator.validate(messageDeleteRequestDto);

        if (statusResponseDto.getCode() == StatusResponse.SUCCESS.getCode()) {
            serviceStorage.getMessageService()
                    .deleteMessage(messageDeleteRequestDto.getMessageId());
        }

        return StatusResponseDto.builder()
                .status(statusResponseDto)
                .build();
    }
}
