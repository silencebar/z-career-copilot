# Career Copilot

Career Copilot 是一个基于 Spring AI 的 AI 职场沟通与面试辅导助手，面向求职、职场表达和团队协作场景，提供多轮对话、RAG 知识库、Tool Calling、MCP 工具接入和 ReAct 超级智能体能力。

## 核心能力

- AI 职场沟通与面试辅导助手：支持面试准备、简历表达优化、汇报话术生成、同事协作建议和领导沟通建议。
- RAG 知识库问答：基于本地职场沟通、简历、面试知识文档提供更贴近业务场景的回答。
- Tool Calling：保留联网搜索、网页抓取、文件操作、资源下载、终端操作、PDF 生成等工具能力。
- MCP：保留图片搜索 MCP 服务，可用于职场汇报封面、面试准备素材等资源检索。
- YuManus 超级智能体：基于 ReAct 模式进行任务规划和工具调用，适合生成学习路线、面试计划、简历优化方案和行动清单。

## 技术栈

- Java 21
- Spring Boot 3
- Spring AI
- LangChain4j
- RAG / PGVector
- Tool Calling
- MCP
- SSE
- Vue 3 + Vite

## 后端启动

```bash
./mvnw spring-boot:run
```

默认服务地址：`http://localhost:8123/api`

常用接口：

- `/api/ai/love_app/chat/sse`：职场沟通与面试辅导助手 SSE 聊天接口
- `/api/ai/manus/chat`：YuManus 超级智能体聊天接口

> 说明：为了保证兼容性，后端接口路径暂时保留原有路径，不影响前端页面展示为 Career Copilot。

## 前端启动

`ash
# 进入前端子目录后执行
npm install
npm run dev
`"
# Career Copilot

Career Copilot 是一个基于 Spring AI 的 AI 职场沟通与面试辅导助手，面向求职、职场表达和团队协作场景，提供多轮对话、RAG 知识库、Tool Calling、MCP 工具接入和 ReAct 超级智能体能力。

## 核心能力

- AI 职场沟通与面试辅导助手：支持面试准备、简历表达优化、汇报话术生成、同事协作建议和领导沟通建议。
- RAG 知识库问答：基于本地职场沟通、简历、面试知识文档提供更贴近业务场景的回答。
- Tool Calling：保留联网搜索、网页抓取、文件操作、资源下载、终端操作、PDF 生成等工具能力。
- MCP：保留图片搜索 MCP 服务，可用于职场汇报封面、面试准备素材等资源检索。
- YuManus 超级智能体：基于 ReAct 模式进行任务规划和工具调用，适合生成学习路线、面试计划、简历优化方案和行动清单。

## 技术栈

- Java 21
- Spring Boot 3
- Spring AI
- LangChain4j
- RAG / PGVector
- Tool Calling
- MCP
- SSE
- Vue 3 + Vite

## 后端启动

```bash
./mvnw spring-boot:run
```

默认服务地址：`http://localhost:8123/api`

常用接口：

- `/api/ai/love_app/chat/sse`：职场沟通与面试辅导助手 SSE 聊天接口
- `/api/ai/manus/chat`：YuManus 超级智能体聊天接口

> 说明：为了保证兼容性，后端接口路径暂时保留原有路径，不影响前端页面展示为 Career Copilot。

## 前端启动

```bash
cd z-career-copilot-frontend
npm install
npm run dev
```

## 前端构建

```bash
cd z-career-copilot-frontend
npm run build
```

## 项目命名说明

当前项目展示名为 `Career Copilot`，Maven artifact 为 `z-career-copilot`。Java 包名 `com.career.copilot` 暂时保留，以避免大规模包路径迁移影响 Spring 组件扫描、测试和已有接口稳定性。 = # Career Copilot

Career Copilot 是一个基于 Spring AI 的 AI 职场沟通与面试辅导助手，面向求职、职场表达和团队协作场景，提供多轮对话、RAG 知识库、Tool Calling、MCP 工具接入和 ReAct 超级智能体能力。

## 核心能力

- AI 职场沟通与面试辅导助手：支持面试准备、简历表达优化、汇报话术生成、同事协作建议和领导沟通建议。
- RAG 知识库问答：基于本地职场沟通、简历、面试知识文档提供更贴近业务场景的回答。
- Tool Calling：保留联网搜索、网页抓取、文件操作、资源下载、终端操作、PDF 生成等工具能力。
- MCP：保留图片搜索 MCP 服务，可用于职场汇报封面、面试准备素材等资源检索。
- YuManus 超级智能体：基于 ReAct 模式进行任务规划和工具调用，适合生成学习路线、面试计划、简历优化方案和行动清单。

## 技术栈

- Java 21
- Spring Boot 3
- Spring AI
- LangChain4j
- RAG / PGVector
- Tool Calling
- MCP
- SSE
- Vue 3 + Vite

## 后端启动

```bash
./mvnw spring-boot:run
```

默认服务地址：`http://localhost:8123/api`

常用接口：

- `/api/ai/love_app/chat/sse`：职场沟通与面试辅导助手 SSE 聊天接口
- `/api/ai/manus/chat`：YuManus 超级智能体聊天接口

> 说明：为了保证兼容性，后端接口路径暂时保留原有路径，不影响前端页面展示为 Career Copilot。

## 前端启动

```bash
cd z-career-copilot-frontend
npm install
npm run dev
```

## 前端构建

```bash
cd z-career-copilot-frontend
npm run build
```

## 项目命名说明

当前项目展示名为 `Career Copilot`，Maven artifact 为 `z-career-copilot`。Java 包名 `com.career.copilot` 暂时保留，以避免大规模包路径迁移影响 Spring 组件扫描、测试和已有接口稳定性。 -replace '## 前端构建\r?\n\r?\n`ash\r?\ncd z-career-copilot-frontend\r?\nnpm run build\r?\n`', 

## 前端构建

```bash
cd z-career-copilot-frontend
npm run build
```

## 项目命名说明

当前项目展示名为 `Career Copilot`，Maven artifact 为 `z-career-copilot`。Java 包名 `com.career.copilot` 暂时保留，以避免大规模包路径迁移影响 Spring 组件扫描、测试和已有接口稳定性。