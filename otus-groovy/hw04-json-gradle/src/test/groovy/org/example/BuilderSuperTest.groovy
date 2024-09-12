package org.example

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BuilderSuperTest {
    BuilderSuper builderSuper = new BuilderSuper()

    @Test
    void testBuilderHtml() {
        builderSuper.downloadFile(
                'jsonFile.json',
                'https://raw.githubusercontent.com/Groovy-Developer/groovy-homeworks/main/hw-5/test.json'
        )
        def testHtml = "<html>\n" +
                "  <div>\n" +
                "    <div id='employee'>\n" +
                "      <p>Пупкин Морква Свеклович</p>\n" +
                "      <p>22</p>\n" +
                "      <p>322-223</p>\n" +
                "      <ul id='powers'>\n" +
                "        <li>100</li>\n" +
                "        <li>50</li>\n" +
                "        <li>70</li>\n" +
                "      </ul>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</html>"

        Assertions.assertEquals(testHtml, builderSuper.builderHtml('jsonFile.json'))
    }

    @Test
    void testBuilderXml() {
        builderSuper.downloadFile(
                'jsonFile.json',
                'https://raw.githubusercontent.com/Groovy-Developer/groovy-homeworks/main/hw-5/test.json'
        )
        def testXml = "<xml>\n" +
                "  <employee>\n" +
                "    <name>Пупкин Морква Свеклович</name>\n" +
                "    <age>22</age>\n" +
                "    <secretIdentity>322-223</secretIdentity>\n" +
                "    <powers>\n" +
                "      <power>100</power>\n" +
                "      <power>50</power>\n" +
                "      <power>70</power>\n" +
                "    </powers>\n" +
                "  </employee>\n" +
                "</xml>"

        builderSuper.builderXmlFile('jsonFile.json', 'xmlFile.xml')
        String xmlFile = ''
        new File('xmlFile.xml').withInputStream { stream ->
            xmlFile = stream.text
        }
        Assertions.assertEquals(testXml, xmlFile)
    }
}
