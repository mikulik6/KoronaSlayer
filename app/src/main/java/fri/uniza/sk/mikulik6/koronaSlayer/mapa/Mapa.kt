package fri.uniza.sk.mikulik6.koronaSlayer.mapa

import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.npc.VirusovaChoroba

class Mapa {
    val levely = arrayOf(
        BakterialnaChoroba("Kašeľ", 3, 16),
        VirusovaChoroba("Chrípka", 6,25),
        BakterialnaChoroba("Lepra", 10, 25),
        BakterialnaChoroba("Mor", 15, 40),
        VirusovaChoroba("Hepatitída-B", 14, 28),
        VirusovaChoroba("Encefalitída", 17, 31),
        VirusovaChoroba("Ebola", 13, 100),
        BakterialnaChoroba("Cholera", 18, 40),
        VirusovaChoroba("Spolík", 25, 26),
        VirusovaChoroba("Korona", 40, 134)
    )
    val pocetLevelov = levely.size

}