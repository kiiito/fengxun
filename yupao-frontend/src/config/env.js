// src/config/env.js
const isDevelopment =
    import.meta.env.MODE === 'development' ||
    window.location.hostname === 'localhost' && window.location.port === '5173'; // 开发端口

const isProduction = !isDevelopment;

const config = {
    development: {
        baseURL: '/api',
        loginURL: '/user/login'
    },
    production: {
        baseURL: 'http://localhost:8080/api',
        loginURL: '/user/login'
    }
};

export default isDevelopment ? config.development : config.production;