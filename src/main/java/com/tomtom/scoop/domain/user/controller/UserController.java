package com.tomtom.scoop.domain.user.controller;

import com.tomtom.scoop.domain.user.model.dto.request.UserJoinDto;
import com.tomtom.scoop.domain.user.model.dto.request.UserUpdateDto;
import com.tomtom.scoop.domain.user.model.dto.response.UserResponseDto;
import com.tomtom.scoop.domain.user.model.entity.User;
import com.tomtom.scoop.domain.user.service.UserService;
import com.tomtom.scoop.global.annotation.ReqUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/join", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Enter additional information when registering as a member")
    public UserResponseDto join(@ReqUser() User user, @RequestPart UserJoinDto userJoinDto, @RequestPart MultipartFile file) {
        return userService.join(user, userJoinDto, file);
    }

    @PostMapping(value = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "update user info")
    public UserResponseDto update(@ReqUser() User user, @RequestPart UserUpdateDto userUpdateDto, @RequestPart MultipartFile file) {
        return userService.update(user, userUpdateDto, file);
    }

    @GetMapping
    @Operation(summary = "get my info")
    public UserResponseDto me(@ReqUser() User user) {
        return userService.me(user);
    }


}
