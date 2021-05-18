package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

/**
 * Typ karty slúžiaci pre navýšenie obrany/bloku hráča.
 *
 * @constructor Zavolá konštruktor predka s parametrami konštruktora tejto triedy.
 *
 * @param nazov
 * @param popis
 * @param blok
 */
class BlokovaciaKarta(nazov: String, popis: String, blok: Int) : Karta(nazov, popis, blok) {

    /**
     * Slúži pre navýšenie obrany/bloku hráča o hodnotu akcie karty.
     *
     * @param hrac
     */
    override fun pouziKartu(hrac: Postava) {
        hrac.pridajBlok(hodnotaAkcie)
    }

    /**
     * Slúži na vytvorenie novej inštancie Blokovacej karty s rovnakými hodnotami danej karty.
     *
     * @return kópiu danej karty
     */
    override fun naklonujSa(): BlokovaciaKarta {
        return BlokovaciaKarta(nazov, popis, hodnotaAkcie)
    }
}