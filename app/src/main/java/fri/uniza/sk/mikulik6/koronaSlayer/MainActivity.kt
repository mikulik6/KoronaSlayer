package fri.uniza.sk.mikulik6.koronaSlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate

/**
 * Aktivita pokrývajúca všetky fragmenty aplikácie.
 * Taktiež slúži pre základné nastavenia aplikácie ako je Full Screen mód aleboy schovanie Action Baru.
 *
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //defaultný night mód
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        //schovanie Action Baru
        supportActionBar?.hide()
        //nastavenie full screenu
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}