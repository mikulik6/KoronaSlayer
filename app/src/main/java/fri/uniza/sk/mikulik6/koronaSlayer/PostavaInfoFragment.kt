package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fri.uniza.sk.mikulik6.koronaSlayer.recyclerView.adapter.ZoznamKarietAdapter
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentPostavaInfoBinding
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.AntibakterialnaUtociacaKarta
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.AntivirusovaUtociacaKarta
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.UzdravovaciaKarta
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Lekar
import fri.uniza.sk.mikulik6.koronaSlayer.recyclerView.zoznamKarietData.KartaInfo
import fri.uniza.sk.mikulik6.koronaSlayer.recyclerView.zoznamKarietData.TypKarty

/**
 * Fragment zobrazujúci základné informácie o postave a balíčku kariet hráča.
 * V hornej časti je zobrazený názov, životy, max. mana, pasívna schopnosť a ilustračný obrázok danej postavy.
 * V spodnej časti je zobrazený zoznam kariet v hráčovom balíčku implementovaným pomocou recyclerView.
 */
class PostavaInfoFragment : Fragment() {

    private val viewModel: HraViewModel by activityViewModels()
    private lateinit var binding: FragmentPostavaInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_postava_info, container,false)
        binding.hraViewModel = viewModel

        if (viewModel.hrac is Lekar) {
            binding.postavaInfoPostavaObrazok.setImageResource(R.drawable.doktor)
        } else {
            binding.postavaInfoPostavaObrazok.setImageResource(R.drawable.sestricka)
        }

        binding.postavaInfoTopAppBar.setNavigationOnClickListener { findNavController().navigate(R.id.action_postavaInfoFragment_to_mapaHryFragment) }

        val zoznamKariet = nacitajZoznamKariet()

        binding.postavaInfoZoznamKarietRecyclerView.adapter = ZoznamKarietAdapter(requireContext(), zoznamKariet)
        binding.postavaInfoZoznamKarietRecyclerView.setHasFixedSize(true)

        return binding.root
    }

    /**
     * Metóda slúžiaca na vytvorenie informačného zoznamu kariet.
     * Kde prvky predstavujú určitú kartu a informáciu o jej počte v hráčovom balíčku.
     *
     * @return
     */
    private fun nacitajZoznamKariet() : List<KartaInfo> {
        val zoznamKariet = mutableListOf<KartaInfo>()
        val hracovBalicekKariet = viewModel.hrac.getBalicekKariet()

        for (karta in hracovBalicekKariet) {
            val nazovKarty = karta.nazov

            var naslaSaKarta = false
            for (kartaData in zoznamKariet) {
                if (kartaData.nazov.equals(nazovKarty)) {
                    kartaData.pocet++
                    naslaSaKarta = true
                    break
                }
            }

            if (naslaSaKarta == false) {
                val typ: TypKarty = when (karta) {
                    is AntibakterialnaUtociacaKarta -> TypKarty.ANTIBAKTERIALNA
                    is AntivirusovaUtociacaKarta -> TypKarty.ANTIVIRUSOVA
                    is UzdravovaciaKarta -> TypKarty.UZDRAVOVACIA
                    else -> TypKarty.BLOKOVACIA
                }

                zoznamKariet.add(KartaInfo(karta.nazov, karta.popis, 1, typ))
            }
        }

        return zoznamKariet
    }

}