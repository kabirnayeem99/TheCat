package io.github.kabirnayeem99.thecat.ui.catList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.kabirnayeem99.thecat.databinding.GridItemCatBinding
import io.github.kabirnayeem99.thecat.domain.entity.Cat

class CatAdapter : PagingDataAdapter<Cat, CatAdapter.CatViewHolder>(CatComparator) {

    class CatViewHolder(val binding: GridItemCatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = GridItemCatBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val currentCat = getItem(position)
        holder.binding.cat = currentCat
        holder.binding.ivCatImage.load(currentCat?.imageUrl ?: "") {
            crossfade(true)
        }
    }


    private val diffCallback = object : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitCatList(catList: List<Cat>) {
        differ.submitList(catList)
    }


    object CatComparator : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat) =
            oldItem == newItem
    }
}