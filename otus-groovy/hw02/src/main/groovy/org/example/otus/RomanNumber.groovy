package org.example.otus

class RomanNumber implements Comparable<RomanNumber> {
    int number
    def mapRomanNumber = [
            "I",
            "II",
            "III",
            "IV",
            "V",
            "VI",
            "VII",
            "VIII",
            "IX"
    ]

    // Конструктор класса
    RomanNumber(String romanNumber) {
        this.number = mapRomanNumber.findIndexOf { it == romanNumber }
    }

    // Метод next отвечает за инкремент(++)
    def next() {
        this.number++
        // Возвращаем объект RomanNumber
        this
    }

    // Переопределяем вывод объекта RomanNumber
    @Override
    String toString() {
        mapRomanNumber[this.number]
    }

    // Переопределяем сравнение объекта RomanNumber (метод equals)
    @Override
    int compareTo(RomanNumber o) {
        return this.number <=> o?.number
    }
}
