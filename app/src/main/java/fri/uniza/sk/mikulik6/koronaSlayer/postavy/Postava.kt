package fri.uniza.sk.mikulik6.koronaSlayer.postavy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fri.uniza.sk.mikulik6.koronaSlayer.hlavny.Akcia
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.*
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.NedostatocnyPocetManyException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.SmrtHracaException
import java.io.Serializable

abstract class Postava(val meno: String, pZdravie: Int) : Serializable {

    //NEPOUZITE
    var ultimateTokens:Int = 0
    var akcia: Akcia = Akcia.VYBER_MIESTNOSTI

    val maxZdravie: Int = pZdravie                                                                  //Getter v "BojFragment"
    private var _zdravie        = MutableLiveData(pZdravie)
    val zdravie: LiveData<Int>
        get() = _zdravie
    private var _mana           = MutableLiveData(3)
    val mana: LiveData<Int>
        get() = _mana
    private var _blok           = MutableLiveData(0)
    val blok: LiveData<Int>
        get() = _blok
    //private var _aktualnyLevel   = MutableLiveData(1)
    //val aktualnyLevel: LiveData<Int>
    //    get() = _aktualnyLevel
    private var _aktualnyLevel = 1
    val aktualnyLevel: Int
        get() = _aktualnyLevel

    var nepriatel: ChorobaNpc? = null
    val balicekKariet = mutableListOf<Karta>()

    init {
        vytvorZakladnyBalicek()
    }





    //Slúži na prijatie útoku (zníženie životov) od NPC o hodnotu útoku zadanú ako parameter.
    fun prijmiUtok(pDamage: Int) {
        var damage: Int = pDamage
        damage -= _blok.value!!

        if(damage > 0) {
            //_zdravie.value = _zdravie.value?.minus(damage)
            _zdravie.value = _zdravie.value!! - damage
            if(_zdravie.value!! <= 0) {
                throw SmrtHracaException()
            }
        }

        _blok.value = 0
    }

    fun uzdravSa(pUzdravenie: Int) {
        var uzdravenie: Int = pUzdravenie

        if (maxZdravie < _zdravie.value!! + uzdravenie) {
            uzdravenie = maxZdravie - _zdravie.value!!
        }

        _zdravie.value = _zdravie.value!! + uzdravenie
    }

    fun pridajBlok(blok: Int) {
        _blok.value = _blok.value!! + blok
    }

    fun uberManu(pocetPouzitejMany: Int) {
        if (_mana.value!! < pocetPouzitejMany) {
            throw NedostatocnyPocetManyException()
        }

        _mana.value = (_mana.value)?.minus(pocetPouzitejMany)
    }

    fun chodDoDalsejMiestnosti(mapa: Mapa, cislo: Int) {
        akcia = Akcia.HRANIE_KARIET
        nepriatel = mapa.levely[_aktualnyLevel]
    }

    fun zabilSiNepriatela() {
        _aktualnyLevel++
        nepriatel = null
        akcia = Akcia.VYBERANIE_KARIET
        _mana.value = 3
        _blok.value = 0
        ultimateTokens++
    }

    open fun noveKolo() {
        _mana.value = 3
        _blok.value = 0
    }

    fun pridajKartu(vybrataKarta: Karta) {
        balicekKariet.add(vybrataKarta)
        akcia = Akcia.VYBER_MIESTNOSTI
    }

    abstract fun ultimate()

    private fun vytvorZakladnyBalicek() {
        var pocitadlo: Int = 0

        while (pocitadlo < 10) {
            if (pocitadlo < 3) {
                this.balicekKariet.add(AntibakterialnaUtociacaKarta("Peniclin", "Vhodny proti bakteriam", 1, 5))
            } else if (pocitadlo < 6) {
                this.balicekKariet.add(AntivirusovaUtociacaKarta("Ibalgin", "Vhodny proti virusom", 1, 5));
            } else if (pocitadlo < 8) {
                this.balicekKariet.add(UzdravovaciaKarta("Horuci caj", "Liek ako ziadny iny", 1, 10));
            } else {
                this.balicekKariet.add(BlokovaciaKarta("Cibula", "Posilnenie na 1 kolo", 1, 4));
            }
            pocitadlo++
        }
    }
}