package org.example

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.streaming.SXSSFCell
import org.apache.poi.xssf.streaming.SXSSFRow
import org.apache.poi.xssf.streaming.SXSSFSheet
import org.apache.poi.xssf.streaming.SXSSFWorkbook

class XlsxBuilderDslRun {
    static void xlsxBuilder(String fileName, @DelegatesTo(value = XlsxBuilderDsl) Closure closure) {
        def xlsxBuilderDsl = new XlsxBuilderDsl(fileName)
        closure = closure.rehydrate(xlsxBuilderDsl, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()

        xlsxBuilderDsl.createXlsx()
    }
}

class XlsxBuilderDsl {
    String fileName
    List<SheetDsl> sheetArr = []
    private SheetDsl sheet

    XlsxBuilderDsl(String fileName) {
        this.fileName = fileName
    }

    def sheet(int idx, @DelegatesTo(value = SheetDsl) Closure closure) {
        sheet = new SheetDsl(idx)
        closure = closure.rehydrate(sheet, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()

        sheetArr << sheet
    }

    def createXlsx() {
        SXSSFWorkbook workbook = new SXSSFWorkbook()
        sheetArr.each { itSheet ->
            SXSSFSheet sheet = workbook.createSheet("${itSheet.name}-${itSheet.idx}")
            itSheet.rowArr.each { itRow ->
                SXSSFRow row = sheet.createRow(itRow.idx)
                itRow.cellArr.each { itCell ->
                    SXSSFCell cell = row.createCell(itCell.idx)
                    CellStyle style = workbook.createCellStyle()
                    Font font = workbook.createFont()
                    cell.setCellValue(itCell.value?.toString())
                    try {
                        if (itCell.style) {
                            def color = itCell.style['fontColor'].toString().toUpperCase()
                            font.setColor(IndexedColors."${color}".getIndex())
                            color = itCell.style['backgroundColor'].toString().toUpperCase()
                            style.setFillForegroundColor(IndexedColors."${color}".getIndex())
                            style.setFillPattern(FillPatternType.SOLID_FOREGROUND)
                            style.setFont(font)
                            cell.setCellStyle(style)
                        }
                    } catch (Exception ignored) {
                        println("Выбран некорректный цвет!")
                    }
                }
            }
        }

        FileOutputStream fos = new FileOutputStream(new File("${fileName}.xlsx"))
        try {
            workbook.write(fos);
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class SheetDsl {
    int idx
    String name
    List<RowDsl> rowArr = []
    private RowDsl row

    SheetDsl(int idx = 0) {
        this.idx = idx
        this.name = idx.toString()
    }

    def row(int idx, @DelegatesTo(value = RowDsl) Closure closure) {
        row = new RowDsl(idx)
        closure = closure.rehydrate(row, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()

        rowArr << row
    }
}

class RowDsl {
    int idx
    List<CellDsl> cellArr = []
    private CellDsl cell

    RowDsl(int idx = 0) {
        this.idx = idx
    }

    def cell(int idx, @DelegatesTo(value = CellDsl) Closure closure) {
        cell = new CellDsl(idx)
        closure = closure.rehydrate(cell, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()

        cellArr << cell
    }
}

class CellDsl {
    int idx
    def value
    Map style = [:]

    CellDsl(int idx = 0) {
        this.idx = idx
    }

    def style(Closure closure) {
        style.with closure
    }
}
