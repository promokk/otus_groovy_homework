package org.example

import groovy.test.GroovyTestCase

class CashMachineTest extends GroovyTestCase {

    void testWithdrawalBanknotes() {
        CashMachine cashMachine = new CashMachine([
                new Banknote(10, 100),
                new Banknote(50, 100),
                new Banknote(100, 100)
        ])

        def result01 = cashMachine.withdrawalBanknotes(160 as BigInteger)

        assertEquals("Выдано: 160", result01)
    }
}
