
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kube.notasalexander.R
import com.kube.notasalexander.model.User

class MyAdapter(private val userList: ArrayList<User>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userList[position]
        holder.nombre.text = user.nombre
        holder.marca.text = user.marca
        holder.color.text = user.color
        holder.cili.text = user.cilindros.toString()
        holder.NPuertas.text = user.puertas.toString()

    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.nombre)
        val marca: TextView = itemView.findViewById(R.id.marca)
        val color: TextView = itemView.findViewById(R.id.color)
        val cili: TextView = itemView.findViewById(R.id.cilindro)
        val NPuertas: TextView = itemView.findViewById(R.id.puertas)
    }
}
