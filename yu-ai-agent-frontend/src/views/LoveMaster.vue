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
          <div class="assistant-kicker">Career Communication</div>
          <h1>职场沟通辅导</h1>
          <p class="assistant-subtitle">
            用于面试准备、简历表达、汇报话术、职场沟通建议，帮助你把复杂问题整理成清晰、可执行的表达方案。
          </p>
        </div>

        <aside class="assistant-status-strip" aria-label="会话状态">
          <div class="assistant-status-row">
            <span>会话状态</span>
            <span class="assistant-online">{{ statusText }}</span>
          </div>
          <div class="assistant-status-row">
            <span>会话 ID</span>
            <span class="assistant-status-value">{{ chatId }}</span>
          </div>
        </aside>
      </section>

      <section class="assistant-card">
        <div class="assistant-card-header">
          <div>
            <div class="assistant-card-title">Career Copilot 对话面板</div>
            <div class="assistant-card-caption">围绕目标岗位、项目经历、沟通对象和汇报场景进行多轮辅导。</div>
          </div>
          <span class="assistant-card-badge">Multi-turn coaching</span>
        </div>

        <ChatRoom
          :messages="messages"
          :connection-status="connectionStatus"
          ai-type="career"
          placeholder="描述你的面试、简历、汇报或职场沟通问题"
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
import { chatWithLoveApp } from '../api'

useHead({
  title: '职场沟通辅导 - Career Copilot',
  meta: [
    {
      name: 'description',
      content: 'Career Copilot 职场沟通辅导用于面试准备、简历表达、汇报话术和职场沟通建议。'
    },
    {
      name: 'keywords',
      content: 'Career Copilot,职场沟通辅导,AI面试辅导,简历表达,汇报话术'
    }
  ]
})

const router = useRouter()
const messages = ref([])
const chatId = ref('')
const connectionStatus = ref('disconnected')
let eventSource = null

const statusText = computed(() => {
  if (connectionStatus.value === 'connecting') return '生成中'
  if (connectionStatus.value === 'error') return '需重试'
  return '在线'
})

const generateChatId = () => {
  return 'career_' + Math.random().toString(36).substring(2, 10)
}

const addMessage = (content, isUser) => {
  messages.value.push({
    content,
    isUser,
    time: new Date().getTime()
  })
}

const sendMessage = (message) => {
  addMessage(message, true)

  if (eventSource) {
    eventSource.close()
  }

  const aiMessageIndex = messages.value.length
  addMessage('', false)

  connectionStatus.value = 'connecting'
  eventSource = chatWithLoveApp(message, chatId.value)

  eventSource.onmessage = (event) => {
    const data = event.data
    if (data && data !== '[DONE]' && aiMessageIndex < messages.value.length) {
      messages.value[aiMessageIndex].content += data
    }

    if (data === '[DONE]') {
      connectionStatus.value = 'disconnected'
      eventSource.close()
    }
  }

  eventSource.onerror = (error) => {
    console.error('SSE Error:', error)
    connectionStatus.value = 'error'
    eventSource.close()
  }
}

const goBack = () => {
  router.push('/')
}

onMounted(() => {
  chatId.value = generateChatId()
  addMessage('欢迎来到 Career Copilot。请告诉我你的面试、简历、汇报或职场沟通问题，我会给出可执行的建议和话术。', false)
})

onBeforeUnmount(() => {
  if (eventSource) {
    eventSource.close()
  }
})
</script>
