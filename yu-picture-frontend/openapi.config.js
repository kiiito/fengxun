import { generateService } from '@umijs/openapi'

// OpenAPI 代码生成配置
generateService({
  // 指定请求库的导入路径，使用项目中封装的 request 模块
  requestLibPath: "import request from '@/request'",
  // API 文档的 JSON Schema 地址，从本地后端服务获取
  schemaPath: 'http://localhost:8123/api/v2/api-docs',
  // 生成的服务代码存放路径，会自动生成到 src 目录下
  serversPath: './src',
})

