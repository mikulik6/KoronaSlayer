package fri.uniza.sk.mikulik6.koronaSlayer.postavy

class Sestricka : Postava("Sestricka", 90){

    override fun noveKolo() {
        super.noveKolo()
        super.uzdravSa(2)
    }

    override fun ultimate() {
        TODO("Not yet implemented")
    }
}