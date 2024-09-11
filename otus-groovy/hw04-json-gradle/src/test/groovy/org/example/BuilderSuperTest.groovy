package org.example

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BuilderSuperTest {
    BuilderSuper builderSuper = new BuilderSuper()
    String sourceJson =
            new URL('https://raw.githubusercontent.com/Groovy-Developer/groovy-homeworks/main/hw-5/test.json').text

    @Test
    void testBuilderHtml() {
        def testHtml = "<html>\n" +
                "  <div>\n" +
                "    <div id='employee'>\n" +
                "      <p>РџСѓРїРєРёРЅ РњРѕСЂРєРІР° РЎРІРµРєР»РѕРІРёС‡</p>\n" +
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

        Assertions.assertEquals(testHtml, builderSuper.builderHtml(sourceJson).toString())
    }

    @Test
    void testBuilderXml() {
        def testXml = "<xml>\n" +
                "  <employee>\n" +
                "    <name>РџСѓРїРєРёРЅ РњРѕСЂРєРІР° РЎРІРµРєР»РѕРІРёС‡</name>\n" +
                "    <age>22</age>\n" +
                "    <secretIdentity>322-223</secretIdentity>\n" +
                "    <powers>\n" +
                "      <power>100</power>\n" +
                "      <power>50</power>\n" +
                "      <power>70</power>\n" +
                "    </powers>\n" +
                "  </employee>\n" +
                "</xml>"

        Assertions.assertEquals(testXml, builderSuper.builderXml(sourceJson).toString())
    }
}
