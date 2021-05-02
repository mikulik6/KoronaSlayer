package fri.uniza.sk.mikulik6.koronaSlayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentMapaHryBinding
import fri.uniza.sk.mikulik6.koronaSlayer.databinding.FragmentNovaHraBinding

class NovaHraFragment : Fragment() {

    private val viewModel: NovaHraViewModel by viewModels()
    //BINDING -> odkazy na XML objekty
    private lateinit var binding: FragmentNovaHraBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //val binding = DataBindingUtil.inflate<FragmentNovaHraBinding>(inflater, R.layout.fragment_nova_hra, container,false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nova_hra, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}