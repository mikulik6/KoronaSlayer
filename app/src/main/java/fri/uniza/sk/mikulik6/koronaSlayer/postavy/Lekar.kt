package fri.uniza.sk.mikulik6.koronaSlayer.postavy

/**
 * Typ Postavy pridavajúcej +3 blok na začiatku každého kola.
 *
 * @constructor Zavolá konštruktor predka s určitými hodnotami.
 */
class Lekar : Postava("Lekár", "+3 blok na začiatku každého kola",100) {

    /**
     * Slúži na zavolanie metódy nové kolo predka a nastavenie hodnoty bloku na 3.
     */
    override fun noveKolo() {
        super.noveKolo()
        super.pridajBlok(3)
    }
}