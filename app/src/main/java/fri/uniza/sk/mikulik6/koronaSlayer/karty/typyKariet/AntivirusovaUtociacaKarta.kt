package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.npc.VirusovaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class AntivirusovaUtociacaKarta(nazov: String, popis: String, utok: Int) : Karta(nazov, popis, utok) {

    override fun pouziKartu(hrac: Postava) {
        val choroba: ChorobaNpc? = hrac.nepriatel

        if (choroba is VirusovaChoroba)
            choroba.prijmiUtok(hodnotaAkcie + 1)
        else
            choroba?.prijmiUtok(hodnotaAkcie)
    }

    override fun naklonujSa(): AntivirusovaUtociacaKarta {
        return AntivirusovaUtociacaKarta(nazov, popis, hodnotaAkcie)
    }
}