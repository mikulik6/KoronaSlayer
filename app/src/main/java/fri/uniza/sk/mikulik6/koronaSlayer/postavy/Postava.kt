package fri.uniza.sk.mikulik6.koronaSlayer.postavy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.*
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.SmrtHracaException

abstract class Postava(val meno: String, val pasivnaSchopnost: String, pZdravie: Int) {

    private val balicekKariet   = mutableListOf<Karta>()
    val maxZdravie: Int         = pZdravie
    val maxMana: Int            = 3

    private var _zdravie                = MutableLiveData(maxZdravie)
    val zdravie: LiveData<Int>
        get() = _zdravie
    private var _mana                   = MutableLiveData(maxMana)
    val mana: LiveData<Int>
        get() = _mana
    private var _blok                   = MutableLiveData(0)
    val blok: LiveData<Int>
        get() = _blok
    private var _aktualnyLevel          = 1
    val aktualnyLevel: Int
        get() = _aktualnyLevel
    private var _nepriatel: ChorobaNpc? = null
    val nepriatel: ChorobaNpc?
        get() = _nepriatel

    init {
        vytvorZakladnyBalicek()
    }





    fun getBalicekKariet(): List<Karta> {
        val podobnyBalicekKariet = mutableListOf<Karta>()
        podobnyBalicekKariet.addAll(balicekKariet)
        return podobnyBalicekKariet
    }

    //Začiatok boja -> slúži na nastavenie nepriateľa (na základe mapy a čísla levelu)
    fun chodDoDalsejMiestnosti(mapa: Mapa) {
        _nepriatel = mapa.choroba(_aktualnyLevel - 1)
    }

    //Počas boja -> Slúži na prijatie útoku (zníženie životov) od NPC o hodnotu útoku zadanú ako parameter.
    fun prijmiUtok(pDamage: Int) {
        var damage: Int = pDamage
        damage -= _blok.value!!

        if(damage > 0) {
            _zdravie.value = _zdravie.value!! - damage

            if(_zdravie.value!! <= 0) {
                _zdravie.value = 0
                throw SmrtHracaException()
            }
        }

        _blok.value = 0
    }

    //Počas Boja
    fun uzdravSa(uzdravenie: Int) {
        _zdravie.value =  _zdravie.value!! + uzdravenie

        if (maxZdravie < _zdravie.value!!)
            _zdravie.value = maxZdravie
    }

    //Počas Boja
    fun pridajBlok(blok: Int) {
        _blok.value = _blok.value!! + blok
    }

    //Počas boja
    fun uberManu() {
        _mana.value = (_mana.value)?.minus(1)

        if (_mana.value!! == 0)
            throw KoniecHracovhoTahuException()
    }

    //Počas boja
    open fun noveKolo() {
        _mana.value = maxMana
        _blok.value = 0
    }

    //Koniec boja
    fun zabilSiNepriatela() {
        _aktualnyLevel++
        this.noveKolo()
        _nepriatel = null
    }

    //Koniec boja
    fun odidZLevelu() {
        this.noveKolo()
        _nepriatel = null
    }

    //Koniec boja
    fun pridajKartu(vybrataKarta: Karta) {
        balicekKariet.add(vybrataKarta)
    }





    //Pri vytvorení postavy
    private fun vytvorZakladnyBalicek() {
        for (i in 0..9) {
            when {
                i < 3 -> balicekKariet.add(AntibakterialnaUtociacaKarta("PENICILIN", "5 utok", 5))
                i < 6 -> balicekKariet.add(AntivirusovaUtociacaKarta("IBALGIN", "5 utok", 5))
                i < 8 -> balicekKariet.add(UzdravovaciaKarta("HORUCI CAJ", "+10 zivotov", 10))
                else  -> balicekKariet.add(BlokovaciaKarta("CIBULA", "+4 obrana", 4))
            }
        }
    }
}