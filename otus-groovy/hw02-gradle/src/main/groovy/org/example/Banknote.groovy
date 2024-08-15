package org.example

class Banknote implements Comparable<Banknote>  {
    BigInteger title
    BigInteger count

    Banknote(title, count) {
        this.title = title
        this.count = count
    }

    def previous() {
        new Banknote(this.title, this.count - 1)
    }

    @Override
    int compareTo(Banknote o) {
        return this.title <=> o?.title
    }
}
