const path = require('path');

module.exports = {
    entry: {
        rcg: './src/index.ts',
        admin: './src/admin.js'
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/
            }
        ]
    },
    resolve: {
        extensions: [ '.tsx', '.ts', '.js' ]
    },
    output: {
        filename: '[name].js',
        path: path.resolve('../src/main/resources/public', 'resources')
    }
};