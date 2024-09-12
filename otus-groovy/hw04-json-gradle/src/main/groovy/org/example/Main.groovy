package org.example

static void main(String[] args) {
//    String sourceJson =
//            new URL('https://raw.githubusercontent.com/Groovy-Developer/groovy-homeworks/main/hw-5/test.json').text

    BuilderSuper builderSuper = new BuilderSuper()

    builderSuper.downloadFile(
            'jsonFile.json',
            'https://raw.githubusercontent.com/Groovy-Developer/groovy-homeworks/main/hw-5/test.json'
    )
    builderSuper.builderXmlFile('jsonFile.json', 'xmlFile.xml')
    println builderSuper.builderHtml('jsonFile.json')
}
