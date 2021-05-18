package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentVyberKartyBinding

/**
 * Fragment slúžiaci na výber novej karty v prípade, že hráč úspešne prešiel level.
 * Základom fragmentu sú 3 tlačidlá s názvamy a popisom vygenerovaných kariet na výber a informatívnym textom o porazení určitej choroby.
 */
class VyberKartyFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentVyberKartyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vyber_karty, container,false)
        binding.hraViewModel = viewModel

        binding.kartaNaVyber1Tlacidlo.setOnClickListener {
           hracSiVybralKartu(0)
        }
        binding.kartaNaVyber2Tlacidlo.setOnClickListener {
            hracSiVybralKartu(1)
        }
        binding.kartaNaVyber3Tlacidlo.setOnClickListener {
            hracSiVybralKartu(2)
        }

        //Nastavenie vyskocenia dialogoveho okna na BACK_BUTTON_CLICK
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Naozaj si nechceš vybrať žiadnu kartu?")
                        .setNegativeButton("Odísť") {dialog, which ->
                            findNavController().navigate(R.id.action_vyberKartyFragment_to_mapaHryFragment)
                            viewModel.riadicKariet.zahodKartyNaVyber()
                        }
                        .setPositiveButton("Vybrať") { dialog, which ->}
                        .show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        return binding.root
    }

    /**
     * Metóda volaná po klinutí na tlačidlo karty, kde parameter predstavuje číslo zvolenej karty.
     * Po zvolení je daná karta pridaná do hráčovho balíčka a hráč je presmerovaný na fragment mapa hry.
     *
     * @param cisloKarty
     */
    private fun hracSiVybralKartu(cisloKarty: Int) {
        viewModel.hracSiVybralKart(cisloKarty)
        findNavController().navigate(R.id.action_vyberKartyFragment_to_mapaHryFragment)
    }
}