package fri.uniza.sk.mikulik6.koronaSlayer.mapa

import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.npc.VirusovaChoroba

/**
 * Trieda slúžiaca pre vytvorenie a ukladanie zoznamu NPC prislúchajúcich určitému levelu.
 * Neskôr počas hry slúži pre získavanie inštancií NPC podľa levelu, v kotorom sa hráč aktuálne nachádza.
 */
class Mapa {
    private val levely = arrayOf(
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

    /**
     * Slúži na vrátenie počtu levelov mapy.
     */
    val pocetLevelov = levely.size

    /**
     * Slúži na vrátenie inštancie choroby v levely zadanom ako parameter.
     *
     * @param cisloLevelu
     */
    fun choroba(cisloLevelu: Int) = levely[cisloLevelu]
}