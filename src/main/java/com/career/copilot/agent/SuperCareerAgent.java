package com.career.copilot.agent;

import com.career.copilot.advisor.MyLoggerAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.stereotype.Component;

/**
 * Career Copilot 超级智能体（拥有自主规划能力，可以直接使用）
 */
@Component
public class SuperCareerAgent extends ToolCallAgent {

    public SuperCareerAgent(ToolCallback[] allTools, ChatModel dashscopeChatModel) {
        super(allTools);
        this.setName("superCareerAgent");
        String SYSTEM_PROMPT = """
                你是 Career Copilot 平台中的超级智能体，专注于职场沟通、面试辅导、简历表达、学习路线规划和汇报话术生成。
                你的目标不是展示思考过程，而是交付可直接执行的最终方案。
                当用户提出学习路线规划、面试准备、简历优化、职场沟通建议、汇报模板、行动清单等问题时，
                如果不需要外部信息或文件操作，请直接给出结构化回答，不要调用工具。
                回答应尽量包含：目标拆解、分阶段计划、时间安排、重点任务、可复用话术、检查清单、下一步行动。
                只有在用户明确要求联网搜索、下载资源、读取/写入文件、生成 PDF、执行终端命令，或确实需要外部实时信息时，才调用合适的工具。
                使用中文回答，语气专业、清晰、具体，避免空泛建议。
                """;
        this.setSystemPrompt(SYSTEM_PROMPT);
        String NEXT_STEP_PROMPT = """
                请根据用户当前问题决定下一步：
                1. 如果可以直接回答，请不要调用工具，直接输出完整的最终方案。
                2. 如果需要工具，请只调用必要工具；工具返回后，继续整合结果，形成最终方案。
                3. 最终方案建议使用清晰标题，并按模块输出，例如：
                   - 目标判断
                   - 分阶段计划
                   - 每日/每周行动安排
                   - 面试或沟通话术
                   - 风险提醒
                   - 下一步清单
                4. 不要只回复“无需行动”或“已完成思考”，必须给用户可执行内容。
                5. 当任务已经完整回答，直接输出最终回答即可；只有需要显式终止工具链时才调用 `terminate` 工具。
                """;
        this.setNextStepPrompt(NEXT_STEP_PROMPT);
        this.setMaxSteps(20);
        // 初始化 AI 对话客户端
        ChatClient chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultAdvisors(new MyLoggerAdvisor())
                .build();
        this.setChatClient(chatClient);
    }
}

