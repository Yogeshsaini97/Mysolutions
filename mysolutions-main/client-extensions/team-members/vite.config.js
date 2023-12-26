import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  vue: {
    template: {
      compilerOptions: {
        isCustomElement: tag => tag === 'my-component', // If needed
      },
    },
  },
  babel: {
    presets: [
      [
        '@vue/babel-preset-jsx',
        {
          mergeProps: false, // If needed
        },
      ],
    ],
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
