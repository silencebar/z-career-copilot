<template>
  <footer class="app-footer">
    <div class="footer-inner">
      <div class="footer-brand">
        <span class="brand-mark">CC</span>
        <div>
          <strong>Career Copilot</strong>
          <p>AI workplace communication and interview coaching platform.</p>
        </div>
      </div>

      <div class="footer-meta">
        <a href="https://github.com/" target="_blank" rel="noreferrer">GitHub Repository</a>
        <span>Spring AI · RAG · Tool Calling · MCP</span>
      </div>
    </div>

    <div class="footer-bottom">
      © 2026 Career Copilot. All rights reserved.
    </div>
  </footer>
</template>

<style scoped>
.app-footer {
  width: 100%;
  border-top: 1px solid #e2e8f0;
  background: #ffffff;
  color: #64748b;
}

.footer-inner {
  width: min(1180px, calc(100% - 40px));
  margin: 0 auto;
  padding: 26px 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 24px;
}

.footer-brand {
  display: flex;
  align-items: center;
  gap: 12px;
}

.brand-mark {
  width: 34px;
  height: 34px;
  border-radius: 8px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #2563eb, #7c3aed);
  color: #ffffff;
  font-size: 12px;
  font-weight: 800;
}

.footer-brand strong {
  color: #111827;
  font-size: 15px;
}

.footer-brand p {
  margin: 3px 0 0;
  font-size: 13px;
}

.footer-meta {
  display: flex;
  align-items: center;
  gap: 16px;
  flex-wrap: wrap;
  justify-content: flex-end;
  font-size: 13px;
}

.footer-meta a {
  color: #2563eb;
  font-weight: 700;
}

.footer-bottom {
  width: min(1180px, calc(100% - 40px));
  margin: 0 auto;
  padding: 16px 0 22px;
  border-top: 1px solid #edf2f7;
  font-size: 13px;
}

@media (max-width: 720px) {
  .footer-inner {
    align-items: flex-start;
    flex-direction: column;
  }

  .footer-meta {
    justify-content: flex-start;
  }
}
</style>
