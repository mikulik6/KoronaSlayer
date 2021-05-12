package fri.uniza.sk.mikulik6.koronaSlayer


import android.util.Log
import androidx.lifecycle.ViewModel
import fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie.RiadicKariet
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Lekar
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka

class HraViewModel : ViewModel() {

    //Postava
    private var _hrac: Postava = Sestricka()
    val hrac: Postava
        get() = _hrac
    //Mapa
    private var _mapa: Mapa = Mapa()
    val mapa: Mapa
        get() = _mapa
    //BolaVytvorenaHra
    private var _bolaVytvorenaHra: Boolean = false
    val bolaVytvorenaHra: Boolean
        get() = _bolaVytvorenaHra

    val riadicKariet = RiadicKariet()





    //nastavenie postavy na základe výberu tlačidla na fragmente "NovaHraFragment"
    fun nastavPostavu(vybrataPostava: Postava) {
        _hrac = vybrataPostava
        _bolaVytvorenaHra = true
    }

    //vytvorenie novej inštancie mapy, nastavenie parametra bolaVytvorenaHra na false
    fun restartujHru() {
        _bolaVytvorenaHra = false
        _mapa = Mapa()
    }

    //nastavit nepriatela pre hraca, vytvorit tahaci balicek, akcie ako pri novom kole
    fun novyLevel() {
        hrac.chodDoDalsejMiestnosti(mapa)
        riadicKariet.vytvorTahaciBalicek(hrac.balicekKariet)
    }

    //zavolanie metod noveKolo pre hráča a riadič kariet
    fun noveKolo() {
        riadicKariet.noveKolo()
        hrac.noveKolo()
    }

    fun resetujLevel() {
        riadicKariet.koniecLevelu()
        hrac.nepriatel?.resetujSa()
        hrac.odidZLevelu()
    }

    fun hracSiVybralKart(cisloKarty: Int) {
        hrac.pridajKartu(riadicKariet.kartyNaVyber[cisloKarty])
        riadicKariet.zahodKartyNaVyber()
    }
}