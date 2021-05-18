package fri.uniza.sk.mikulik6.koronaSlayer.npc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.MrtveNpcException

/**
 * Trieda ukaldajúca zákaldné informácie o NPC a definujúca zákaldné správanie NPC.
 *
 * @property meno
 * @property utok
 * @property muzskyRod
 * @constructor Nastaví hodnoty jednotlivých atribútov meno, útok, mužský rod, životy a max. životy.
 *
 * @param pZivoty
 */
abstract class ChorobaNpc(val meno: String, protected val utok: Int, pZivoty: Int, val muzskyRod: Boolean) {

    val maxZivoty: Int = pZivoty
    protected var _zivoty = MutableLiveData(pZivoty)
    val zivoty: LiveData<Int>
        get() = _zivoty


    /**
     * Slúži na udelenie poškodenia hráčovi zadanému ako parameter o hodnotu útoku.
     *
     * @param hrac
     */
    fun zautoc(hrac: Postava){
        hrac.prijmiUtok(utok)
    }

    /**
     * Slúži na zníženie životov o hodnotu zdanú ako parameter.
     * V prípade, že je po znížení životov počet životov menší ako 0 je vyhodená výnimko o smrti NPC.
     *
     * @param damage
     */
    fun prijmiUtok(damage: Int) {
        _zivoty.value = _zivoty.value?.minus(damage)
        if (_zivoty.value!! <= 0) {
            _zivoty.value = 0
            throw MrtveNpcException()
        }
    }

    /**
     * Slúži na obnovenie životov NPC na max. počet životov.
     */
    fun resetujSa() {
        _zivoty.value = maxZivoty
    }

    /**
     * Abstraktná metóda slúžiaca na vykonanie akcie určitého typu NPC.
     *
     * @param hrac
     */
    abstract fun vykonajAkciu(hrac: Postava)
}