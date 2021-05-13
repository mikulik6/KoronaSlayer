package fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie

import androidx.lifecycle.LiveData
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.*
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import kotlin.random.Random

class RiadicKariet {

    private val vzoryKariet = arrayOf(
            AntibakterialnaUtociacaKarta("PENICILIN", "5 utok", 1, 5),
            AntivirusovaUtociacaKarta("IBALGIN", "5 utok", 1, 5),
            UzdravovaciaKarta("HORUCI CAJ", "+10 zivotov", 1, 10),
            UzdravovaciaKarta("NOSNE KVAPKY", "+8 zivotov", 1, 8),
            UzdravovaciaKarta("JABLKO", "+15 zivotov", 1, 15),
            UzdravovaciaKarta("CITRON", "+20 zivotov", 1, 20),
            BlokovaciaKarta("CIBULA", "+4 obrana", 1, 4),
            BlokovaciaKarta("CESNAK", "+5 obrana", 1, 5))

    private var tahaciBalicek = mutableListOf<Karta>()
    private var zahadzovaciBalicek = mutableListOf<Karta>()
    private var _hratelneKarty = mutableListOf<Karta>()
    val hratelneKarty
        get() = _hratelneKarty
    private var _kartyNaVyber = mutableListOf<Karta>()
    val kartyNaVyber
        get() = _kartyNaVyber

    val pouziteKarty = mutableListOf<Int>()



    //Začiatok levelu
    fun vytvorTahaciBalicek(hracovBalicekKariet: MutableList<Karta>) {
        //tahaciBalicek = hracovBalicekKariet
        tahaciBalicek.addAll(hracovBalicekKariet)
    }

    //Počas levelu
    fun noveKolo() {
        zahodHratelneKarty()
        vytiahniHratelneKarty()
        pouziteKarty.clear()
    }

    //Počas levelu
    fun zahrajKartu(cisloKarty: Int, hrac: Postava) {
        val vybrataKarta = _hratelneKarty.get(cisloKarty)

        //PRIDANE
        pouziteKarty.add(cisloKarty)

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
        vytvorKartyNaVyber()
    }

    fun zahodKartyNaVyber() {
        _kartyNaVyber.clear()
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
    }

    private fun vytvorKartyNaVyber() {
        for (i in 1..3) {
            _kartyNaVyber.add(vzoryKariet.get(Random.nextInt(vzoryKariet.size - 1)).naklonujSa())
        }
    }
}