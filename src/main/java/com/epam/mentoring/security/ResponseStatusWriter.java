package com.epam.mentoring.security;

import com.epam.mentoring.dto.StatusResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseStatusWriter {
    public static void writeStatusResponse(HttpServletResponse response,
                                     StatusResponseDto statusResponseDto) throws IOException {
        PrintWriter printWriter = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();


        printWriter.write(objectMapper.writeValueAsString(statusResponseDto));

        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(statusResponseDto.getStatus().getCode());
    }
}
