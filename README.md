# Z-Career-Copilot README

## 1. 项目页面展示

### 首页：Career Copilot 工作台

![首页预览](docs/images/home.png)

说明文案：

```text
首页采用现代 SaaS 风格设计，聚合职场沟通辅导、智能体任务规划、面试准备、简历表达优化等核心入口，突出 AI 职场助手的产品定位。
```

---

### 职场沟通与面试辅导页面

![职场助手页面](docs/images/career-assistant.png)

说明文案：

```text
职场助手面向面试准备、简历表达、汇报话术、同事协作和领导沟通等场景，结合系统 Prompt、多轮会话记忆和 RAG 知识库，生成可直接套用的建议与话术。
```

---

### 超级智能体任务规划页面

![超级智能体页面](docs/images/super-agent.png)

说明文案：

```text
超级智能体基于 ReAct + Tool Calling 实现多步任务规划，支持联网搜索、文件读写、结果整理和任务终止等工具能力，适合处理复杂职场任务。
```

---

# 2. 系统整体架构图

```mermaid
flowchart TB
    subgraph Client[客户端]
        Web[Vue3 + Vite 前端]
        Page1[职场助手页面]
        Page2[超级智能体页面]
    end

    subgraph Server[Spring Boot 服务端]
        Controller[AiController]
        CareerApp[CareerAssistantApp
职场问答助手]
        SuperAgent[SuperCareerAgent
超级智能体]
    end

    subgraph AI[Spring AI 核心能力]
        ChatClient[ChatClient]
        ChatModel[DashScope / Qwen ChatModel]
        Memory[ChatMemory
多轮会话记忆]
        Advisor[Advisors
日志 / RAG / Memory]
    end

    subgraph RAG[RAG 知识库模块]
        Docs[Markdown 知识库
resources/document]
        Loader[DocumentLoader]
        Embedding[EmbeddingModel]
        VectorStore[SimpleVectorStore
可扩展 PgVector]
        Retriever[QuestionAnswerAdvisor]
    end

    subgraph Agent[Agent 工具调用模块]
        Base[BaseAgent]
        ReAct[ReActAgent
Think + Act]
        ToolCall[ToolCallAgent]
        Tools[本地 Tools
搜索 / 文件 / 终止]
        MCP[MCP 扩展预留]
    end

    Web --> Page1
    Web --> Page2
    Page1 -->|SSE| Controller
    Page2 -->|SSE| Controller

    Controller --> CareerApp
    Controller --> SuperAgent

    CareerApp --> ChatClient
    CareerApp --> Memory
    CareerApp --> Advisor
    Advisor --> Retriever
    Retriever --> VectorStore

    Docs --> Loader
    Loader --> Embedding
    Embedding --> VectorStore

    SuperAgent --> Base
    Base --> ReAct
    ReAct --> ToolCall
    ToolCall --> Tools
    ToolCall --> MCP
    ToolCall --> ChatClient

    ChatClient --> ChatModel
```

---

# 3. 职场问答助手 RAG 链路

```mermaid
flowchart LR
    A[用户输入职场/面试问题] --> B[CareerAssistant 页面]
    B --> C[AiController
/api/ai/career/chat/sse]
    C --> D[CareerAssistantApp]
    D --> E[ChatClient]
    E --> F[System Prompt
职场顾问角色]
    E --> G[ChatMemory
会话上下文]
    E --> H[RAG Advisor]
    H --> I[VectorStore 检索相关文档]
    I --> J[拼接上下文增强 Prompt]
    J --> K[DashScope / Qwen]
    K --> L[SSE 流式返回]
    L --> M[前端实时展示]
```

---

# 4. RAG 知识库构建图

```mermaid
flowchart TD
    A[Markdown 原始文档
resources/document/*.md] --> B[CareerAssistantDocumentLoader]
    B --> C[MarkdownDocumentReader]
    C --> D[Document
content + metadata]
    D --> E[MyKeywordEnricher
关键词元数据增强]
    E --> F[EmbeddingModel
DashScope Embedding]
    F --> G[向量表示]
    G --> H[SimpleVectorStore
JVM 内存向量库]
    H --> I[QuestionAnswerAdvisor]
    I --> J[检索增强回答]

    H -. 可扩展 .-> K[PgVector / Milvus / 云知识库]
```

---

# 5. 超级智能体执行链路

```mermaid
flowchart TD
    A[用户复杂任务] --> B[SuperAgent 页面]
    B --> C[AiController
/api/ai/agent/chat]
    C --> D[SuperCareerAgent]
    D --> E[BaseAgent.runStream]
    E --> F[ReActAgent.step]
    F --> G[think
判断是否需要工具]
    G --> H{需要工具?}
    H -- 否 --> I[直接生成最终方案]
    H -- 是 --> J[ToolCallAgent.act]
    J --> K[executeToolCalls]
    K --> L[WebSearchTool]
    K --> M[FileOperationTool]
    K --> N[TerminateTool]
    L --> O[Observation]
    M --> O
    N --> O
    O --> P[回写 messageList]
    P --> F
    I --> Q[SSE 返回前端]
```

---

# 6. ReAct + Tool Calling 思维模型

```mermaid
flowchart LR
    T[Thought
任务分析] --> A[Action
工具调用]
    A --> O[Observation
工具结果]
    O --> R[Reasoning
结果整合]
    R --> F[Final Answer
最终方案]
```

---


# 7. README 项目亮点

```text
Z-Career-Copilot 是一个基于 Spring Boot 与 Spring AI 构建的 AI 职场沟通与面试辅导平台，面向求职、面试、简历表达、汇报沟通和职场协作等真实场景，提供 RAG 知识库问答、SSE 流式响应、ReAct 智能体规划和 Tool Calling 工具调用能力。

项目包含两个核心模块：

1. 职场问答助手：基于 ChatClient、系统 Prompt、多轮会话记忆和 RAG 检索增强，实现面向职场场景的专业问答与话术生成。
2. 超级智能体：基于 BaseAgent、ReActAgent 和 ToolCallAgent 构建多步任务执行流程，可根据用户需求自主判断是否调用搜索、文件读写等工具。
```

---

# 8. 技术栈说明

```text
后端：Spring Boot 3、Spring AI、DashScope/Qwen、RAG、Tool Calling、SSE
前端：Vue3、Vite、Element Plus、SaaS 风格 UI
RAG：MarkdownDocumentReader、EmbeddingModel、SimpleVectorStore、QuestionAnswerAdvisor
Agent：BaseAgent、ReActAgent、ToolCallAgent、SuperCareerAgent
扩展：PgVector、MCP Server、云知识库
```
