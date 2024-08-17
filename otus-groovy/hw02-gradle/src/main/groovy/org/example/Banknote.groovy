package org.example

class Banknote implements Comparable<Banknote>  {
    BigInteger value
    BigInteger count
    Currency currency

    Banknote(value, count, currency) {
        this.value  = value
        this.count = count
        this.currency = currency
    }

    def next() {
        new Banknote(this.value , this.count + 1, this.currency)
    }

    def previous() {
        new Banknote(this.value , this.count - 1, this.currency)
    }

    @Override
    int compareTo(Banknote o) {
        return this.value  <=> o?.value
    }
}
