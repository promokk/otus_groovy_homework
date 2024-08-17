package org.example

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CashMachineTest{

    @Test
    void testWithdrawalBanknotes() {
        CashMachine cashMachine = new CashMachine([
                new Banknote(10, 100, Currency.RUBLE),
                new Banknote(50, 100, Currency.RUBLE),
                new Banknote(100, 100, Currency.RUBLE),
                new Banknote(500, 100, Currency.RUBLE),
                new Banknote(1000, 100, Currency.RUBLE),
                new Banknote(5000, 100, Currency.RUBLE),
                new Banknote(1, 100, Currency.DOLLAR),
                new Banknote(2, 100, Currency.DOLLAR),
                new Banknote(5, 100, Currency.DOLLAR),
                new Banknote(20, 100, Currency.DOLLAR),
                new Banknote(50, 100, Currency.DOLLAR),
                new Banknote(100, 100, Currency.DOLLAR)
        ])

        String result01 = cashMachine.withdrawalBanknotes(160 as BigInteger, Currency.RUBLE)
        String result02 = cashMachine.withdrawalBanknotes(121 as BigInteger, Currency.RUBLE)
        String result03 = cashMachine.withdrawalBanknotes(124 as BigInteger, Currency.DOLLAR)
        String result04 = cashMachine.withdrawalBanknotes(500000 as BigInteger,  Currency.DOLLAR)
        String result05 = cashMachine.enteringBanknotes([10, 50, 50], Currency.RUBLE)
        String result06 = cashMachine.enteringBanknotes([1, 20, 100], Currency.DOLLAR)

        Assertions.assertEquals("Выдано: 160 ₽", result01)
        Assertions.assertEquals("Ошибка: невозвожно выдать часть средств - 1 из 121 ₽!", result02)
        Assertions.assertEquals("Выдано: 124 \$", result03)
        Assertions.assertEquals("Ошибка: недостаточно средств в банкомате!", result04)
        Assertions.assertEquals("Внесено: 110 ₽", result05)
        Assertions.assertEquals("Внесено: 121 \$", result06)
    }
}
