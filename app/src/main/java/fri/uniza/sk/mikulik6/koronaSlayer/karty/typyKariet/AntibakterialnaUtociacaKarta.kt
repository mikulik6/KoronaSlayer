package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

/**
 * Typ karty slúžiaci na udelenie poškodenia NPC.
 * V prípade, že je NPC inštanciou typu bakteriálna choroba je útok navýšený.
 *
 * @constructor Zavolá konštruktor predka s parametrami konštruktora tejto triedy.
 *
 * @param nazov
 * @param popis
 * @param utok
 */
class AntibakterialnaUtociacaKarta(nazov: String, popis: String, utok: Int) : Karta(nazov, popis, utok){

    /**
     * Slúži na udelenie poškodenia NPC na základe hodnoty akcie karty.
     * V príapde že je NPC typu bakteriálna choroba je základný útok navýšený o 1.
     *
     * @param hrac
     */
    override fun pouziKartu(hrac: Postava) {
        val choroba: ChorobaNpc? = hrac.nepriatel

        if (choroba is BakterialnaChoroba)
            choroba.prijmiUtok(hodnotaAkcie + 1)
        else
            choroba?.prijmiUtok(hodnotaAkcie)
    }

    /**
     * Slúži na vytvorenie novej inštancie Antibakteriálnej útočiacej karty s rovnakými hodnotami danej karty.
     *
     * @return kópiu danej karty
     */
    override fun naklonujSa(): AntibakterialnaUtociacaKarta {
        return AntibakterialnaUtociacaKarta(nazov, popis, hodnotaAkcie)
    }
}