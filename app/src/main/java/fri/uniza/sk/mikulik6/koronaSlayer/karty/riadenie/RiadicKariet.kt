package fri.uniza.sk.mikulik6.koronaSlayer.karty.riadenie

import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.*
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import kotlin.random.Random

/**
 * Trieda zodpovedajúca za prácu s kartami počas hry.
 * Trieda obsahuje pole vzorov kariet, z ktorých sú 3 generované na konci levelu.
 * Okrem iného obsahuje listy ako sú zahadzovací, ťahací balíček, hratelné karty a použité karty, ktoré sú používané počas levelu.
 *
 * @constructor Vytvorí zoznam vzorov kariet a inicializuje prázdne listy potrebné počas hry.
 * @property vzoryKariet Pole obsahujúce vzory všetkých kariet hry.
 */
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


    /**
     * Slúži na naplnenie ťahacieho balíčka na začiatku každého levelu.
     * Je naplnená kartami na základe kariet v hráčovom baíčku.
     *
     * @param hracovBalicekKariet
     */
    fun vytvorTahaciBalicek(hracovBalicekKariet: List<Karta>) {
        tahaciBalicek.addAll(hracovBalicekKariet)
    }

    /**
     * Používaná na začiatku každého kola pre vyprázdenie listov hratelných a použitých kariet a následné vytiahnutie nových hratelných kariet.
     */
    fun noveKolo() {
        zahodHratelneKarty()
        vytiahniHratelneKarty()
        pouziteKarty.clear()
    }

    /**
     * Slúži na zahranie karty za blíčku hratelné karty na zákalde čísla karty zadaného ako parameter.
     * Najskôr je daná karta označená ako použitá pridaním čísla karty do zoznamu použitých kariet.
     * Následne je karta použitá a hráčovi ubratá mana.
     *
     * @param cisloKarty
     * @param hrac
     */
    fun zahrajKartu(cisloKarty: Int, hrac: Postava) {
        val vybrataKarta = hratelneKarty[cisloKarty]

        pouziteKarty.add(cisloKarty)

        //zahadzovaciBalicek.add(vybrataKarta)
        vybrataKarta.pouziKartu(hrac)
        hrac.uberManu()
    }

    /**
     * Používaná na konci alebo pri reštatovaní levelu pre vyčistenie zoznamov kariet používaných počas levelu.
     */
    fun vycistenieBalickov() {
        tahaciBalicek.clear()
        hratelneKarty.clear()
        zahadzovaciBalicek.clear()
        pouziteKarty.clear()
    }

    /**
     * Slúži pre zabezpečenie vyčistenia zoznamov používaných počas levelu a pre vytvorenie kariet na výber.
     */
    fun koniecLevelu() {
        vycistenieBalickov()
        vytvorKartyNaVyber()
    }

    /**
     * Slúži na vyčistenie zoznamku kariet na výber po úspešnom vybratí karty.
     */
    fun zahodKartyNaVyber() {
        kartyNaVyber.clear()
    }

    /**
     * Slúži na vrátenie hratelnej karty podľa čísla karty zadaného ako parameter
     *
     * @param cisloKarty
     * @return
     */
    fun hratelnaKarta(cisloKarty: Int): Karta = hratelneKarty[cisloKarty]

    /**
     * Slúži na vrátenie karty na výber podľa čísla karty zadaného ako parameter
     *
     * @param cisloKarty
     * @return
     */
    fun kartaNaVyber(cisloKarty: Int): Karta = kartyNaVyber[cisloKarty]

    /**
     * Metóda slúžiaca na vrátenie čísla použitej karty zo zonamu použitých kariet.
     *
     * @param index
     * @return
     */
    fun pouzitaKarta(index: Int):Int = pouziteKarty[index]

    /**
     * Metóda slúžiaca na vrátenie veľkosti zoznamu použitých kariet.
     */
    fun pouziteKartySize() = pouziteKarty.size


    /**
     * Slúži na pridanie všetkých hratelných kariet do zoznamu zahadzovacieho balíčka a následné vyčistenie balíčka hratelných kariet.
     */
    private fun zahodHratelneKarty() {
        zahadzovaciBalicek.addAll(hratelneKarty)
        hratelneKarty.clear()
    }

    /**
     * Slúži na náhodné naplnenie zoznamu hratelných kariet zo zoznamu ťahací balíček.
     * V prípade, že v zozname ťahací baíček sa nachádza menej ako 5 kariet sú do neho vrátené všetky karty zo zahadzovacieho balíčka, ktorý je následne vyčistený.
     */
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

    /**
     * Slúži pre vytvorenie 3 náhodných kópií kariet zo zoznamu vzorov a ich následné vloženie do zoznamu karty na výber.
     */
    private fun vytvorKartyNaVyber() {
        for (i in 1..3) {
            kartyNaVyber.add(vzoryKariet[Random.nextInt(vzoryKariet.size)].naklonujSa())
        }
    }
}