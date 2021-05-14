package fri.uniza.sk.mikulik6.koronaSlayer.postavy

class Lekar : Postava("Lekár", "+3 blok na začiatku každého kola",100) {

    override fun noveKolo() {
        super.noveKolo()
        super.pridajBlok(3)
    }
}