package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentMapaHryBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentUvodnaStranaBinding

class MapaHryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMapaHryBinding>(inflater, R.layout.fragment_mapa_hry, container,false)
        return binding.root
    }
}