package com.career.copilot.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CareerAssistantAppTest {

    @Resource
    private CareerAssistantApp careerAssistantApp;

    @Test
    void testChat() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "你好，我正在准备 Java 后端面试";
        String answer = careerAssistantApp.doChat(message, chatId);
        // 第二轮
        message = "我想让面试官更清楚地看到我的项目亮点";
        answer = careerAssistantApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
        // 第三轮
        message = "我刚才想突出什么优势来着？帮我回忆一下";
        answer = careerAssistantApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我正在准备 Java 后端面试，我想让面试官更清楚地看到我的项目亮点，但我不知道该怎么做";
        CareerAssistantApp.CareerReport careerReport = careerAssistantApp.doChatWithReport(message, chatId);
        Assertions.assertNotNull(careerReport);
    }

    @Test
    void doChatWithRag() {
        String chatId = UUID.randomUUID().toString();
        String message = "我马上要参加 Java 后端面试，但是项目经历讲不清楚，怎么办？";
        String answer = careerAssistantApp.doChatWithRag(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithTools() {
        // 测试联网搜索问题的答案
        testMessage("下周要参加上海一家公司的现场面试，请帮我制定一天的面试准备计划");

        // 测试网页抓取：职场沟通案例分析
        testMessage("最近和同事协作有分歧，请帮我整理一次项目沟通复盘和后续协作建议");

        // 测试资源下载：图片下载
        testMessage("直接下载一张适合做面试准备封面的职场办公图片为文件");

        // 测试终端操作：执行代码
        testMessage("执行 Python3 脚本来生成数据分析报告");

        // 测试文件操作：保存用户档案
        testMessage("保存我的面试准备档案为文件");

        // 测试 PDF 生成
        testMessage("生成一份“后端开发面试准备计划”PDF，包含复习安排、项目讲解提纲和追问清单");
    }

    private void testMessage(String message) {
        String chatId = UUID.randomUUID().toString();
        String answer = careerAssistantApp.doChatWithTools(message, chatId);
        Assertions.assertNotNull(answer);
    }

    @Test
    void doChatWithMcp() {
        String chatId = UUID.randomUUID().toString();
        // 测试地图 MCP
//        String message = "我下周要去上海静安区面试，请帮我找到 5 公里内适合面试前准备的安静地点";
//        String answer =  careerAssistantApp.doChatWithMcp(message, chatId);
//        Assertions.assertNotNull(answer);
        // 测试图片搜索 MCP
        String message = "帮我搜索一些适合职场汇报或面试准备封面的图片";
        String answer =  careerAssistantApp.doChatWithMcp(message, chatId);
        Assertions.assertNotNull(answer);
    }
}

