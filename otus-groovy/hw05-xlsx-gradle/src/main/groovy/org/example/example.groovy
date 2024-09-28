package org.example

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.xssf.streaming.SXSSFWorkbook

static void main(String[] args) {
    SXSSFWorkbook workbook = new SXSSFWorkbook()

    def sheet = workbook.createSheet()

    def data = [
            [1, true, null, 'hello'],
    ]

    CellStyle style = workbook.createCellStyle()

    def rowIdx = 0
    data.forEach {
        def row = sheet.createRow(++rowIdx)
        def cellIdx = 0
        it.forEach { cellData ->
            Cell cell = row.createCell(++cellIdx)
            cell.setCellValue(cellData?.toString())
            cell.setCellStyle(style)
        }
    }

    FileOutputStream fos = new FileOutputStream(new File("test.xlsx"))
    try {
        workbook.write(fos);
        workbook.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
