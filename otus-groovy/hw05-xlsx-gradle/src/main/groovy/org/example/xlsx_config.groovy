package org.example

import static org.example.XlsxBuilderDslRun.xlsxBuilder

xlsxBuilder("test01") {
    sheet(0) {
        name = "sheet"
        row(0) {
            cell(0) {
                value = 1
                style {
                    fontColor = "green"
                    backgroundColor = "red"
                }
            }
            cell(1) {
                value = "test"
                style {
                    fontColor = "red"
                    backgroundColor = "black"
                }
            }
            cell(2) {
                value = true
            }
        }
    }
}
