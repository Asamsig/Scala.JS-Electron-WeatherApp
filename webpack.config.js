const webpack = require('webpack');
const path = require('path');

// load the auto-generated webpack config file
module.exports = require('./scalajs.webpack.config');

// modify the output directory where the bundle.js file is written
module.exports.output = {
    path: path.resolve('../../../../web/js'),
    filename: "scalajselectron-bundle.js"
};
