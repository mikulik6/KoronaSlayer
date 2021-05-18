package fri.uniza.sk.mikulik6.koronaSlayer

import androidx.lifecycle.ViewModel
import fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie.RiadicKariet
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.DalsieLevelException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.MrtveNpcException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.VyhraException

/**
 * ViewModel, ktorý je základom hry a slúži na uchovávanie stavov jednotlivých objektov potrebných počas hry.
 * Uchováva informácie o riadiči kariet, ktorý slúži na riadenie práce s kartami.
 * Taktiež uchováva informácie o mape, postave hráča a o tom či bola alebo nebola vytvorená hra.
 *
 * @constructor Vytvorí inštancie riadiča kariet, mapy, hráča a inicializuje hodnotu bolaVytvorenaHra na false.
 */
class HraViewModel : ViewModel() {

    val riadicKariet = RiadicKariet()

    private var _hrac: Postava = Sestricka()
    val hrac: Postava
        get() = _hrac
    private var _mapa: Mapa = Mapa()
    val mapa: Mapa
        get() = _mapa
    private var _bolaVytvorenaHra: Boolean = false
    val bolaVytvorenaHra: Boolean
        get() = _bolaVytvorenaHra



    /**
     * Slúži na nastavenie hodnoty atribútu bolaVytvorenaHra na false, vytvorenie novej mapy a prečistenie balíčkov kariet v riadiči.
     */
    fun restartujHru() {
        _bolaVytvorenaHra = false
        _mapa = Mapa()
        riadicKariet.vycistenieBalickov()
    }

    /**
     * Slúži na nastavenie postavy hráča na postavu zadanú ako parameter a nastavenie hodnoty atribútu bolaVytvorenaHra na true.
     *
     * @param vybrataPostava
     */
    fun nastavPostavu(vybrataPostava: Postava) {
        _hrac = vybrataPostava
        _bolaVytvorenaHra = true
    }

    /**
     * Slúži na nastavenie hodnoty nepriateľa hráča a vytvorenie ťahacieho balíčka v riadiči kariet.
     */
    fun novyLevel() {
        hrac.chodDoDalsejMiestnosti(mapa)
        riadicKariet.vytvorTahaciBalicek(hrac.getBalicekKariet())
    }

    /**
     * Slúži na vyčistenie balíčkov riadiča kariet, obnovenie životov NPC v danom levely a resetovanie hodnôt hráča.
     */
    fun resetujLevel() {
        riadicKariet.vycistenieBalickov()
        hrac.nepriatel?.resetujSa()
        hrac.odidZLevelu()
    }

    /**
     * Slúži na zavolanie metódy riadiča kariet a hráča noveKolo, ktoré slúžia na vytvorenie hratelných kariet a nastavenie hodnôt many a bloku hráča na začiatku kola.
     */
    fun noveKolo() {
        riadicKariet.noveKolo()
        hrac.noveKolo()
    }

    /**
     * Metóda slúžiaca na zahranie karty na základe čísla karty zadaného ako parameter.
     * Po prijatí výnimky MartveNpc je skontrolvané či je aktuálny level rovný celkovému počtu levelov čo by znamenalo vyhodenie výnimky VyhraException.
     * V prípade, že sa dané hodnoty nerovnajú je zavolaná metóda koniecLevelu pre riadič a informovanie hráča o postupe do ďalšieho levelu.
     * Okrem iného je v tejto časti vyhodená aj výnimka DalsiLevelException.
     *
     * @param cisloKarty
     */
    fun zahranieKarty(cisloKarty: Int) {
        try {
            riadicKariet.zahrajKartu(cisloKarty, hrac)
        } catch (e: MrtveNpcException) {
            if (hrac.aktualnyLevel == mapa.pocetLevelov)
                throw VyhraException()
            else {
                riadicKariet.koniecLevelu()
                hrac.zabilSiNepriatela()
                throw DalsieLevelException()
            }
        }
    }

    /**
     * Slúži na vykonanie akcie nepriateľa voči hráčovi.
     */
    fun nepriatelUtok() {
        hrac.nepriatel?.vykonajAkciu(hrac)
    }

    /**
     * Slúži na pridanie karty do hráčovho balíčku, na základe čísla karty zadaného ako parameter.
     *
     * @param cisloKarty
     */
    fun hracSiVybralKart(cisloKarty: Int) {
        hrac.pridajKartu(riadicKariet.kartaNaVyber(cisloKarty))
        riadicKariet.zahodKartyNaVyber()
    }
}