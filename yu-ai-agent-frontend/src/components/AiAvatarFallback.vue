<template>
  <div class="ai-avatar-fallback" :class="type">
    <span v-if="type === 'career'">CC</span>
    <span v-else>AI</span>
  </div>
</template>

<script setup>
defineProps({
  type: {
    type: String,
    default: 'default'
  }
})
</script>

<style scoped>
.ai-avatar-fallback {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  font-weight: 800;
  border-radius: 50%;
}

.career {
  background: linear-gradient(45deg, #0ea5e9, #22c55e);
}

.default, .super {
  background: linear-gradient(45deg, #3f51b5, #5677fc);
}
</style> 
