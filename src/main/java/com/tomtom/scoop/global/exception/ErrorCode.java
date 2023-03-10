package com.tomtom.scoop.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C-0000", "Bad Request"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "C-0001", "Not Found the Contents"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-0002", "Internal Server Error"),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "C-0003", "Not Found Upload File"),
    FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C-0004", "File Upload Error"),

    // Auth
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH-0001", "Unauthorized"),
    AUTH_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH-0002", "Not Found the Auth Id"),
    JWT_ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN-0001", "Access token has expired"),
    JWT_REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN-0002", "Refresh token has expired"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN-0003", "Invalid token"),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-0001", "Not Found the User"),

    // Meeting
    MEETING_NOT_FOUND(HttpStatus.NOT_FOUND, "MEETING-0001", "Not Found the Meeting"),
    MEETING_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "MEETING-0002", "Not Found the Meeting Type"),
    ALREADY_JOINED_MEETING(HttpStatus.BAD_REQUEST, "MEETING-0003", "Already Joined the Meeting"),
    NOT_MEETING_OWNER(HttpStatus.FORBIDDEN, "MEETING-0004", "Not Owner of the Meeting"),
    NOT_LIKED_MEETING(HttpStatus.BAD_REQUEST, "MEETING-0005", "Not Liked the Meeting"),
    NOT_JOINED_USER_IN_MEETING(HttpStatus.BAD_REQUEST, "MEETING-0006", "Not Joined User in the Meeting"),

    // Exercise
    EXERCISE_NOT_FOUND(HttpStatus.NOT_FOUND, "EXERCISE-0001", "Not Found the Exercise"),
    EXERCISE_LEVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "EXERCISE-0002", "Not Found the Exercise Level");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
