package com.example.fcmserver.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.fcmserver.util.FcmUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fcmtest")
public class FcmController {

    private final FcmUtil fcmUtil;

    @PostMapping()
    public FcmTestResponse fcmtest(@RequestParam(value = "token_id") String tokenId) {
        String title = "제목입니다";
        String content = "내용입니다";

        fcmUtil.sendFCM(tokenId, title, content);

        return new FcmTestResponse("success");
    }

    @Data
    @AllArgsConstructor
    static class FcmTestResponse {
        private String message;
    }
}
