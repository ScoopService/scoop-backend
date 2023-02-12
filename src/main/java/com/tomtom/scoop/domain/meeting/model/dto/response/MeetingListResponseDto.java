package com.tomtom.scoop.domain.meeting.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MeetingListResponseDto {

    @Schema(description = "Meeting id", example = "1")
    private Long id;

    @Schema(description = "Meeting title", example = "Running in the park")
    private String title;

    @Schema(description = "Meeting ciy", example = "Seoul")
    private String city;

    @Schema(description = "Meeting start time", example = "2021-08-01 10:00:00")
    private LocalDateTime eventDate;

    @Schema(description = "Meeting Attendee count", example = "5")
    private Integer memberCount;

    @Schema(description = "Meeting Attendee limit", example = "10")
    private Integer memberLimit;

    @Schema(description = "Meeting Main Image", example = "https://scoop.s3.ap-northeast-2.amazonaws.com/4256.png")
    private String imgUrl;

    @Schema(description = "Meeting Exercise name", example = "Running")
    private String exerciseName;

    @Schema(description = "Meeting Exercise level", example = "Beginner")
    private String exerciseLevel;

    @Schema(description = "Meeting Exercise type", example = "Play")
    private String meetingType;

}
