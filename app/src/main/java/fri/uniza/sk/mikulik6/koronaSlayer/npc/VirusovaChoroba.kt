package fri.uniza.sk.mikulik6.koronaSlayer.npc

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

/**
 * Typ NPC zvyšujúci útok voči hráčovi v prípade, že jeho životy klesnú pod určitú hranicu.
 * Správanie je definované v prepísanej abstraktnej metóde ChorobyNPC.
 *
 * @constructor Zavolá konštruktor predka s parametrami konštruktora tejto triedy.
 *
 * @param meno
 * @param utok
 * @param pZivoty
 * @param pMuzskyRod
 */
class VirusovaChoroba(meno: String, utok: Int, pZivoty: Int, pMuzskyRod: Boolean) : ChorobaNpc(meno, utok, pZivoty, pMuzskyRod) {

    /**
     * Slúži na zaútočenie na hráča.
     * V prípade že životy NPC klesnú pod 30% je hodnota útoku navýšená o 20% základnej hodnoty útoku.
     *
     * @param hrac
     */
    override fun vykonajAkciu(hrac: Postava) {
        val hranica: Int = (maxZivoty * 0.3).toInt()

        if (hranica >= _zivoty.value!!)
            this.zautoc(hrac, (utok * 0.2).toInt())
        else
            zautoc(hrac)
    }

    /**
     * Slúži na vykonanie útoku na hráča zadaného ako parameter s hodnotou základného útoku + bonusového útoku.
     *
     * @param hrac
     * @param bonusDamage
     */
    private fun zautoc(hrac: Postava, bonusDamage: Int) {
        val novyUtok = utok + bonusDamage
        hrac.prijmiUtok(novyUtok)
    }
}