package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentMapaHryBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentUvodnaStranaBinding
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka

class UvodnaStranaFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentUvodnaStranaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_uvodna_strana, container,false)

        //Nastavenie viditelnosti tlačidlo "Pokračovať v hre" podľa parametra z HraViewModel
        if(!viewModel.bolaVytvorenaHra) {
            binding.pokracovatVHreTlacidlo.visibility = View.INVISIBLE
        } else {
            binding.pokracovatVHreTlacidlo.visibility = View.VISIBLE
        }

        //Nastavenie onClickListenerov
        binding.pokracovatVHreTlacidlo.setOnClickListener { findNavController().navigate(R.id.action_uvodnaStranaFragment_to_mapaHryFragment) }
        binding.novaHraTlacidlo.setOnClickListener { novaHra() }

        return binding.root
    }

    private fun novaHra() {
        if (!viewModel.bolaVytvorenaHra) {
            findNavController().navigate(R.id.action_uvodnaStranaFragment_to_novaHraFragment)
        } else {
            MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Hra už existuje!")
                    .setMessage("Chceš vytvoriť novú hru?")
                    .setNegativeButton("Nie") {dialog, which -> }
                    .setPositiveButton("OK") { dialog, which ->
                        viewModel.restartujHru()
                        findNavController().navigate(R.id.action_uvodnaStranaFragment_to_novaHraFragment)
                    }
                    .show()
        }
    }
}