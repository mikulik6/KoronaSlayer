package fri.uniza.sk.mikulik6.koronaSlayer.recyclerView.zoznamKarietData

/**
 * Dátová trieda ukladajúca informácie o určtej karte.
 * Okrem základných informácií obsahuje aj informáciu o type karty a o jej počte v hráčovom balíčku.
 *
 * @property nazov
 * @property popis
 * @property pocet
 * @property typ
 * @constructor Nastaví hodnoty atribútov na zadané hodnoty.
 */
data class KartaInfo(val nazov: String, val popis: String, var pocet: Int, val typ: TypKarty){}
