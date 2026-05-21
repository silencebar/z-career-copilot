package com.career.copilot.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CareerAssistantDocumentLoaderTest {

    @Resource
    private CareerAssistantDocumentLoader careerAssistantDocumentLoader;

    @Test
    void loadMarkdowns() {
        careerAssistantDocumentLoader.loadMarkdowns();
    }
}