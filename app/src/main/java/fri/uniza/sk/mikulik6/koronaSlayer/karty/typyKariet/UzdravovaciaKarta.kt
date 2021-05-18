package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

/**
 * Typ karty slúžiaci pre navýšenie životov hráča.
 *
 * @constructor Zavolá konštruktor predka s parametrami konštruktora tejto triedy.
 *
 * @param nazov
 * @param popis
 * @param uzdravenie
 */
class UzdravovaciaKarta(nazov: String, popis: String, uzdravenie: Int) : Karta(nazov, popis, uzdravenie) {

    /**
     * Slúži pre navýšenie životov hráča o hodnotu akcie karty.
     *
     * @param hrac
     */
    override fun pouziKartu(hrac: Postava) {
        hrac.uzdravSa(super.hodnotaAkcie)
    }

    /**
     * Slúži na vytvorenie novej inštancie Uzdravovacej karty s rovnakými hodnotami danej karty.
     *
     * @return kópiu danej karty
     */
    override fun naklonujSa(): UzdravovaciaKarta {
        return UzdravovaciaKarta(nazov, popis, hodnotaAkcie)
    }
}