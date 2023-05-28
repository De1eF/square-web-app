package budkevych.squareapi.controller;

import budkevych.squareapi.dto.TimestampResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timestamp")
public class TimestampController {
    @GetMapping
    public TimestampResponseDto getTimestamp() {
        TimestampResponseDto dto = new TimestampResponseDto();
        dto.setTimestamp(System.currentTimeMillis());
        return dto;
    }
}