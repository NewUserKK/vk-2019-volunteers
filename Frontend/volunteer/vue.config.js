const path = require('path');

module.exports = {
    devServer: {
        proxy: 'http://demo135.foxtrot.vkhackathon.com:8080'
    },
    configureWebpack: {
        resolve: {
            alias: {
                vue$: 'vue/dist/vue.esm.js',
                '@': path.resolve(__dirname, 'src')
            }
        }
    },
};
