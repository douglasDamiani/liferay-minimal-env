import path from "path";
import fs from "fs";
import { fileURLToPath } from 'url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));

export default {
    mode: 'development',
    // entry: './src/components/ChildWebComponent.tsx',
    entry: () => {

        const relativePath = './src/components/'
        const files = fs.readdirSync(relativePath, { recursive: true });

        const filesFiltered = files.filter(file => {
            if (file.includes(".web-component.tsx"))
                return file;
        }).map((file) => {
            return path.join(__dirname, relativePath, file)
        });

        return filesFiltered;
    },
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
