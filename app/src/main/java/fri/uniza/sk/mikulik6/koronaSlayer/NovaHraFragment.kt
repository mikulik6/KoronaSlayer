package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentNovaHraBinding
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Lekar
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka

class NovaHraFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentNovaHraBinding
    private val postavy = arrayOf(Lekar(), Sestricka())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nova_hra, container,false)            // Nafúknutie layoutu pre fragment -> vytvorenie výzoru

        binding.postava1 = postavy[0]
        binding.postava2 = postavy[1]

        binding.novaHraAppBar.setNavigationOnClickListener { findNavController().navigate(R.id.action_novaHraFragment_to_uvodnaStranaFragment) }

        binding.postava1Constraint.setOnClickListener {
            viewModel.nastavPostavu(postavy[0])
            findNavController().navigate(R.id.action_novaHraFragment_to_mapaHryFragment)
        }
        binding.postava2Constraint.setOnClickListener {
            viewModel.nastavPostavu(postavy[1])
            findNavController().navigate(R.id.action_novaHraFragment_to_mapaHryFragment)
        }

        return binding.root
    }
}