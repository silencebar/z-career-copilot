<template>
  <div class="assistant-page">
    <main class="assistant-shell">
      <header class="assistant-topbar">
        <button class="assistant-back-button" type="button" @click="goBack">← 返回</button>
        <div class="assistant-brand">
          <span class="assistant-brand-mark">CC</span>
          <span>Career Copilot</span>
        </div>
      </header>

      <section class="assistant-hero">
        <div>
          <div class="assistant-kicker">Agent Planning</div>
          <h1>智能体任务规划</h1>
          <p class="assistant-subtitle">
            支持联网搜索、文件读写、计划生成和工具链执行，把复杂任务整理为任务进展与最终结果。
          </p>
        </div>

        <aside class="assistant-status-strip" aria-label="智能体状态">
          <div class="assistant-status-row">
            <span>执行状态</span>
            <span class="assistant-online">{{ statusText }}</span>
          </div>
          <div class="assistant-status-row">
            <span>模式</span>
            <span class="assistant-status-value">ReAct Agent</span>
          </div>
        </aside>
      </section>

      <section class="assistant-card">
        <div class="assistant-card-header">
          <div>
            <div class="assistant-card-title">Career Copilot 智能体面板</div>
            <div class="assistant-card-caption">任务进展会被整理为卡片式内容，最终方案会合并到同一结果卡片中。</div>
          </div>
          <span class="assistant-card-badge">Tool-enabled planning</span>
        </div>

        <ChatRoom
          :messages="messages"
          :connection-status="connectionStatus"
          ai-type="super"
          placeholder="输入学习路线、面试计划、文件生成或工具执行任务"
          @send-message="sendMessage"
        />
      </section>
    </main>

    <AppFooter />
  </div>
</template>

<script setup>
import { computed, ref, onMounted, onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'
import { useHead } from '@vueuse/head'
import ChatRoom from '../components/ChatRoom.vue'
import AppFooter from '../components/AppFooter.vue'
import { chatWithCareerAgent } from '../api'

useHead({
  title: '智能体任务规划 - Career Copilot',
  meta: [
    {
      name: 'description',
      content: 'Career Copilot 智能体任务规划支持联网搜索、文件读写、计划生成和工具链执行。'
    },
    {
      name: 'keywords',
      content: 'Career Copilot,智能体任务规划,ReAct Agent,Tool Calling,MCP,学习路线,面试计划'
    }
  ]
})

const router = useRouter()
const messages = ref([])
const connectionStatus = ref('disconnected')
let eventSource = null

const statusText = computed(() => {
  if (connectionStatus.value === 'connecting') return '执行中'
  if (connectionStatus.value === 'error') return '需重试'
  return '在线'
})

const addMessage = (content, isUser, type = '') => {
  messages.value.push({
    content,
    isUser,
    type,
    time: new Date().getTime()
  })
}

const sendMessage = (message) => {
  addMessage(message, true, 'user-question')

  if (eventSource) {
    eventSource.close()
  }

  connectionStatus.value = 'connecting'

  const aiMessageIndex = messages.value.length
  addMessage('', false, 'ai-answer')

  const normalizeCareerAgentChunk = (data) => {
    return data
      .replace(/^Step\s+\d+\s*[:：]\s*/i, '')
      .replace(/^执行结束：达到最大步骤（\d+）\s*$/i, '')
      .replace(/^思考完成\s*-\s*无需行动\s*$/i, '')
      .trim()
  }

  const appendToAiMessage = (content, type = 'ai-answer') => {
    if (!content) {
      return
    }
    const current = messages.value[aiMessageIndex]
    if (current) {
      const separator = current.content && !current.content.endsWith('\n') ? '\n\n' : ''
      current.content += separator + content
      current.type = type
    }
  }

  eventSource = chatWithCareerAgent(message)

  eventSource.onmessage = (event) => {
    const rawData = event.data || ''
    if (rawData === '[DONE]') {
      connectionStatus.value = 'disconnected'
      eventSource.close()
      return
    }

    const data = normalizeCareerAgentChunk(rawData)
    if (data) {
      appendToAiMessage(data)
    }
  }

  eventSource.onerror = (error) => {
    console.error('SSE Error:', error)
    eventSource.close()

    const current = messages.value[aiMessageIndex]
    if (current && !current.content) {
      connectionStatus.value = 'error'
      appendToAiMessage('连接中断，请稍后重试。', 'ai-error')
    } else {
      connectionStatus.value = 'disconnected'
    }
  }
}

const goBack = () => {
  router.push('/')
}

onMounted(() => {
  addMessage('你好，我是 Career Copilot 智能体。你可以让我规划学习路线、准备面试、优化简历，或生成带工具执行的行动方案。', false, 'ai-answer')
})

onBeforeUnmount(() => {
  if (eventSource) {
    eventSource.close()
  }
})
</script>
