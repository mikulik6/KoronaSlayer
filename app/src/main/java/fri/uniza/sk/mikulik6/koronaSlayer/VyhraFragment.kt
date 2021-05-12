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
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentVyhraBinding

class VyhraFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentVyhraBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vyhra, container,false)

        viewModel.restartujHru()

        binding.vyhraMenuTlacidlo.setOnClickListener {
            findNavController().navigate(R.id.action_vyhraFragment_to_uvodnaStranaFragment)
        }
        binding.vyhraNovaHraTlacidlo.setOnClickListener {
            findNavController().navigate(R.id.action_vyhraFragment_to_novaHraFragment)
        }

        return binding.root
    }


}