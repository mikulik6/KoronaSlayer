package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentPrehraBinding
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Lekar

/**
 * Jednoduchý fragment informujúci o prehre hráča.
 * Fragment obsahuje informatívny text s názvom NPC, ktoré hráča porazilo, ilustračný obrázok mrtvej postavy a 2 tlačidlá.
 * Kde jedno tlačidlo presmeruje hráča na úvodnú obrazovku a jedno no fragment nová hra.
 */
class PrehraFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentPrehraBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_prehra, container,false)

        binding.prehraNazovNepriatelaText.text = viewModel.hrac.nepriatel?.meno

        binding.prehraPopisText.text = getString(R.string.prehraPopisText, if(viewModel.hrac.nepriatel?.muzskyRod == true) "premohol" else "premohla")

        if (viewModel.hrac is Lekar) {
            binding.prehraHracObrazok.setImageResource(R.drawable.doktor_mrtvy)
        } else {
           binding.prehraHracObrazok.setImageResource(R.drawable.sestricka_mrtva)
        }

        binding.prehraMenuTlacidlo.setOnClickListener {
            findNavController().navigate(R.id.action_prehraFragment_to_uvodnaStranaFragment)
        }
        binding.prehraNovaHraTlacidlo.setOnClickListener {
            findNavController().navigate(R.id.action_prehraFragment_to_novaHraFragment)
        }

        viewModel.restartujHru()

        return binding.root
    }

}