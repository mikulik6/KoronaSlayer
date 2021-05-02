package fri.uniza.sk.mikulik6.koronaSlayer.postavy

class Lekar : Postava("Lekar", 100) {

    override fun noveKolo() {
        super.noveKolo()
        super.pridajBlok(3)

    }

    override fun ultimate() {
        TODO("Not yet implemented")
    }

}