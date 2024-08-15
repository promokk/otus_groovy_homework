package org.example

class CashMachine {
    // Всего денег в банкомате
    private BigInteger totalSum = 0
    // Массив Banknote
    private Object nominal = [Banknote]

    CashMachine(banknotes) {
        this.totalSum = checkSum(banknotes)
        this.nominal = banknotes.sort {a,b -> a == b ? 0: a < b ? 1 : -1}
    }

    // Метод для расчета суммы денег в банкомате
    private def checkSum(banknotes) {
        int sum = 0
        for (banknote in banknotes) {
            sum += banknote.title * banknote.count
        }
        return this.totalSum = sum
    }

    // Метод выводит инфо банкомата
    def checkStatusSum() {
        String str = """Инфо:
            |Осталось денежных средств в банкомате: ${this.totalSum}
            |Количество банкнот:\n""".stripMargin()
        for (banknote in this.nominal) {
            str += "Номинал ${banknote.title}: ${banknote.count}\n"
        }
        return str
    }

    // Метод для вывода средств из банкомата
    def withdrawalBanknotes(BigInteger sumMoney) {
        BigInteger money = sumMoney
        Object nominal = this.nominal.clone()
        if (this.totalSum - money < 0) {
            return "Ошибка: недостаточно средств в банкомате!"
        }
        for (int i = 0; i < nominal.size(); i++) {
            while (money - nominal[i].title >= 0 && nominal[i].count) {
                money -= nominal[i].title
                nominal[i]--
            }
        }
        if (money) {
            return "Ошибка: невозвожно выдать часть средств - ${money} из ${sumMoney}!"
        }

        this.totalSum = checkSum(nominal)
        this.nominal = nominal
        return "Выдано: ${sumMoney}"
    }
}
