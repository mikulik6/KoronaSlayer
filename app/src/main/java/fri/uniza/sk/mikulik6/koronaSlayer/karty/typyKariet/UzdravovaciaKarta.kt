package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class UzdravovaciaKarta(nazov: String, popis: String, uzdravenie: Int) : Karta(nazov, popis, uzdravenie) {

    override fun pouziKartu(hrac: Postava) {
        hrac.uzdravSa(super.hodnotaAkcie)
    }

    override fun naklonujSa(): UzdravovaciaKarta {
        return UzdravovaciaKarta(nazov, popis, hodnotaAkcie)
    }
}