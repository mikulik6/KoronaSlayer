package fri.uniza.sk.mikulik6.koronaSlayer

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentBojBinding
import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.KoniecHracovhoTahuException
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.MrtveNpcException

class BojFragment : Fragment(){

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentBojBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_boj, container,false)
        //inicializácia DATA BINDING
        binding.hraViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //Nastavenie obrázku nepriateľa podľa typu nepraiteľa nastaveného v Postave
        if (viewModel.hrac.nepriatel is BakterialnaChoroba) {
            binding.nepriatelObrazok.setImageResource(R.drawable.bakteria)
        } else {
            binding.nepriatelObrazok.setImageResource(R.drawable.virus)
        }

        //Nastavenie onClickListenerov pre KARTY_Tlacidla + UKONCENIE_KOLA_Tlacidlo
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
            viewModel.hrac.nepriatel?.vykonajAkciu(viewModel.hrac)
            zaciatokKola()
        }

        //Nastavenie vyskocenia dialogoveho okna na BACK_BUTTON_CLICK
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Naozaj chceš ukončiť kolo?")
                        .setNegativeButton("Nie") {dialog, which -> }
                        .setPositiveButton("Áno") { dialog, which ->
                            findNavController().navigate(R.id.action_bojFragment_to_mapaHryFragment)
                            viewModel.resetujLevel()
                        }
                        .show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        //Vykonanie akcie na zaciatku kola
        zaciatokKola()

        return binding.root
    }

    private fun zaciatokKola() {
        viewModel.noveKolo()

        binding.karta1Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelneKarty[0].nazov, viewModel.riadicKariet.hratelneKarty[0].popis)
        binding.karta2Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelneKarty[1].nazov, viewModel.riadicKariet.hratelneKarty[1].popis)
        binding.karta3Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelneKarty[2].nazov, viewModel.riadicKariet.hratelneKarty[2].popis)
        binding.karta4Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelneKarty[3].nazov, viewModel.riadicKariet.hratelneKarty[3].popis)
        binding.karta5Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelneKarty[4].nazov, viewModel.riadicKariet.hratelneKarty[4].popis)

        binding.karta1Tlacidlo.visibility = View.VISIBLE
        binding.karta2Tlacidlo.visibility = View.VISIBLE
        binding.karta3Tlacidlo.visibility = View.VISIBLE
        binding.karta4Tlacidlo.visibility = View.VISIBLE
        binding.karta5Tlacidlo.visibility = View.VISIBLE
    }

    private fun kliknutieTlacidlaKarty(cisloKarty: Int) {
        try {
            viewModel.riadicKariet.zahrajKartu(cisloKarty, viewModel.hrac)
        } catch (e: KoniecHracovhoTahuException) {
            viewModel.hrac.nepriatel?.vykonajAkciu(viewModel.hrac)
            zaciatokKola()
        } catch (e: MrtveNpcException) {
            //findNavController().navigate(R.id.action_bojFragment_to_mapaHryFragment)
            viewModel.riadicKariet.koniecLevelu()
            viewModel.hrac.zabilSiNepriatela()
            findNavController().navigate(R.id.action_bojFragment_to_vyberKartyFragment)
        }
    }
}