package fri.uniza.sk.mikulik6.koronaSlayer.npc

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class BakterialnaChoroba(meno: String, utok: Int, pZivoty: Int) : ChorobaNpc(meno, utok, pZivoty) {

    override fun vykonajAkciu(hrac: Postava) {
        val hranica: Int = (maxZivoty * 0.3).toInt()

        if (hranica >= _zivoty) {
            this.uzdravSa((maxZivoty * 0.2).toInt())
        } else {
            zautoc(hrac)
        }
    }

    private fun uzdravSa(uzdravenie: Int) {
        var konecneZivoty = _zivoty + uzdravenie

        if (konecneZivoty > maxZivoty){
            _zivoty = maxZivoty
        } else {
            _zivoty = konecneZivoty
        }
    }
}