

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.jesusapp.R
import com.example.jesusapp.data.model.Users
import com.example.jesusapp.ui.recyclerview.UserItemDiffCallback
import com.example.jesusapp.ui.recyclerview.UserViewHolder

class UsersAdapters : ListAdapter<Users, UserViewHolder>(UserItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}