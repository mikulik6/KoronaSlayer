package fri.uniza.sk.mikulik6.koronaSlayer

import androidx.lifecycle.ViewModel
import fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie.RiadicKariet
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.DalsieLevelException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.MrtveNpcException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.VyhraException

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



    //vytvorenie novej inštancie mapy, nastavenie parametra bolaVytvorenaHra na false
    //Po rozhodnutí sa vytvoriť novú hru pokiaľ už jedna existuje
    //UvodnaStranaFragment
    //PrehraFragment
    //VyhraFragment
    fun restartujHru() {
        _bolaVytvorenaHra = false
        _mapa = Mapa()
        riadicKariet.vycistenieBalickov()
    }

    //nastavenie postavy na základe výberu tlačidla na fragmente "NovaHraFragment"
    //NovaHraFragment
    fun nastavPostavu(vybrataPostava: Postava) {
        _hrac = vybrataPostava
        _bolaVytvorenaHra = true
    }

    //nastavit nepriatela pre hraca, vytvorit tahaci balicek, akcie ako pri novom kole
    //MapaHryFragment
    fun novyLevel() {
        hrac.chodDoDalsejMiestnosti(mapa)
        riadicKariet.vytvorTahaciBalicek(hrac.getBalicekKariet())
    }

    //Obnovenie zivotov choroby, upratanie kariet v riadici ako pri konci levelu, hrac nastavenie bloku/many
    //BojFragment
    fun resetujLevel() {
        riadicKariet.vycistenieBalickov()
        hrac.nepriatel?.resetujSa()
        hrac.odidZLevelu()
    }

    //zavolanie metod noveKolo pre hráča a riadič kariet
    //BojFragment
    fun noveKolo() {
        riadicKariet.noveKolo()
        hrac.noveKolo()
    }

    //BojFragment
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

    //BojFragment
    fun nepriatelUtok() {
        hrac.nepriatel?.vykonajAkciu(hrac)
    }

    //VyberKartyFragment
    fun hracSiVybralKart(cisloKarty: Int) {
        hrac.pridajKartu(riadicKariet.kartaNaVyber(cisloKarty))
        riadicKariet.zahodKartyNaVyber()
    }
}