package budkevych.squareapi.controller;

import budkevych.squareapi.dto.GameCharacterRequestDto;
import budkevych.squareapi.dto.TimestampResponseDto;
import budkevych.squareapi.mapper.GameCharacterMapper;
import budkevych.squareapi.model.GameCharacter;
import budkevych.squareapi.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = {"http://93.175.234.30:5500/", "http://127.0.0.1:5500"})
@AllArgsConstructor
public class GameCharacterController {
    private final CharacterService characterService;
    private final GameCharacterMapper mapper;

    @GetMapping("/check-update/{id}")
    public TimestampResponseDto getUpToDate(@PathVariable Long id,
                                       @RequestParam Long timestamp) {
        GameCharacter gameCharacter = characterService.find(id);
        TimestampResponseDto timestampResponseDto = new TimestampResponseDto();
        timestampResponseDto.setTimestamp(gameCharacter.getLastUpdate());
        if (!gameCharacter.getLastUpdate().equals(timestamp)) {
            timestampResponseDto.setObject(mapper.toDto(gameCharacter));
        }
        return timestampResponseDto;
    }

    @PostMapping("/save")
    public void save(@RequestBody GameCharacterRequestDto dto) {
        GameCharacter gameCharacter = mapper.toModel(dto);
        characterService.save(gameCharacter);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody GameCharacterRequestDto dto) {
        characterService.update(id, mapper.toModel(dto));
    }
}
