package budkevych.dcsapi.dto.mapper;

import budkevych.dcsapi.dto.request.GameCharacterRequestDto;
import budkevych.dcsapi.dto.response.GameCharacterResponseDto;
import budkevych.dcsapi.model.GameCharacter;
import budkevych.dcsapi.model.ParamMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class GameCharacterMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public GameCharacterResponseDto toDto(GameCharacter character) {
        GameCharacterResponseDto dto = new GameCharacterResponseDto();
        dto.setId(character.getId());
        dto.setTimestamp(character.getLastUpdate());
        dto.setUserId(character.getUserId());
        dto.setName(character.getName());
        try {
            dto.setParamMap(objectMapper.readValue(
                    character.getParamMap().getData(),
                    HashMap.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to parse paramMap to json ", e);
        }
        return dto;
    }

    public GameCharacter toModel(GameCharacterRequestDto dto) {
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setName(dto.getName());
        gameCharacter.setIsDeleted((short) 0);
        gameCharacter.setPortraitId(dto.getPortraitId());
        try {
            ParamMap paramMap = new ParamMap();
            paramMap.setData(objectMapper.writeValueAsString(dto.getParamMap()));
            gameCharacter.setParamMap(paramMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to parse paramMap from json ", e);
        }
        return gameCharacter;
    }
}
