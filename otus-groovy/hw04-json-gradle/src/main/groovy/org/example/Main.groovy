package org.example

static void main(String[] args) {
    String sourceJson =
            new URL('https://raw.githubusercontent.com/Groovy-Developer/groovy-homeworks/main/hw-5/test.json').text

    BuilderSuper builderSuper = new BuilderSuper()

    println builderSuper.builderXml(sourceJson)
    println builderSuper.builderHtml(sourceJson)
}
