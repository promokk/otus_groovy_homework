package org.example

enum Currency {
    RUBLE('₽'),
    DOLLAR('$')

    final String value

    Currency(value) {
        this.value = value
    }
}