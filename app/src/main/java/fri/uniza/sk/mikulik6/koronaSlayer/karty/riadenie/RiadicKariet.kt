package fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie

import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.*
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import kotlin.random.Random

class RiadicKariet {

    private val vzoryKariet = arrayOf(
            AntibakterialnaUtociacaKarta("PENICILIN", "5 utok", 5),
            AntivirusovaUtociacaKarta("IBALGIN", "5 utok", 5),
            UzdravovaciaKarta("HORUCI CAJ", "+10 zivotov", 10),
            UzdravovaciaKarta("NOSNE KVAPKY", "+8 zivotov", 8),
            UzdravovaciaKarta("JABLKO", "+15 zivotov", 15),
            UzdravovaciaKarta("CITRON", "+20 zivotov", 20),
            BlokovaciaKarta("CIBULA", "+4 obrana", 4),
            BlokovaciaKarta("CESNAK", "+5 obrana", 5))

    private val tahaciBalicek = mutableListOf<Karta>()
    private val zahadzovaciBalicek = mutableListOf<Karta>()

    private val hratelneKarty = mutableListOf<Karta>()
    private val kartyNaVyber = mutableListOf<Karta>()
    private val pouziteKarty = mutableListOf<Int>()



    //Začiatok levelu
    fun vytvorTahaciBalicek(hracovBalicekKariet: List<Karta>) {
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
        val vybrataKarta = hratelneKarty[cisloKarty]

        pouziteKarty.add(cisloKarty)

        zahadzovaciBalicek.add(vybrataKarta)
        vybrataKarta.pouziKartu(hrac)
        hrac.uberManu()

        if (hratelneKarty.isEmpty())
            throw KoniecHracovhoTahuException()
    }

    fun vycistenieBalickov() {
        tahaciBalicek.clear()
        hratelneKarty.clear()
        zahadzovaciBalicek.clear()
        pouziteKarty.clear()
    }

    //Koniec levelu
    fun koniecLevelu() {
        vycistenieBalickov()
        vytvorKartyNaVyber()
    }

    fun zahodKartyNaVyber() {
        kartyNaVyber.clear()
    }




    fun hratelnaKarta(cisloKarty: Int): Karta = hratelneKarty[cisloKarty]
    fun kartaNaVyber(cisloKarty: Int): Karta = kartyNaVyber[cisloKarty]
    fun pouzitaKarta(index: Int):Int = pouziteKarty[index]
    fun pouziteKartySize() = pouziteKarty.size





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

    private fun vytvorKartyNaVyber() {
        for (i in 1..3) {
            kartyNaVyber.add(vzoryKariet[Random.nextInt(vzoryKariet.size)].naklonujSa())
        }
    }
}