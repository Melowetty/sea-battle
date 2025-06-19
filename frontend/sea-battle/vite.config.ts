import { reactRouter } from "@react-router/dev/vite";
import tailwindcss from "@tailwindcss/vite";
import { defineConfig } from "vite";
import tsconfigPaths from "vite-tsconfig-paths";

export default defineConfig({
  plugins: [tailwindcss(), reactRouter(), tsconfigPaths()],
  server: {
    allowedHosts: true,
    proxy: {
      '/api': {
        target: 'https://d5d5ujno72nh9qu45pq5.sk0vql13.apigw.yandexcloud.net',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  },
  css: {
    modules: {
      localsConvention: 'camelCase' // или 'dashes'
    }
  }
  });
