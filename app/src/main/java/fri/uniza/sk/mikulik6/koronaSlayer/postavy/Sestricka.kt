package fri.uniza.sk.mikulik6.koronaSlayer.postavy

/**
 * Typ Postavy zvyšujúcej svoje životy o 2 na začiatku každého kola.
 *
 * @constructor Zavolá konštruktor predka s určitými hodnotami.
 */
class Sestricka : Postava("Sestrička", "+2 životy na začiatku každého kola",90){

    /**
     * Slúži na zavolanie metódy nové kolo predka a nastavenie zvýšenie svojích životov o 2.
     */
    override fun noveKolo() {
        super.noveKolo()
        super.uzdravSa(2)
    }
}