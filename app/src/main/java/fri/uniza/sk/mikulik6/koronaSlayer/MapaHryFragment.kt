package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentMapaHryBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentNovaHraBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentUvodnaStranaBinding
import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava

class MapaHryFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentMapaHryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mapa_hry, container,false)
        binding.hraViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        var pocitadlo = 1
        for (tlacidlo in binding.mapaHryConstraint.children){
            if (viewModel.hrac.aktualnyLevel > pocitadlo) {
                tlacidlo.setBackgroundColor(resources.getColor(R.color.redColor))
            } else if (viewModel.hrac.aktualnyLevel == pocitadlo) {
                tlacidlo.setBackgroundColor(resources.getColor(R.color.greenColor))
            }

            if (viewModel.mapa.levely[pocitadlo - 1] is BakterialnaChoroba){
                (tlacidlo as (Button)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.bakteria_24, 0, 0, 0)
            } else {
                (tlacidlo as (Button)).setCompoundDrawablesWithIntrinsicBounds(R.drawable.virus_24, 0, 0, 0)
            }
            pocitadlo++
        }

        binding.level1Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(1) }
        binding.level2Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(2) }
        binding.level3Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(3) }
        binding.level4Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(4) }
        binding.level5Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(5) }
        binding.level6Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(6) }
        binding.level7Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(7) }
        binding.level8Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(8) }
        binding.level9Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(9) }
        binding.level10Tlacidlo.setOnClickListener { hracDoDalsejMiestnosti(10) }

        binding.topAppBar.setNavigationOnClickListener { findNavController().navigate(R.id.action_mapaHryFragment_to_uvodnaStranaFragment) }

        return binding.root
    }

    private fun hracDoDalsejMiestnosti(cisloLevelu: Int) {
        if (cisloLevelu == viewModel.hrac.aktualnyLevel) {
            viewModel.hrac.chodDoDalsejMiestnosti(viewModel.mapa)
            findNavController().navigate(R.id.action_mapaHryFragment_to_bojFragment)
        } else {
            Toast.makeText(activity, "Toto je nespravny level!", Toast.LENGTH_SHORT).show()
        }
    }
}