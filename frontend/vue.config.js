const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    chainWebpack: config => {
        config.module.rule('svg').uses.clear()
        config.module
            .rule('svg')
            .use('vue-svg-loader')
            .loader('vue-svg-loader')
    }
})

