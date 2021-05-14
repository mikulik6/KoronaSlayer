package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class AntibakterialnaUtociacaKarta(nazov: String, popis: String, utok: Int) : Karta(nazov, popis, utok){

    override fun pouziKartu(hrac: Postava) {
        val choroba: ChorobaNpc? = hrac.nepriatel

        if (choroba is BakterialnaChoroba)
            choroba.prijmiUtok(hodnotaAkcie + 1)
        else
            choroba?.prijmiUtok(hodnotaAkcie)
    }

    override fun naklonujSa(): AntibakterialnaUtociacaKarta {
        return AntibakterialnaUtociacaKarta(nazov, popis, hodnotaAkcie)
    }
}