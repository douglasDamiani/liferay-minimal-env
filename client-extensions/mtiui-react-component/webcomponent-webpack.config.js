const path = require("path");
module.exports = {
    mode: 'development',
    // entry: './src/components/ChildWebComponent.tsx',
    entry: ['./src/components/title/ChildWebComponent.tsx', './src/components/button/ButtonWebComponent.tsx'],
    module: {
        rules: [
            {
            test: /\.tsx?$/,
            use: 'ts-loader',
            exclude: /node_modules/,
            },
        ],
    },
    resolve: {
        extensions: [".ts", ".tsx", ".js"],
    },
    output: {
        path: path.resolve(__dirname, "build"),
        filename: 'file.js',
    },
}