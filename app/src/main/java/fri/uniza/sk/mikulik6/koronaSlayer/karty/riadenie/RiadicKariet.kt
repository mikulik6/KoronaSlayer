package fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie

import androidx.lifecycle.LiveData
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.Karta
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import kotlin.random.Random

class RiadicKariet {

    private var tahaciBalicek = mutableListOf<Karta>()
    private var zahadzovaciBalicek = mutableListOf<Karta>()
    private var _hratelneKarty = mutableListOf<Karta>()
    private var _hratelneKartyLive = mutableListOf<LiveData<Karta>>()
        val hratelneKarty get() = _hratelneKartyLive
    //val hratelneKarty: mutableListOf<LiveData<Karta>()







    //Začiatok levelu
    fun vytvorTahaciBalicek(hracovBalicekKariet: MutableList<Karta>) {
        //tahaciBalicek = hracovBalicekKariet
        tahaciBalicek.addAll(hracovBalicekKariet)
    }

    //Počas levelu
    fun noveKolo() {
        zahodHratelneKarty()
        vytiahniHratelneKarty()
    }

    //Počas levelu
    fun zahrajKartu(cisloKarty: Int, hrac: Postava) {
        val vybrataKarta = _hratelneKarty.get(cisloKarty)

        zahadzovaciBalicek.add(vybrataKarta)
        vybrataKarta.pouziKartu(hrac)
        hrac.uberManu(vybrataKarta.cena)

        if (_hratelneKarty.isEmpty()) {
            throw KoniecHracovhoTahuException()
        }
    }

    //Koniec levelu
    fun koniecLevelu() {
        tahaciBalicek.clear()
        _hratelneKarty.clear()
        zahadzovaciBalicek.clear()
        //vytvorenie kariet pre vyber
    }





    private fun zahodHratelneKarty() {
        zahadzovaciBalicek.addAll(_hratelneKarty)
        _hratelneKarty.clear()
    }

    private fun vytiahniHratelneKarty() {
        if (tahaciBalicek.size < 5) {
            tahaciBalicek.addAll(zahadzovaciBalicek)
            zahadzovaciBalicek.clear()
        }

        val maxIndexKarty = tahaciBalicek.size - 1
        var i = 0
        while (i < 5){
            if (maxIndexKarty - i > 0) {
                _hratelneKarty.add(tahaciBalicek.removeAt(Random.nextInt(maxIndexKarty - i)))
            } else {
                _hratelneKarty.add(tahaciBalicek.removeAt(0))
            }
            i++
        }

        _hratelneKartyLive.addAll(hratelneKarty)
    }
}