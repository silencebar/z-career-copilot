<template>
  <div class="home-page">
    <header class="top-nav">
      <button class="brand" type="button" @click="navigateTo('/')">
        <span class="brand-mark">CC</span>
        <span class="brand-name">Career Copilot</span>
      </button>

      <nav class="nav-links" aria-label="Primary navigation">
        <a href="#features">功能</a>
        <a href="#capabilities">技术能力</a>
        <button type="button" class="nav-link-button" @click="navigateTo('/career-assistant')">开始辅导</button>
      </nav>

      <a class="github-button" href="https://github.com/" target="_blank" rel="noreferrer">
        GitHub
      </a>
    </header>

    <main>
      <section class="hero-section">
        <div class="hero-copy">
          <div class="eyebrow">AI Career Communication Platform</div>
          <h1>
            <span>AI 职场沟通与</span>
            <span>面试辅导平台</span>
          </h1>
          <p class="hero-subtitle">
            面向求职与职场表达场景，帮助你准备面试、优化简历表达、打磨汇报话术，并生成更清晰的沟通行动方案。
          </p>
          <div class="hero-actions">
            <button class="primary-button" type="button" @click="navigateTo('/career-assistant')">
              开始职场辅导
            </button>
            <button class="secondary-button" type="button" @click="navigateTo('/super-agent')">
              使用智能体规划
            </button>
          </div>
        </div>

        <aside class="preview-panel" aria-label="Interview coaching preview">
          <div class="preview-header">
            <div>
              <div class="preview-title">面试辅导预览</div>
              <div class="preview-status">Multi-turn coaching session</div>
            </div>
            <span class="status-dot"></span>
          </div>

          <div class="chat-preview">
            <div class="message-row user">
              <span class="message-label">你</span>
              <div class="message-bubble">
                我下周面试 Java 后端岗位，项目经历总是讲得太散。
              </div>
            </div>
            <div class="message-row assistant">
              <span class="message-label">Career Copilot</span>
              <div class="message-bubble">
                可以按“项目背景、你的职责、技术方案、结果指标、复盘思考”组织。我先帮你生成 3 分钟项目表达稿。
              </div>
            </div>
            <div class="coach-note">
              <span>下一步</span>
              <strong>补充目标岗位 JD 与项目亮点</strong>
            </div>
          </div>
        </aside>
      </section>

      <section id="features" class="section-block">
        <div class="section-heading">
          <span>Core Modules</span>
          <h2>围绕真实职场场景组织能力</h2>
        </div>

        <div class="feature-grid">
          <article v-for="feature in features" :key="feature.title" class="feature-card">
            <div class="feature-icon">{{ feature.short }}</div>
            <h3>{{ feature.title }}</h3>
            <p>{{ feature.description }}</p>
            <button type="button" @click="navigateTo(feature.path)">
              {{ feature.action }}
            </button>
          </article>
        </div>
      </section>

      <section id="capabilities" class="capability-section">
        <div>
          <span class="capability-kicker">Technical Foundation</span>
          <h2>保留原有 AI 工程能力</h2>
        </div>
        <div class="capability-tags">
          <span v-for="tag in techTags" :key="tag">{{ tag }}</span>
        </div>
      </section>
    </main>

    <AppFooter />
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { useHead } from '@vueuse/head'
import AppFooter from '../components/AppFooter.vue'

useHead({
  title: 'Career Copilot - AI职场沟通与面试辅导平台',
  meta: [
    {
      name: 'description',
      content: 'Career Copilot 提供职场沟通、面试准备、简历表达和汇报话术生成能力。'
    },
    {
      name: 'keywords',
      content: 'Career Copilot,AI职场沟通,AI面试辅导,简历优化,汇报话术,Spring AI,RAG'
    }
  ]
})

const router = useRouter()

const features = [
  {
    short: 'M',
    title: '面试模拟',
    description: '围绕岗位 JD、项目经历和高频追问，生成结构化面试准备路径。',
    action: '开始模拟',
    path: '/career-assistant'
  },
  {
    short: 'R',
    title: '简历表达优化',
    description: '把职责描述改写成更可信的成果表达，突出技术方案与业务价值。',
    action: '优化表达',
    path: '/career-assistant'
  },
  {
    short: 'C',
    title: '职场沟通建议',
    description: '针对同事协作、向上沟通和冲突澄清，生成可直接使用的话术。',
    action: '获取建议',
    path: '/career-assistant'
  },
  {
    short: 'P',
    title: '汇报话术生成',
    description: '把进度、风险、阻塞和诉求组织成清晰的汇报模板与行动清单。',
    action: '生成模板',
    path: '/super-agent'
  }
]

const techTags = ['Spring AI', 'RAG', 'Tool Calling', 'MCP', 'Multi-turn Chat']

const navigateTo = (path) => {
  router.push(path)
}
</script>

<style scoped>
.home-page {
  min-height: 100vh;
  background:
    linear-gradient(180deg, #f7f9ff 0%, #eef4ff 48%, #ffffff 100%);
  color: #172033;
}

.top-nav {
  width: min(1180px, calc(100% - 40px));
  height: 72px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 24px;
}

.brand {
  display: inline-flex;
  align-items: center;
  gap: 10px;
  border: 0;
  background: transparent;
  color: #111827;
  font: inherit;
  font-weight: 700;
}

.brand-mark {
  width: 34px;
  height: 34px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
  background: linear-gradient(135deg, #2563eb, #7c3aed);
  color: #ffffff;
  font-size: 12px;
}

.brand-name {
  font-size: 17px;
}

.nav-links {
  display: flex;
  align-items: center;
  gap: 22px;
  color: #5b6475;
  font-size: 14px;
}

.nav-links a,
.nav-link-button {
  color: inherit;
  border: 0;
  background: transparent;
  font: inherit;
}

.nav-links a:hover,
.nav-link-button:hover {
  color: #2563eb;
}

.github-button,
.primary-button,
.secondary-button,
.feature-card button {
  border-radius: 8px;
  font-weight: 700;
  transition: transform 0.2s, box-shadow 0.2s, border-color 0.2s;
}

.github-button {
  padding: 10px 16px;
  border: 1px solid #d7deea;
  background: #ffffff;
  color: #172033;
  box-shadow: 0 8px 24px rgba(30, 41, 59, 0.06);
}

.hero-section {
  width: min(1180px, calc(100% - 40px));
  margin: 52px auto 0;
  display: grid;
  grid-template-columns: minmax(0, 1fr) 440px;
  gap: 52px;
  align-items: center;
}

.hero-copy {
  max-width: 690px;
}

.eyebrow,
.section-heading span,
.capability-kicker {
  color: #5b21b6;
  font-size: 13px;
  font-weight: 800;
  text-transform: uppercase;
}

.hero-copy h1 {
  margin: 16px 0 18px;
  font-size: 50px;
  line-height: 1.08;
  color: #0f172a;
  font-weight: 800;
  text-align: left;
}

.hero-copy h1 span {
  display: block;
}

.hero-subtitle {
  max-width: 620px;
  color: #5b6475;
  font-size: 18px;
  line-height: 1.75;
}

.hero-actions {
  display: flex;
  gap: 14px;
  margin-top: 30px;
  flex-wrap: wrap;
}

.primary-button,
.secondary-button {
  height: 46px;
  padding: 0 20px;
  border: 1px solid transparent;
  font-size: 15px;
}

.primary-button {
  background: linear-gradient(135deg, #2563eb, #7c3aed);
  color: #ffffff;
  box-shadow: 0 16px 30px rgba(37, 99, 235, 0.28);
}

.secondary-button {
  background: #ffffff;
  color: #1d4ed8;
  border-color: #c8d5f3;
}

.github-button:hover,
.primary-button:hover,
.secondary-button:hover,
.feature-card button:hover {
  transform: translateY(-1px);
}

.preview-panel {
  background: rgba(255, 255, 255, 0.92);
  border: 1px solid rgba(210, 219, 235, 0.9);
  border-radius: 8px;
  box-shadow: 0 26px 60px rgba(51, 65, 85, 0.14);
  overflow: hidden;
}

.preview-header {
  min-height: 70px;
  padding: 18px 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #edf1f7;
}

.preview-title {
  font-weight: 800;
  color: #111827;
}

.preview-status {
  margin-top: 4px;
  color: #64748b;
  font-size: 13px;
}

.status-dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background: #22c55e;
  box-shadow: 0 0 0 5px rgba(34, 197, 94, 0.14);
}

.chat-preview {
  padding: 22px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.message-row {
  display: flex;
  flex-direction: column;
  gap: 7px;
}

.message-row.user {
  align-items: flex-end;
}

.message-label {
  color: #64748b;
  font-size: 12px;
  font-weight: 700;
}

.message-bubble {
  max-width: 88%;
  padding: 13px 15px;
  border-radius: 8px;
  font-size: 14px;
  line-height: 1.65;
}

.user .message-bubble {
  background: #2563eb;
  color: #ffffff;
}

.assistant .message-bubble {
  background: #f3f6fb;
  color: #233044;
}

.coach-note {
  margin-top: 4px;
  padding: 14px;
  border-radius: 8px;
  background: linear-gradient(135deg, rgba(37, 99, 235, 0.1), rgba(124, 58, 237, 0.1));
  border: 1px solid rgba(124, 58, 237, 0.15);
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.coach-note span {
  color: #6b7280;
  font-size: 12px;
}

.coach-note strong {
  color: #1e1b4b;
  font-size: 14px;
}

.section-block {
  width: min(1180px, calc(100% - 40px));
  margin: 86px auto 0;
}

.section-heading {
  max-width: 620px;
  margin-bottom: 26px;
}

.section-heading h2,
.capability-section h2 {
  margin: 8px 0 0;
  color: #0f172a;
  font-size: 30px;
  line-height: 1.25;
}

.feature-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 18px;
}

.feature-card {
  min-height: 260px;
  padding: 22px;
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  box-shadow: 0 14px 34px rgba(51, 65, 85, 0.08);
  display: flex;
  flex-direction: column;
}

.feature-icon {
  width: 38px;
  height: 38px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #ffffff;
  font-weight: 800;
  background: linear-gradient(135deg, #2563eb, #7c3aed);
}

.feature-card h3 {
  margin: 18px 0 10px;
  color: #111827;
  font-size: 18px;
}

.feature-card p {
  margin: 0;
  color: #64748b;
  font-size: 14px;
  line-height: 1.7;
}

.feature-card button {
  height: 40px;
  margin-top: auto;
  border: 1px solid #d8e1f0;
  background: #f8fbff;
  color: #1d4ed8;
}

.capability-section {
  width: min(1180px, calc(100% - 40px));
  margin: 54px auto 76px;
  padding: 28px;
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  box-shadow: 0 14px 34px rgba(51, 65, 85, 0.08);
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 26px;
}

.capability-tags {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-end;
  gap: 10px;
}

.capability-tags span {
  padding: 9px 12px;
  border-radius: 8px;
  background: #f1f5ff;
  color: #334155;
  border: 1px solid #dbe6ff;
  font-size: 13px;
  font-weight: 700;
}

@media (max-width: 960px) {
  .nav-links {
    display: none;
  }

  .hero-section {
    grid-template-columns: 1fr;
  }

  .preview-panel {
    max-width: 620px;
  }

  .feature-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .capability-section {
    align-items: flex-start;
    flex-direction: column;
  }

  .capability-tags {
    justify-content: flex-start;
  }
}

@media (max-width: 640px) {
  .top-nav,
  .hero-section,
  .section-block,
  .capability-section {
    width: min(100% - 28px, 1180px);
  }

  .github-button {
    display: none;
  }

  .hero-section {
    margin-top: 28px;
  }

  .hero-copy h1 {
    font-size: 36px;
  }

  .hero-subtitle {
    font-size: 16px;
  }

  .feature-grid {
    grid-template-columns: 1fr;
  }
}
</style>
