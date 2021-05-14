package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class BlokovaciaKarta(nazov: String, popis: String, blok: Int) : Karta(nazov, popis, blok) {

    override fun pouziKartu(hrac: Postava) {
        hrac.pridajBlok(hodnotaAkcie)
    }

    override fun naklonujSa(): BlokovaciaKarta {
        return BlokovaciaKarta(nazov, popis, hodnotaAkcie)
    }
}