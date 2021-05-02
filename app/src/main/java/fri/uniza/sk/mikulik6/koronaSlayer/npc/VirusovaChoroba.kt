package fri.uniza.sk.mikulik6.koronaSlayer.npc

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class VirusovaChoroba(meno: String, utok: Int, pZivoty: Int) : ChorobaNpc(meno, utok, pZivoty) {
    override fun vykonajAkciu(hrac: Postava) {
        val hranica: Int = (maxZivoty * 0.3).toInt()

        if (hranica >= _zivoty) {
            this.zautoc(hrac, (utok * 0.2).toInt())
        } else {
            zautoc(hrac)
        }
    }

    private fun zautoc(hrac: Postava, bonusDamage: Int) {
        val novyUtok = utok + bonusDamage
        hrac.prijmiUtok(novyUtok)
    }
}