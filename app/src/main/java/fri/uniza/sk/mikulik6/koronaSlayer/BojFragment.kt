package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentBojBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentMapaHryBinding
import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.MrtveNpcException

class BojFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentBojBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_boj, container,false)

        viewModel.riadicKariet.vytvorTahaciBalicek(viewModel.hrac.balicekKariet)
        zaciatokKola()

        //inicializácia DATA BINDING
        binding.hraViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if (viewModel.hrac.nepriatel is BakterialnaChoroba) {
            binding.nepriatelObrazok.setImageResource(R.drawable.bakteria)
        } else {
            binding.nepriatelObrazok.setImageResource(R.drawable.virus)
        }


        binding.karta1Tlacidlo.setOnClickListener {
            binding.karta1Tlacidlo.visibility = View.INVISIBLE
            kliknutieTlacidlaKarty(0)
        }
        binding.karta2Tlacidlo.setOnClickListener {
            binding.karta2Tlacidlo.visibility = View.INVISIBLE
            kliknutieTlacidlaKarty(1)
        }
        binding.karta3Tlacidlo.setOnClickListener {
            binding.karta3Tlacidlo.visibility = View.INVISIBLE
            kliknutieTlacidlaKarty(2)
        }
        binding.karta4Tlacidlo.setOnClickListener {
            binding.karta4Tlacidlo.visibility = View.INVISIBLE
            kliknutieTlacidlaKarty(3)
        }
        binding.karta5Tlacidlo.setOnClickListener {
            binding.karta5Tlacidlo.visibility = View.INVISIBLE
            kliknutieTlacidlaKarty(4)
        }


        binding.ukoncenieKolaTlacidlo.setOnClickListener {
            zaciatokKola()
        }

        return binding.root
    }

    private fun zaciatokKola() {
        binding.karta1Tlacidlo.visibility = View.VISIBLE
        binding.karta2Tlacidlo.visibility = View.VISIBLE
        binding.karta3Tlacidlo.visibility = View.VISIBLE
        binding.karta4Tlacidlo.visibility = View.VISIBLE
        binding.karta5Tlacidlo.visibility = View.VISIBLE

        viewModel.riadicKariet.noveKolo()
        viewModel.hrac.noveKolo()
    }

    private fun kliknutieTlacidlaKarty(cisloKarty: Int) {
        try {
            viewModel.riadicKariet.zahrajKartu(cisloKarty, viewModel.hrac)
        } catch (e: KoniecHracovhoTahuException) {
            viewModel.hrac.nepriatel?.vykonajAkciu(viewModel.hrac)
            zaciatokKola()
        } catch (e: MrtveNpcException) {
            viewModel.riadicKariet.koniecLevelu()
            viewModel.hrac.zabilSiNepriatela()
            findNavController().navigate(R.id.action_bojFragment_to_mapaHryFragment)
        }
    }
}