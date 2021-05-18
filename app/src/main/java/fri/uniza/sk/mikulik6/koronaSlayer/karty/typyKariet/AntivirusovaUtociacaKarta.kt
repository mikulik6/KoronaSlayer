package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.npc.VirusovaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

/**
 * Typ karty slúžiaci na udelenie poškodenia NPC.
 * V prípade, že je NPC inštanciou typu vírusová choroba je útok navýšený.
 *
 * @constructor Zavolá konštruktor predka s parametrami konštruktora tejto triedy.
 *
 * @param nazov
 * @param popis
 * @param utok
 */
class AntivirusovaUtociacaKarta(nazov: String, popis: String, utok: Int) : Karta(nazov, popis, utok) {

    /**
     * Slúži na udelenie poškodenia NPC na základe hodnoty akcie karty.
     * V príapde že je NPC typu vírusová choroba je základný útok navýšený o 1.
     *
     * @param hrac
     */
    override fun pouziKartu(hrac: Postava) {
        val choroba: ChorobaNpc? = hrac.nepriatel

        if (choroba is VirusovaChoroba)
            choroba.prijmiUtok(hodnotaAkcie + 1)
        else
            choroba?.prijmiUtok(hodnotaAkcie)
    }

    /**
     * Slúži na vytvorenie novej inštancie Antivírusovej útočiacej karty s rovnakými hodnotami danej karty.
     *
     * @return kópiu danej karty
     */
    override fun naklonujSa(): AntivirusovaUtociacaKarta {
        return AntivirusovaUtociacaKarta(nazov, popis, hodnotaAkcie)
    }
}