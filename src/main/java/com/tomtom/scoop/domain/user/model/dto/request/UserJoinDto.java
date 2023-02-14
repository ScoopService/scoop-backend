package com.tomtom.scoop.domain.user.model.dto.request;

import com.tomtom.scoop.domain.common.Gender;
import com.tomtom.scoop.domain.user.model.dto.ExerciseLevelDto;
import com.tomtom.scoop.domain.user.model.dto.UserLocationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDto {
    private String name;
    private String phone;
    private String nickname;
    private Gender gender;
    private String deviceToken;
    private List<ExerciseLevelDto> exerciseLevels;
    private UserLocationDto userLocation;
    private List<String> keywords;
}
