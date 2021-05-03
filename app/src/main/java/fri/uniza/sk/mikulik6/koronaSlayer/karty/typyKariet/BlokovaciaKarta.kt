package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class BlokovaciaKarta(nazov: String, popis: String, cena: Int, blok: Int)
    : Karta(nazov, popis, cena, blok, "blok") {

    override fun pouziKartu(hrac: Postava) {
        hrac.pridajBlok(hodnotaAkcie)
    }
}