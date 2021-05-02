package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class UzdravovaciaKarta(nazov: String, popis: String, cena: Int, uzdravenie: Int)
    : Karta(nazov, popis, cena, uzdravenie, "heal") {

    override fun pouziKartu(hrac: Postava) {
        hrac.uzdravSa(super.hodnotaAkcie)
    }
}