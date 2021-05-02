package fri.uniza.sk.mikulik6.koronaSlayer.npc

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.MrtveNpcException

abstract class ChorobaNpc(val meno: String, val utok: Int, pZivoty: Int) {

    var _zivoty: Int = pZivoty
    //val zivoty: Int
    //    get() = _zivoty
    val maxZivoty: Int = pZivoty

    fun zautoc(hrac: Postava){
        hrac.prijmiUtok(utok)
    }

    fun prijmiUtok(damage: Int, hrac: Postava) {
        _zivoty -= damage
        if (_zivoty < 0) {
            throw MrtveNpcException()
        }
    }

    abstract fun vykonajAkciu(hrac: Postava)
}