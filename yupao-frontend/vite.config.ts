import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue';
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { VantResolver } from '@vant/auto-import-resolver';
// https://vite.dev/config/
export default defineConfig({
  plugins: [vue(),AutoImport({
    resolvers: [VantResolver()],
  }),
    Components({
      resolvers: [VantResolver()],
    }),],
  build: {
    outDir: 'dist'
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/uploads': {
        target: 'http://localhost:8080/api',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/uploads/, '/uploads')
      }
    }
  }
})


