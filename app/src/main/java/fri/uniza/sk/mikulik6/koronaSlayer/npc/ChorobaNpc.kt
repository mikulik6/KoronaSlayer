package fri.uniza.sk.mikulik6.koronaSlayer.npc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.MrtveNpcException

abstract class ChorobaNpc(val meno: String, protected val utok: Int, pZivoty: Int, val muzskyRod: Boolean) {

    val maxZivoty: Int = pZivoty
    protected var _zivoty = MutableLiveData(pZivoty)
    val zivoty: LiveData<Int>
        get() = _zivoty





    fun zautoc(hrac: Postava){
        hrac.prijmiUtok(utok)
    }

    fun prijmiUtok(damage: Int, hrac: Postava) {
        _zivoty.value = _zivoty.value?.minus(damage)
        if (_zivoty.value!! <= 0) {
            _zivoty.value = 0
            throw MrtveNpcException()
        }
    }

    fun resetujSa() {
        _zivoty.value = maxZivoty
    }





    abstract fun vykonajAkciu(hrac: Postava)
}