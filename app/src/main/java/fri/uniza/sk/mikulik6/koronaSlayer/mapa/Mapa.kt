package fri.uniza.sk.mikulik6.koronaSlayer.mapa

import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.npc.VirusovaChoroba

class Mapa {
    val levely = arrayOf(
        BakterialnaChoroba("Kašeľ", 3, 16, true),
        VirusovaChoroba("Chrípka", 6,25, false),
        BakterialnaChoroba("Lepra", 10, 25, false),
        BakterialnaChoroba("Mor", 15, 40, true),
        VirusovaChoroba("Hepatitída-B", 14, 28, false),
        VirusovaChoroba("Encefalitída", 17, 31, false),
        VirusovaChoroba("Ebola", 13, 100, false),
        BakterialnaChoroba("Cholera", 18, 40, false),
        VirusovaChoroba("Soplík", 25, 26, true),
        VirusovaChoroba("Korona", 15, 134, false)
    )
    val pocetLevelov = levely.size

}