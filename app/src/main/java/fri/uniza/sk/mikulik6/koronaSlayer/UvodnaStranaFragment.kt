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
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentUvodnaStranaBinding
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka

class UvodnaStranaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentUvodnaStranaBinding>(inflater, R.layout.fragment_uvodna_strana, container,false)

        //Nastavenie onClickListenerov
        binding.spustenieHryTlacidlo.setOnClickListener { view : View -> view.findNavController().navigate(R.id.action_uvodnaStranaFragment_to_novaHraFragment) }

        return binding.root
    }
}