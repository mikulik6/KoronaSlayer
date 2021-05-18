package fri.uniza.sk.mikulik6.koronaSlayer.postavy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.*
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.SmrtHracaException

/**
 * Trieda ukladajúca základné informácie o postave hráča a definujúca základné správanie každého typu postavy.
 *
 * @property meno
 * @property pasivnaSchopnost
 * @constructor Nastaví hodnoty jednotlivých atribútov triedy a zavolá metódu slúžiacu na vytvorenie základného balíčka.
 *
 * @param pZdravie
 */
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


    /**
     * Slúži na vrátenie kópie hráčovho balíčku kariet.
     *
     * @return
     */
    fun getBalicekKariet(): List<Karta> {
        val podobnyBalicekKariet = mutableListOf<Karta>()
        podobnyBalicekKariet.addAll(balicekKariet)
        return podobnyBalicekKariet
    }

    /**
     * Slúži na nastavenie hodnoty nepriateľa na zákalde hodnoty aktálneho levelu.
     *
     * @param mapa
     */
    fun chodDoDalsejMiestnosti(mapa: Mapa) {
        _nepriatel = mapa.choroba(_aktualnyLevel - 1)
    }

    /**
     * Slúži na zníženie životov o hodnotu zadanú ako parameter avšak zníženú o hodnotu obrany/bloku postavy.
     * V prípade že je po odčítaní bloku útok väčší ako 0 sú postave znížené životy o zostávajúcu hodnotu.
     * Ak následne životy postavy klesnú pod 0 je vyhodená výnimka signalizujúca smrť postavy.
     * Na konci prijatia útoku je blok nastavený na hodnotu 0 aj v prípade, že nebola hodnoty bloku celá využitá.
     *
     * @param pDamage
     */
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

    /**
     * Slúži na navýšenie životo postavy o hodnotu zadanú ako parameter nanajvýš však na hodotu maximálnych životov.
     *
     * @param uzdravenie
     */
    fun uzdravSa(uzdravenie: Int) {
        _zdravie.value =  _zdravie.value!! + uzdravenie

        if (maxZdravie < _zdravie.value!!)
            _zdravie.value = maxZdravie
    }

    /**
     * Slúži na nyvýšenie hodnoty obrany/bloku postavy o hodnotu zadanú ako parameter
     *
     * @param blok
     */
    fun pridajBlok(blok: Int) {
        _blok.value = _blok.value!! + blok
    }

    /**
     * Slúži na znížeie hodnoty aktuálnej many postavy o 1.
     * V prípade, že hodnota many je po znížený rovná 0 je vyhodená výnimka signalizujúca koniec kola hráča.
     */
    fun uberManu() {
        _mana.value = (_mana.value)?.minus(1)

        if (_mana.value!! == 0)
            throw KoniecHracovhoTahuException()
    }

    /**
     * Slúži na nastavenie hodnoty many a bolku postavy na začiatku kola.
     * Hodnota many je nastavená na maximálnu hodnotu many.
     * Hodnota bloku je nastavená na 0.
     */
    open fun noveKolo() {
        _mana.value = maxMana
        _blok.value = 0
    }

    /**
     * Slúži na zvýšenie hodnoty aktuálneho levelu a zavolanie metódy nové kolo, ktorá slúži na nastavenie hodnôt many a bolku.
     * Okrem iného slúži taktiež aj pre vynulovanie hodnoty nepriateľa.
     */
    fun zabilSiNepriatela() {
        _aktualnyLevel++
        this.noveKolo()
        _nepriatel = null
    }

    /**
     * Slúži na zavolanie metódy nové kolo, ktorá slúži na nastavenie hodnoty many a bloku a na vynullovanie hodnoty nepriateľa.
     */
    fun odidZLevelu() {
        this.noveKolo()
        _nepriatel = null
    }

    /**
     * Slúži na pridanie karty zadanej ako pramater do balíčku kariet.
     *
     * @param vybrataKarta
     */
    fun pridajKartu(vybrataKarta: Karta) {
        balicekKariet.add(vybrataKarta)
    }


    /**
     * Slúži na naplnenie balíčku kariet, základnými kartami.
     */
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