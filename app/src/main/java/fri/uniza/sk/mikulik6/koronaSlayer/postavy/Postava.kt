package fri.uniza.sk.mikulik6.koronaSlayer.postavy

import fri.uniza.sk.mikulik6.koronaSlayer.hlavny.Akcia
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.*
import fri.uniza.sk.mikulik6.koronaSlayer.mapa.Mapa
import fri.uniza.sk.mikulik6.koronaSlayer.npc.ChorobaNpc
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.NedostatocnyPocetManyException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.SmrtHracaException

abstract class Postava(val meno: String, pZdravie: Int) {
                                                            //meno: getter
    val maxZdravie: Int = pZdravie
    var zdravie: Int = pZdravie
    var mana: Int = 3                                       //getter
    var blok:Int = 0
    var ultimateTokens:Int = 0                              //getter
    var aktualnyLevel: Int = 1                              //getter
    var akcia: Akcia = Akcia.VYBER_MIESTNOSTI               //getter
    var nepriatel: ChorobaNpc? = null                       //getter
    val balicekKariet = mutableListOf<Karta>()              //getter

    init {
        vytvorZakladnyBalicek()
    }

    //Slúži na prijatie útoku (zníženie životov) od NPC o hodnotu útoku zadanú ako parameter.
    fun prijmiUtok(pDamage: Int) {
        var damage: Int = pDamage
        damage -= blok

        if(damage > 0) {
            zdravie -= damage
            if(zdravie <= 0) {
                throw SmrtHracaException()
            }
        }

        blok = 0
    }

    fun uzdravSa(pUzdravenie: Int) {
        var uzdravenie: Int = pUzdravenie

        if (this.maxZdravie < this.zdravie + uzdravenie) {
            uzdravenie = this.maxZdravie - this.zdravie
        }

        this.zdravie += uzdravenie
    }

    fun pridajBlok(blok: Int) {
        this.blok = blok
    }

    fun uberManu(pocetPouzitejMany: Int) {
        if (mana < pocetPouzitejMany) {
            throw NedostatocnyPocetManyException()
        }

        mana -= pocetPouzitejMany;
    }

    fun chodDoDalsejMiestnosti(mapa: Mapa, cislo: Int) {
        this.akcia = Akcia.HRANIE_KARIET;
        //this.nepriatel = mapa.getNpc(this.aktualnyLevel, cislo);
        this.nepriatel = mapa.levely[this.aktualnyLevel]
    }

    fun zabilSiNepriatela() {
        aktualnyLevel++
        nepriatel = null
        akcia = Akcia.VYBERANIE_KARIET
        mana = 3
        blok = 0
        ultimateTokens++
    }

    open fun noveKolo() {
        mana = 3
        blok = 0
    }

    fun pridajKartu(vybrataKarta: Karta) {
        balicekKariet.add(vybrataKarta)
        akcia = Akcia.VYBER_MIESTNOSTI
    }

    abstract fun ultimate()

    private fun vytvorZakladnyBalicek() {
        var pocitadlo: Int = 0

        while (pocitadlo < 10) {
            if (pocitadlo < 3) {
                this.balicekKariet.add(AntibakterialnaUtociacaKarta("Peniclin", "Vhodny proti bakteriam", 1, 5))
            } else if (pocitadlo < 6) {
                this.balicekKariet.add(AntivirusovaUtociacaKarta("Ibalgin", "Vhodny proti virusom", 1, 5));
            } else if (pocitadlo < 8) {
                this.balicekKariet.add(UzdravovaciaKarta("Horuci caj", "Liek ako ziadny iny", 1, 10));
            } else {
                this.balicekKariet.add(BlokovaciaKarta("Cibula", "Posilnenie na 1 kolo", 1, 4));
            }
            pocitadlo++
        }
    }
}