package fri.uniza.sk.mikulik6.koronaSlayer.recyclerView.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import fri.uniza.sk.mikulik6.koronaSlayer.R
import fri.uniza.sk.mikulik6.koronaSlayer.karty.typyKariet.Karta
import fri.uniza.sk.mikulik6.koronaSlayer.recyclerView.zoznamKarietData.KartaInfo
import fri.uniza.sk.mikulik6.koronaSlayer.recyclerView.zoznamKarietData.TypKarty

class ZoznamKarietAdapter(private val context: Context, private val zoznamKariet: List<KartaInfo>) : RecyclerView.Adapter<ZoznamKarietAdapter.ZoznamKarietViewHolder>() {
    class ZoznamKarietViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nazovText: TextView = view.findViewById(R.id.zoznamKarietItemNazovText)
        val popisText: TextView = view.findViewById(R.id.zoznamKarietItemPopisText)
        val pocetText: TextView = view.findViewById(R.id.zoznamKarietItemPocetText)
        val constraint: ConstraintLayout = view.findViewById(R.id.zoznamKarietItemConstraint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoznamKarietViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.zoznam_kariet_recycler_view_item, parent, false)

        return ZoznamKarietViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ZoznamKarietViewHolder, position: Int) {
        when(zoznamKariet[position].typ) {
            /*TypKarty.ANTIBAKTERIALNA -> context.getDrawable(R.drawable.nova_hra_constraint_layout_design)?.setTint(context.resources.getColor(R.color.lightRedColor))
            TypKarty.ANTIVIRUSOVA -> context.getDrawable(R.drawable.nova_hra_constraint_layout_design)?.setTint(context.resources.getColor(R.color.lightRedColor))
            TypKarty.BLOKOVACIA -> context.getDrawable(R.drawable.nova_hra_constraint_layout_design)?.setTint(context.resources.getColor(R.color.yellowColor))
            else -> context.getDrawable(R.drawable.nova_hra_constraint_layout_design)?.setTint(context.resources.getColor(R.color.lightBlueColor))*/
            TypKarty.ANTIBAKTERIALNA -> holder.constraint.setBackgroundColor(context.resources.getColor(R.color.lightRedColor))
            TypKarty.ANTIVIRUSOVA -> holder.constraint.setBackgroundColor(context.resources.getColor(R.color.lightRedColor))
            TypKarty.UZDRAVOVACIA -> holder.constraint.setBackgroundColor(context.resources.getColor(R.color.greenColor))
            else -> holder.constraint.setBackgroundColor(context.resources.getColor(R.color.yellowColor))
        }

        holder.nazovText.text = zoznamKariet[position].nazov
        holder.popisText.text = zoznamKariet[position].popis
        holder.pocetText.text = context.resources.getString(R.string.zoznamKarietItemPocet, zoznamKariet[position].pocet)



        //holder.constraint.setBackgroundColor(context.resources.getColor(R.color.lightRedColor))
    }

    override fun getItemCount(): Int {
        return zoznamKariet.size
    }
}