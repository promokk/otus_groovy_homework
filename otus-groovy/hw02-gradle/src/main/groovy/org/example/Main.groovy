package org.example

static void main(String[] args) {
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

    println cashMachine.withdrawalBanknotes(160 as BigInteger, Currency.RUBLE)
    println cashMachine.withdrawalBanknotes(121 as BigInteger, Currency.RUBLE)
    println cashMachine.withdrawalBanknotes(6420 as BigInteger, Currency.RUBLE)
    println cashMachine.withdrawalBanknotes(272680 as BigInteger, Currency.RUBLE)
    println cashMachine.withdrawalBanknotes(500000 as BigInteger,  Currency.RUBLE)
    println "-----"
    println cashMachine.withdrawalBanknotes(160 as BigInteger, Currency.DOLLAR)
    println cashMachine.withdrawalBanknotes(121 as BigInteger, Currency.DOLLAR)
    println cashMachine.withdrawalBanknotes(6420 as BigInteger, Currency.DOLLAR)
    println cashMachine.withdrawalBanknotes(272680 as BigInteger, Currency.DOLLAR)
    println cashMachine.withdrawalBanknotes(500000 as BigInteger,  Currency.DOLLAR)
    println "-----"
    println cashMachine.enteringBanknotes([10, 50, 50], Currency.RUBLE)
    println cashMachine.enteringBanknotes([1, 20, 100], Currency.DOLLAR)
    println "-----"
    println cashMachine.checkStatus()
}
