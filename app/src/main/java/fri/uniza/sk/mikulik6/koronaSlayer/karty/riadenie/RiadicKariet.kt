package fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie

import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.Karta
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import kotlin.random.Random

class RiadicKariet {

    private var tahaciBalicek = mutableListOf<Karta>()
    val hratelneKarty = mutableListOf<Karta>()
    private val zahadzovaciBalicek = mutableListOf<Karta>()





    fun vytvorTahaciBalicek(hracovBalicekKariet: MutableList<Karta>) {
        tahaciBalicek = hracovBalicekKariet
    }

    fun koniecLevelu() {
        tahaciBalicek.clear()
        hratelneKarty.clear()
        zahadzovaciBalicek.clear()
        //vytvorenie kariet pre vyber
    }

    fun noveKolo() {
        zahodHratelneKarty()
        vytiahniHratelneKarty()
    }

    fun zahrajKartu(cisloKarty: Int, hrac: Postava) {
        val vybrataKarta = hratelneKarty.get(cisloKarty)

        //hrac.uberManu(vybrataKarta.cena)

        //hratelneKarty.remove(vybrataKarta)
        zahadzovaciBalicek.add(vybrataKarta)
        vybrataKarta.pouziKartu(hrac)
        hrac.uberManu(vybrataKarta.cena)

        //vynimky pre ukazanie konca hracovho kola ak je mana = 0 alebo niesu hratelne karty
        // mana == 0 kotrolovana v hracovi
        if (hratelneKarty.isEmpty()) {
            throw KoniecHracovhoTahuException()
        }
    }





    private fun zahodHratelneKarty() {
        zahadzovaciBalicek.addAll(hratelneKarty)
        hratelneKarty.clear()
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
                hratelneKarty.add(tahaciBalicek.removeAt(Random.nextInt(maxIndexKarty - i)))
            } else {
                hratelneKarty.add(tahaciBalicek.removeAt(0))
            }
            i++
        }
    }
}