<template>
  <div class="chat-container" :class="aiType">
    <div class="chat-messages" ref="messagesContainer">
      <div v-for="(msg, index) in messages" :key="index" class="message-wrapper">
        <div v-if="!msg.isUser" class="message ai-message" :class="[msg.type]">
          <div class="avatar ai-avatar">
            <span>CC</span>
          </div>
          <div class="message-bubble">
            <div class="message-label">{{ getAiLabel(msg, index) }}</div>
            <div class="message-content">
              {{ msg.content }}
              <span v-if="connectionStatus === 'connecting' && index === messages.length - 1" class="typing-indicator">▋</span>
            </div>
            <div class="message-time">{{ formatTime(msg.time) }}</div>
          </div>
        </div>

        <div v-else class="message user-message" :class="[msg.type]">
          <div class="message-bubble">
            <div class="message-label">你</div>
            <div class="message-content">{{ msg.content }}</div>
            <div class="message-time">{{ formatTime(msg.time) }}</div>
          </div>
          <div class="avatar user-avatar">
            <div class="avatar-placeholder">我</div>
          </div>
        </div>
      </div>
    </div>

    <div class="chat-input-container">
      <div class="chat-input">
        <textarea
          v-model="inputMessage"
          @keydown.enter.prevent="sendMessage"
          :placeholder="placeholder"
          class="input-box"
          :disabled="connectionStatus === 'connecting'"
        ></textarea>
        <button
          @click="sendMessage"
          class="send-button"
          :disabled="connectionStatus === 'connecting' || !inputMessage.trim()"
        >发送</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick, watch } from 'vue'

const props = defineProps({
  messages: {
    type: Array,
    default: () => []
  },
  connectionStatus: {
    type: String,
    default: 'disconnected'
  },
  aiType: {
    type: String,
    default: 'default'
  },
  placeholder: {
    type: String,
    default: '输入你的问题，按 Enter 发送'
  }
})

const emit = defineEmits(['send-message'])

const inputMessage = ref('')
const messagesContainer = ref(null)

const sendMessage = () => {
  if (!inputMessage.value.trim()) return

  emit('send-message', inputMessage.value)
  inputMessage.value = ''
}

const formatTime = (timestamp) => {
  const date = new Date(timestamp)
  return date.toLocaleTimeString('zh-CN', { hour: '2-digit', minute: '2-digit' })
}

const getAiLabel = (msg, index) => {
  if (msg.type === 'ai-error') {
    return '连接状态'
  }
  if (props.aiType === 'super') {
    return props.connectionStatus === 'connecting' && index === props.messages.length - 1
      ? '任务进展'
      : '最终结果'
  }
  return 'Career Copilot'
}

const scrollToBottom = async () => {
  await nextTick()
  if (messagesContainer.value) {
    messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight
  }
}

watch(() => props.messages.length, () => {
  scrollToBottom()
})

watch(() => props.messages.map(m => m.content).join(''), () => {
  scrollToBottom()
})

onMounted(() => {
  scrollToBottom()
})
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: min(68vh, 720px);
  min-height: 560px;
  background: #ffffff;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 22px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.message-wrapper {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.message {
  display: flex;
  align-items: flex-start;
  max-width: 86%;
}

.user-message {
  margin-left: auto;
  flex-direction: row;
}

.ai-message {
  margin-right: auto;
}

.super .ai-message {
  max-width: 92%;
}

.avatar {
  width: 34px;
  height: 34px;
  border-radius: 8px;
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  font-weight: 800;
}

.user-avatar {
  margin-left: 10px;
}

.ai-avatar {
  margin-right: 10px;
  background: linear-gradient(135deg, #2563eb, #7c3aed);
  color: #ffffff;
}

.avatar-placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #d8e1f0;
  border-radius: 8px;
  background: #e8eefb;
  color: #334155;
  box-sizing: border-box;
}

.message-bubble {
  min-width: 120px;
  padding: 13px 15px;
  border-radius: 8px;
  word-wrap: break-word;
  box-shadow: 0 10px 24px rgba(51, 65, 85, 0.06);
}

.user-message .message-bubble {
  background: linear-gradient(135deg, #2563eb, #7c3aed);
  color: #ffffff;
  text-align: left;
}

.ai-message .message-bubble {
  border: 1px solid #e5ebf5;
  background: #f3f6fb;
  color: #233044;
  text-align: left;
}

.ai-message.ai-error .message-bubble {
  border-color: #fed7aa;
  background: #fff7ed;
  color: #9a3412;
}

.message-label {
  margin-bottom: 7px;
  color: inherit;
  font-size: 12px;
  font-weight: 800;
  opacity: 0.72;
}

.message-content {
  font-size: 15px;
  line-height: 1.72;
  white-space: pre-wrap;
}

.message-time {
  margin-top: 7px;
  font-size: 12px;
  opacity: 0.62;
  text-align: right;
}

.chat-input-container {
  background: #ffffff;
  border-top: 1px solid #edf1f7;
}

.chat-input {
  display: flex;
  align-items: flex-end;
  gap: 12px;
  padding: 16px 18px;
  box-sizing: border-box;
}

.input-box {
  flex-grow: 1;
  min-height: 24px;
  max-height: 96px;
  padding: 12px 14px;
  border: 1px solid #d8e1f0;
  border-radius: 8px;
  outline: none;
  resize: none;
  overflow-y: auto;
  color: #172033;
  background: #ffffff;
  font-size: 15px;
  transition: border-color 0.2s, box-shadow 0.2s;
  scrollbar-width: none;
  -ms-overflow-style: none;
}

.input-box::-webkit-scrollbar {
  display: none;
}

.input-box:focus {
  border-color: #2563eb;
  box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.12);
}

.send-button {
  height: 48px;
  padding: 0 20px;
  border: 0;
  border-radius: 8px;
  background: linear-gradient(135deg, #2563eb, #7c3aed);
  color: #ffffff;
  font-size: 15px;
  box-shadow: 0 12px 24px rgba(37, 99, 235, 0.22);
}

.send-button:hover:not(:disabled) {
  transform: translateY(-1px);
}

.typing-indicator {
  display: inline-block;
  margin-left: 2px;
  animation: blink 0.7s infinite;
}

@keyframes blink {
  0% { opacity: 0; }
  50% { opacity: 1; }
  100% { opacity: 0; }
}

.input-box:disabled,
.send-button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.ai-answer {
  animation: fadeIn 0.3s ease-in-out;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(4px); }
  to { opacity: 1; transform: translateY(0); }
}

@media (max-width: 768px) {
  .chat-container {
    height: min(72vh, 680px);
    min-height: 520px;
  }

  .message {
    max-width: 100%;
  }

  .message-content {
    font-size: 14px;
  }

  .chat-input {
    flex-direction: column;
    align-items: stretch;
    padding: 12px;
  }

  .send-button {
    width: 100%;
  }
}

@media (max-width: 480px) {
  .avatar {
    display: none;
  }

  .message-bubble {
    padding: 12px;
  }
}
</style>
