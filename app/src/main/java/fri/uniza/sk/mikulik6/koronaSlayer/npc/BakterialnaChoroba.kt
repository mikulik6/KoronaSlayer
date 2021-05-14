package fri.uniza.sk.mikulik6.koronaSlayer.npc

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class BakterialnaChoroba(meno: String, utok: Int, pZivoty: Int, pMuzskyRod: Boolean) : ChorobaNpc(meno, utok, pZivoty, pMuzskyRod) {

    override fun vykonajAkciu(hrac: Postava) {
        val hranica: Int = (maxZivoty * 0.3).toInt()

        if (hranica >= _zivoty.value!!)
            this.uzdravSa((maxZivoty * 0.2).toInt())
        else
            zautoc(hrac)
    }

    private fun uzdravSa(uzdravenie: Int) {
        val konecneZivoty = _zivoty.value!! + uzdravenie

        if (konecneZivoty > maxZivoty)
            _zivoty.value = maxZivoty
        else
            _zivoty.value = konecneZivoty
    }
}