package com.tomtom.scoop.domain.meeting.model.dto.request;

import com.tomtom.scoop.domain.common.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MeetingRequestDto {

    @Schema(description = "Meeting title", example = "Running in the park")
    @NotNull
    private String title;

    @Schema(description = "Meeting content", example = "Let's run together In KonKuk the park")
    @NotNull
    private String content;

    @Schema(description = "Meeting member limit", example = "10")
    @NotNull
    private Integer memberLimit;

    @Schema(description = "Gender - MALE, FEMALE, BOTH", example = "MALE")
    @NotNull
    private Gender gender;

    @Schema(description = "Meeting start time", example = "2021-08-01 10:00:00")
    private LocalDateTime meetingDate;

    @Schema(description = "Meeting location latitude", example = "37.5419")
    @NotNull
    private Float locationLatitude;

    @Schema(description = "Meeting location longitude", example = "127.0738")
    @NotNull
    private Float locationLongitude;

    @Schema(description = "Meeting location name", example = "KonKuk University")
    @NotNull
    private String locationName;

    @Schema(description = "Meeting location detail", example = "Library")
    @NotNull
    private String locationDetail;

    @Schema(description = "Meeting location city", example = "Seoul")
    @NotNull
    private String locationCity;

    @Schema(description = "Meeting Exercise name", example = "Running")
    @NotNull
    private String exerciseName;

    @Schema(description = "Meeting Exercise level", example = "Beginner")
    @NotNull
    private String exerciseLevel;

    @Schema(description = "Meeting Exercise type", example = "Play")
    private String meetingType;

}
