package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

abstract class Karta(val nazov: String, val popis: String, val cena: Int, val hodnotaAkcie: Int, val akcia: String) {
    abstract fun pouziKartu(hrac: Postava)
}