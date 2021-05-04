package fri.uniza.sk.mikulik6.koronaSlayer.postavy

class Sestricka : Postava("Sestrička", "+2 životy na začiatku každého kola",90){

    override fun noveKolo() {
        super.noveKolo()
        super.uzdravSa(2)
    }
}