package com.career.copilot.agent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YuManusTest {

    @Resource
    private YuManus yuManus;

    @Test
    public void run() {
        String userPrompt = """
                我下周要去上海静安区参加后端开发面试，请帮我找到 5 公里内适合面试前准备的安静地点，
                并结合一些网络图片，制定一份详细的面试准备计划，
                并以 PDF 格式输出""";
        String answer = yuManus.run(userPrompt);
        Assertions.assertNotNull(answer);
    }
}
