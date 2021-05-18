package fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet

import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

/**
 * Abstraktná trieda slúžiaca pre ukladanie základných informácií o karte a pre deklarovanie 2 abstraktných metód.
 *
 * @property nazov Predstavuje názov danej karty.
 * @property popis Predstavuje popis danej karty.
 * @property hodnotaAkcie Predstavuje hodnotu určitej akcie na základe typu karty.
 * @constructor Nastaví hodnoty jednotlivých atribútov názov, popis a hodnota akcie.
 */
abstract class Karta(val nazov: String, val popis: String, val hodnotaAkcie: Int) {

    /**
     * Abstraktná metóda pre vykonanie určitej akcie na základe typu karty.
     *
     * @param hrac
     */
    abstract fun pouziKartu(hrac: Postava)

    /**
     * Abstraktná metóda slúžiaca na vrátenie kópie karty.
     *
     * @return kópiu určtého typu karty.
     */
    abstract fun naklonujSa(): Karta
}