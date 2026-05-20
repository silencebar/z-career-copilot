import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue'),
    meta: {
      title: '首页 - Career Copilot',
      description: 'Career Copilot 提供职场沟通辅导、面试准备、简历表达、汇报话术和智能体任务规划。'
    }
  },
  {
    path: '/career-assistant',
    name: 'CareerAssistant',
    component: () => import('../views/LoveMaster.vue'),
    meta: {
      title: '职场沟通辅导 - Career Copilot',
      description: '用于面试准备、简历表达、汇报话术和职场沟通建议。'
    }
  },
  {
    path: '/super-agent',
    name: 'SuperAgent',
    component: () => import('../views/SuperAgent.vue'),
    meta: {
      title: '智能体任务规划 - Career Copilot',
      description: '支持联网搜索、文件读写、计划生成和工具链执行。'
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 全局导航守卫，设置文档标题
router.beforeEach((to, from, next) => {
  // 设置页面标题
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

export default router 

