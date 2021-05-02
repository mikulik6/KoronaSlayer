package fri.uniza.sk.mikulik6.koronaSlayer


import android.util.Log
import androidx.lifecycle.ViewModel
import fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie.RiadicKariet
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Lekar
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka

class HraViewModel : ViewModel() {

    val mapa: Mapa = Mapa()
    val riadicKariet = RiadicKariet()
    private var _hrac: Postava = Sestricka()
    val hrac: Postava
        get() = _hrac

    //nastavenie postavy na základe výberu tlačidla na fragmente "NovaHraFragment"
    fun nastavPostavu(vybrataPostava: Postava) {
        _hrac = vybrataPostava
    }
}