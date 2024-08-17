package org.example

class CashMachine {
    // Всего денег в банкомате
    private Object totalSum = [:]
    // Массив Banknote
    private Object nominal = [Banknote]

    CashMachine(banknotes) {
        this.totalSum = checkSum(banknotes)
        this.nominal = banknotes.sort {a,b -> a == b ? 0: a < b ? 1 : -1}
    }

    // Метод для расчета суммы денег в банкомате
    private def checkSum(Object banknotes) {
        Map sum = [:]
        for (i in Currency.values()){
            sum[i] = 0
        }
        for (banknote in banknotes) {
            sum[banknote.currency] += banknote.value * banknote.count
        }
        return this.totalSum = sum
    }

    // Метод для проверки средств определенной валюты
    private def checkMoney(BigInteger sumMoney, Currency currency) {
        if (this.totalSum[currency] - sumMoney < 0) {
            return false
        }
        return true
    }

    // Метод выводит инфо банкомата
    def checkStatus() {
        String str = "Инфо:\n"
        for (cur in this.totalSum) {
            str += "Осталось денежных средств в банкомате: ${cur.value} ${cur.key.value}" +
                    "\nКоличество банкнот ${cur.key}(${cur.key.value}):\n"
            for (banknote in this.nominal) {
                if (banknote.currency == cur.key)
                str += "Номинал ${banknote.value}: ${banknote.count}\n"
            }
        }
        return str
    }

//     Метод для вывода средств из банкомата
    def withdrawalBanknotes(BigInteger sumMoney, Currency currency) {
        BigInteger money = sumMoney
        Object nominal = this.nominal.clone()

         if (!checkMoney(money, currency)) {
             return "Ошибка: недостаточно средств в банкомате!"
         }

        for (int i = 0; i < nominal.size(); i++) {
            while (money - nominal[i].value >= 0 && nominal[i].count && nominal[i].currency == currency) {
                money -= nominal[i].value
                nominal[i]--
            }
        }
        if (money) {
            return "Ошибка: невозвожно выдать часть средств - ${money} из ${sumMoney} ${currency.value}!"
        }

        this.totalSum = checkSum(nominal)
        this.nominal = nominal
        return "Выдано: ${sumMoney} ${currency.value}"
    }

    // Метод для ввода средств в банкомат
    def enteringBanknotes(Object value, Currency currency) {
        Object nominal = this.nominal.clone()
        for (v in value) {
            int i = this.nominal.findIndexOf { it.value == v }
            nominal[i]++
        }

        this.totalSum = checkSum(nominal)
        this.nominal = nominal
        return "Внесено: ${value.sum()} ${currency.value}"
    }
}
