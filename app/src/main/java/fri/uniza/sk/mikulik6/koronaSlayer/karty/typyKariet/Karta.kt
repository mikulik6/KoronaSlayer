package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

abstract class Karta(val nazov: String, val popis: String, val hodnotaAkcie: Int) {
    abstract fun pouziKartu(hrac: Postava)
    abstract fun naklonujSa(): Karta
}