package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentBojBinding
import fri.uniza.sk.mikulik6.koronaSlayer.npc.BakterialnaChoroba
import fri.uniza.sk.mikulik6.koronaSlayer.vynimky.*

class BojFragment : Fragment(){

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentBojBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_boj, container,false)
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
            zahranieKarty(0)
        }
        binding.karta2Tlacidlo.setOnClickListener {
            binding.karta2Tlacidlo.visibility = View.INVISIBLE
            zahranieKarty(1)
        }
        binding.karta3Tlacidlo.setOnClickListener {
            binding.karta3Tlacidlo.visibility = View.INVISIBLE
            zahranieKarty(2)
        }
        binding.karta4Tlacidlo.setOnClickListener {
            binding.karta4Tlacidlo.visibility = View.INVISIBLE
            zahranieKarty(3)
        }
        binding.karta5Tlacidlo.setOnClickListener {
            binding.karta5Tlacidlo.visibility = View.INVISIBLE
            zahranieKarty(4)
        }
        binding.ukoncenieKolaTlacidlo.setOnClickListener {
            koniecHracovhoTahu()
        }

        //Nastavenie vyskocenia dialogoveho okna na BACK_BUTTON_CLICK
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Level bude resetovaný!")
                        .setMessage("Naozaj chceš opustiť level?")
                        .setNegativeButton("Nie") {dialog, which -> }
                        .setPositiveButton("Áno") { dialog, which ->
                            viewModel.resetujLevel()
                            findNavController().navigate(R.id.action_bojFragment_to_mapaHryFragment)
                        }
                        .show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        //Nastavenie textov a visibility pri otočení obrazovky
        if (savedInstanceState == null)
            zaciatokKola()
        else {
            nastavenieTextovKariet()

            for (i in 0 until viewModel.riadicKariet.pouziteKartySize()) {
                when(viewModel.riadicKariet.pouzitaKarta(i)) {
                    0    -> binding.karta1Tlacidlo.visibility = View.INVISIBLE
                    1    -> binding.karta2Tlacidlo.visibility = View.INVISIBLE
                    2    -> binding.karta3Tlacidlo.visibility = View.INVISIBLE
                    3    -> binding.karta4Tlacidlo.visibility = View.INVISIBLE
                    else -> binding.karta5Tlacidlo.visibility = View.INVISIBLE
                }
            }
        }

        return binding.root
    }





    private fun zaciatokKola() {
        viewModel.noveKolo()
        nastavenieTextovKariet()
        for(tlacidlo in binding.bojTlacidlaConstraint.children)
            tlacidlo.visibility = View.VISIBLE
    }

    private fun nastavenieTextovKariet(){
        binding.karta1Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelnaKarta(0).nazov, viewModel.riadicKariet.hratelnaKarta(0).popis)
        binding.karta2Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelnaKarta(1).nazov, viewModel.riadicKariet.hratelnaKarta(1).popis)
        binding.karta3Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelnaKarta(2).nazov, viewModel.riadicKariet.hratelnaKarta(2).popis)
        binding.karta4Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelnaKarta(3).nazov, viewModel.riadicKariet.hratelnaKarta(3).popis)
        binding.karta5Tlacidlo.text = getString(R.string.kartaTlacidlo, viewModel.riadicKariet.hratelnaKarta(4).nazov, viewModel.riadicKariet.hratelnaKarta(4).popis)
    }

    private fun zahranieKarty(cisloKarty: Int) {
        try {
            viewModel.zahranieKarty(cisloKarty)
        } catch (e: KoniecHracovhoTahuException) {
            koniecHracovhoTahu()
        } catch (e: DalsieLevelException) {
            findNavController().navigate(R.id.action_bojFragment_to_vyberKartyFragment)
        } catch (e: VyhraException) {
            findNavController().navigate(R.id.action_bojFragment_to_vyhraFragment)
        }
    }

    private fun koniecHracovhoTahu() {
        try {
            viewModel.nepriatelUtok()
            zaciatokKola()
        } catch (e: SmrtHracaException) {
            findNavController().navigate(R.id.action_bojFragment_to_prehraFragment)
        }
    }
}