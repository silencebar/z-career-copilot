package com.career.copilot.controller;

import com.career.copilot.agent.SuperCareerAgent;
import com.career.copilot.app.CareerAssistantApp;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Resource
    private CareerAssistantApp careerAssistantApp;

    @Resource
    private ToolCallback[] allTools;

    @Resource
    private ChatModel dashscopeChatModel;

    /**
     * 同步调用 AI 职场沟通与面试辅导助手
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping("/career/chat/sync")
    public String doChatWithCareerAssistantAppSync(String message, String chatId) {
        return careerAssistantApp.doChat(message, chatId);
    }

    /**
     * SSE 流式调用 AI 职场沟通与面试辅导助手
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/career/chat/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> doChatWithCareerAssistantAppSSE(String message, String chatId) {
        return careerAssistantApp.doChatByStream(message, chatId);
    }

    /**
     * SSE 流式调用 AI 职场沟通与面试辅导助手
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/career/chat/server_sent_event")
    public Flux<ServerSentEvent<String>> doChatWithCareerAssistantAppServerSentEvent(String message, String chatId) {
        return careerAssistantApp.doChatByStream(message, chatId)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }

    /**
     * SSE 流式调用 AI 职场沟通与面试辅导助手
     *
     * @param message
     * @param chatId
     * @return
     */
    @GetMapping(value = "/career/chat/sse_emitter")
    public SseEmitter doChatWithCareerAssistantAppServerSseEmitter(String message, String chatId) {
        // 创建一个超时时间较长的 SseEmitter
        SseEmitter sseEmitter = new SseEmitter(180000L); // 3 分钟超时
        // 获取 Flux 响应式数据流并且直接通过订阅推送给 SseEmitter
        careerAssistantApp.doChatByStream(message, chatId)
                .subscribe(chunk -> {
                    try {
                        sseEmitter.send(chunk);
                    } catch (IOException e) {
                        sseEmitter.completeWithError(e);
                    }
                }, sseEmitter::completeWithError, sseEmitter::complete);
        // 返回
        return sseEmitter;
    }

    /**
     * 流式调用 CareerAgent 超级智能体
     *
     * @param message
     * @return
     */
    @GetMapping("/agent/chat")
    public SseEmitter doChatWithCareerAgent(String message) {
        SuperCareerAgent superCareerAgent = new SuperCareerAgent(allTools, dashscopeChatModel);
        return superCareerAgent.runStream(message);
    }
}

