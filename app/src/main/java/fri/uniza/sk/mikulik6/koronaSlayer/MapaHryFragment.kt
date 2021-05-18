package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentMapaHryBinding
import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba

/**
 * Fragment slúžiaci hráčovi na vstup do určitého levelu.
 * Fragment zobrazuje 10 tlačidiel s názvami chorôb(NPC) a obrázkami o type choroby nachádzajúcich sa v určitom levely.
 * Tlačidlá sú okrem iného rozdielne farbou, kde zelená predstavuje level, ktorý hráča práve čaká.
 * Červená predstavuje level, ktorý už hráč prešiel a na koniec biela farba predstavuje levely ktoré hráča čakajú v budúcnosti.
 * AppBar umožnuje presmerovanie na úvodnú stranu alebo na fragment zobrazujúci informácie o postave.
 */
class MapaHryFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentMapaHryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mapa_hry, container,false)
        binding.hraViewModel = viewModel

        var pocitadlo = 1
        for (tlacidlo in binding.mapaHryConstraint.children){
            //Nastavenie farby tlacidla
            if (viewModel.hrac.aktualnyLevel > pocitadlo) {
                tlacidlo.setBackgroundColor(resources.getColor(R.color.redColor))
            } else if (viewModel.hrac.aktualnyLevel == pocitadlo) {
                tlacidlo.setBackgroundColor(resources.getColor(R.color.greenColor))
            }

            //Nastavenie obrázku tlačidla
            if (viewModel.mapa.choroba(pocitadlo - 1) is BakterialnaChoroba){
                (tlacidlo as (Button)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.bakteria_24, 0, 0, 0)
            } else {
                (tlacidlo as (Button)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.virus_24, 0, 0, 0)
            }

            val cisloLevelu = pocitadlo
            tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(cisloLevelu) }

            pocitadlo++
        }

        binding.mapaHryAppBar.setNavigationOnClickListener { findNavController().navigate(R.id.action_mapaHryFragment_to_uvodnaStranaFragment) }

        binding.mapaHryAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.postavaInfoItem -> {
                    findNavController().navigate(R.id.action_mapaHryFragment_to_postavaInfoFragment)
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    /**
     * Metóda volaná po kliknutí na tlačidlo levelu, kde hodnota parametra predstavuje číslo levelu.
     * V prípade že číslo levelu sa nezhoduje s číslom levelu hráča je hráč upozornený že klikol na level ktorý už prešiel alebo že ešte musí určitý level prejsť.
     *
     * @param cisloLevelu
     */
    private fun hracDoDalsejMiestnosti(cisloLevelu: Int) {
        when {
            cisloLevelu < viewModel.hrac.aktualnyLevel -> Toast.makeText(activity, "Túto chorobu si už porazil!", Toast.LENGTH_SHORT).show()
            cisloLevelu > viewModel.hrac.aktualnyLevel -> Toast.makeText(activity, "Momentálne ťa čaká ${viewModel.hrac.aktualnyLevel} level!", Toast.LENGTH_SHORT).show()
            else -> {
                viewModel.novyLevel()
                findNavController().navigate(R.id.action_mapaHryFragment_to_bojFragment)
            }
        }
    }
}