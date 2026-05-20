package com.career.copilot.rag;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class PgVectorVectorStoreConfigTest {

    @Resource
    private VectorStore pgVectorVectorStore;

    @Test
    void pgVectorVectorStore() {
        List<Document> documents = List.of(
                new Document("Career Copilot 可以帮助准备面试、优化简历表达和生成汇报话术", Map.of("meta1", "meta1")),
                new Document("Java 后端面试需要讲清楚项目背景、技术方案和结果指标"),
                new Document("职场沟通需要明确背景、目标、风险和下一步行动", Map.of("meta2", "meta2")));
        // 添加文档
        pgVectorVectorStore.add(documents);
        // 相似度查询
        List<Document> results = pgVectorVectorStore.similaritySearch(SearchRequest.builder().query("怎么准备后端面试啊").topK(3).build());
        Assertions.assertNotNull(results);
    }
}
