package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentMapaHryBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentNovaHraBinding
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Lekar
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka

class NovaHraFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentNovaHraBinding                                                                    //BINDING -> odkazy na XML objekty
    private val postavy = arrayOf(Lekar(), Sestricka())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nova_hra, container,false)            // Nafúknutie layoutu pre fragment -> vytvorenie výzoru

        binding.button2.text = postavy[0].meno
        binding.button3.text = postavy[1].meno

        binding.button2.setOnClickListener {
            viewModel.nastavPostavu(postavy[0])
            findNavController().navigate(R.id.action_novaHraFragment_to_mapaHryFragment)}
        binding.button3.setOnClickListener {
            viewModel.nastavPostavu(postavy[1])
            findNavController().navigate(R.id.action_novaHraFragment_to_mapaHryFragment)}

        return binding.root
    }
}