package fri.uniza.sk.mikulik6.koronaSlayer.postavy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fri.uniza.sk.mikulik6.koronaSlayer.hlavny.Akcia
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.*
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.SmrtHracaException

abstract class Postava(val meno: String, val pasivnaSchopnost: String, pZdravie: Int) {

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





    //Začiatok boja -> slúži na nastavenie nepriateľa (na základe mapy a čísla levelu)
    fun chodDoDalsejMiestnosti(mapa: Mapa) {
        nepriatel = mapa.levely[_aktualnyLevel - 1]
    }

    //Počas boja -> Slúži na prijatie útoku (zníženie životov) od NPC o hodnotu útoku zadanú ako parameter.
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

    //Počas Boja
    fun uzdravSa(pUzdravenie: Int) {
        var uzdravenie: Int = pUzdravenie

        if (maxZdravie < _zdravie.value!! + uzdravenie) {
            uzdravenie = maxZdravie - _zdravie.value!!
        }

        _zdravie.value = _zdravie.value!! + uzdravenie
    }

    //Počas Boja
    fun pridajBlok(blok: Int) {
        _blok.value = _blok.value!! + blok
    }

    //Počas boja
    fun uberManu(pocetPouzitejMany: Int) {
        _mana.value = (_mana.value)?.minus(pocetPouzitejMany)

        if (_mana.value!! == 0) {
            throw KoniecHracovhoTahuException()
        }
    }

    //Počas boja
    open fun noveKolo() {
        _mana.value = 3
        _blok.value = 0
    }


    //Koniec boja
    fun zabilSiNepriatela() {
        _aktualnyLevel++
        nepriatel = null
        _mana.value = 3
        _blok.value = 0
    }

    //Koniec boja
    fun pridajKartu(vybrataKarta: Karta) {
        balicekKariet.add(vybrataKarta)
    }





    //Pri vytvorení postavy
    private fun vytvorZakladnyBalicek() {
        var pocitadlo: Int = 0

        while (pocitadlo < 10) {
            if (pocitadlo < 3) {
                this.balicekKariet.add(AntibakterialnaUtociacaKarta("PENICILIN", "Vhodny proti bakteriam", 1, 5))
            } else if (pocitadlo < 6) {
                this.balicekKariet.add(AntivirusovaUtociacaKarta("IBALGIN", "Vhodny proti virusom", 1, 5));
            } else if (pocitadlo < 8) {
                this.balicekKariet.add(UzdravovaciaKarta("HORUCI CAJ", "Liek ako ziadny iny", 1, 10));
            } else {
                this.balicekKariet.add(BlokovaciaKarta("CIBULA", "Posilnenie na 1 kolo", 1, 4));
            }
            pocitadlo++
        }
    }
}