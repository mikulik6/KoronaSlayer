package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentBojBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentMapaHryBinding
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka

class BojFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentBojBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_boj, container,false)

        //inicializácia DATA BINDING
        binding.hraViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.karta1Tlacidlo.setOnClickListener {
            viewModel.riadicKariet.zahrajKartu(0, viewModel.hrac)
            binding.karta1Tlacidlo.visibility = View.INVISIBLE
        }
        binding.karta2Tlacidlo.setOnClickListener {
            viewModel.riadicKariet.zahrajKartu(1, viewModel.hrac)
            binding.karta2Tlacidlo.visibility = View.INVISIBLE
        }
        binding.karta3Tlacidlo.setOnClickListener {
            viewModel.riadicKariet.zahrajKartu(2, viewModel.hrac)
            binding.karta3Tlacidlo.visibility = View.INVISIBLE
        }
        binding.karta4Tlacidlo.setOnClickListener {
            viewModel.riadicKariet.zahrajKartu(3, viewModel.hrac)
            binding.karta4Tlacidlo.visibility = View.INVISIBLE
        }
        binding.karta5Tlacidlo.setOnClickListener {
            viewModel.riadicKariet.zahrajKartu(4, viewModel.hrac)
            binding.karta5Tlacidlo.visibility = View.INVISIBLE
        }
        binding.ukoncenieKolaTlacidlo.setOnClickListener {

        }

        zaciatokKola()
        return binding.root
    }

    private fun zaciatokKola() {
        viewModel.riadicKariet.vytvorTahaciBalicek(viewModel.hrac.balicekKariet)
        viewModel.riadicKariet.noveKolo()
        viewModel.hrac.noveKolo()
    }
}