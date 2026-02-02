<template>
  <div class="error-page">
    <div class="error-content">
      <h1 class="error-code">404</h1>
      <h2 class="error-title">资源未找到</h2>
      <p class="error-message">
        抱歉，您请求的页面或资源不存在。
      </p>
      <router-link to="/" class="back-home-btn">
        返回首页
      </router-link>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.error-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f9f9f9;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
}

.error-content {
  padding: 2rem;
  border-radius: 12px;
  background: white;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  max-width: 500px;
}

.error-code {
  font-size: 6rem;
  margin: 0;
  color: #ff6b6b;
  font-weight: bold;
}

.error-title {
  font-size: 1.8rem;
  margin: 1rem 0 0.5rem;
  color: #333;
}

.error-message {
  font-size: 1rem;
  color: #666;
  line-height: 1.6;
}

.back-home-btn {
  display: inline-block;
  margin-top: 1.5rem;
  padding: 0.6rem 1.5rem;
  background-color: #42b883;
  color: white;
  text-decoration: none;
  border-radius: 6px;
  font-weight: 600;
  transition: background-color 0.3s ease;
}

.back-home-btn:hover {
  background-color: #359a6a;
}
</style>
