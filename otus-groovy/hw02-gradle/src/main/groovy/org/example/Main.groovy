package org.example

static void main(String[] args) {
    CashMachine cashMachine = new CashMachine([
            new Banknote(10, 100),
            new Banknote(50, 100),
            new Banknote(100, 100),
            new Banknote(500, 100),
            new Banknote(1000, 100),
            new Banknote(5000, 100)
    ])

    println cashMachine.withdrawalBanknotes(160 as BigInteger)
    println cashMachine.withdrawalBanknotes(121 as BigInteger)
    println cashMachine.withdrawalBanknotes(6420 as BigInteger)
    println cashMachine.withdrawalBanknotes(272680 as BigInteger)
    println cashMachine.withdrawalBanknotes(500000 as BigInteger)
    println "-----"
    println cashMachine.checkStatusSum()
}
