package com.career.copilot.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PDFGenerationToolTest {

    @Test
    void generatePDF() {
        PDFGenerationTool tool = new PDFGenerationTool();
        String fileName = "后端面试准备计划.pdf";
        String content = "后端面试准备计划：项目介绍、技术方案、追问清单、复盘总结";
        String result = tool.generatePDF(fileName, content);
        assertNotNull(result);
    }
}