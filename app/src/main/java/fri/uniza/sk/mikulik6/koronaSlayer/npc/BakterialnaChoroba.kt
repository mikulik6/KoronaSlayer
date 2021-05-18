package fri.uniza.sk.mikulik6.koronaSlayer.npc

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

/**
 * Typ NPC zvyšujúci svoje životy v prípade, že jeho životy klesnú pod určitú hranicu.
 * Správanie je definované v prepísanej abstraktnej metóde ChorobyNPC.
 *
 * @constructor Zavolá konštruktor predka s parametrami konštruktora tejto triedy.
 *
 * @param meno
 * @param utok
 * @param pZivoty
 * @param pMuzskyRod
 */
class BakterialnaChoroba(meno: String, utok: Int, pZivoty: Int, pMuzskyRod: Boolean) : ChorobaNpc(meno, utok, pZivoty, pMuzskyRod) {

    /**
     * Slúži na zaútočenie na hráča alebo na zvýšenie životov v prípade, že jeho žvoty klesnú pod 30%.
     *
     * @param hrac
     */
    override fun vykonajAkciu(hrac: Postava) {
        val hranica: Int = (maxZivoty * 0.3).toInt()

        if (hranica >= _zivoty.value!!)
            this.uzdravSa((maxZivoty * 0.2).toInt())
        else
            zautoc(hrac)
    }

    /**
     * Slúži na zvýšenie svojich životov o hodnotu zadanú ako parameter avšak nanajvýš na hodotu max. životov.
     *
     * @param uzdravenie
     */
    private fun uzdravSa(uzdravenie: Int) {
        val konecneZivoty = _zivoty.value!! + uzdravenie

        if (konecneZivoty > maxZivoty)
            _zivoty.value = maxZivoty
        else
            _zivoty.value = konecneZivoty
    }
}