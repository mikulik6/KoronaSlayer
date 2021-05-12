package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentBojBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentPrehraBinding
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Lekar

class PrehraFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentPrehraBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_prehra, container,false)

        if (viewModel.hrac is Lekar) {
            binding.prehraHracObrazok.setImageResource(R.drawable.doktor_mrtvy)
        } else {
           binding.prehraHracObrazok.setImageResource(R.drawable.sestricka_mrtva)
        }

        binding.prehraNazovNepriatelaText.text = viewModel.hrac.nepriatel?.meno

        binding.prehraPopisText.text = getString(R.string.prehraPopisText, if(viewModel.hrac.nepriatel?.muzskyRod == true) "premhol" else "premohla")

        viewModel.restartujHru()

        binding.prehraMenuTlacidlo.setOnClickListener {
            findNavController().navigate(R.id.action_prehraFragment_to_uvodnaStranaFragment)
        }
        binding.prehraNovaHraTlacidlo.setOnClickListener {
            findNavController().navigate(R.id.action_prehraFragment_to_novaHraFragment)
        }

        return binding.root
    }

}