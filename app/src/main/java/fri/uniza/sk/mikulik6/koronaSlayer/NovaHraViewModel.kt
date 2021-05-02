package fri.uniza.sk.mikulik6.koronaSlayer

import androidx.lifecycle.ViewModel
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Lekar
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Postava
import fri.uniza.sk.mikulik6.koronaSlayer.postavy.Sestricka

class NovaHraViewModel : ViewModel() {

    val postavy = arrayOf(Lekar(), Sestricka())



}