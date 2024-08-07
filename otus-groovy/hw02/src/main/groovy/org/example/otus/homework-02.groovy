package org.example.otus

static void main(String[] args) {
    def number = new RomanNumber("II");
    println "Number: $number"
    number++
    assert number == new RomanNumber("III")
    println "After incrementing: $number"
    number++
    number++
    assert number == new RomanNumber("V")
    println "After incrementing: $number"

}
