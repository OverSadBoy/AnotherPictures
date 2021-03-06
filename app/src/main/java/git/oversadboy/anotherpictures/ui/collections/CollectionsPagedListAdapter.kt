package git.oversadboy.anotherpictures.ui.collections

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import git.oversadboy.anotherpictures.R
import git.oversadboy.anotherpictures.model.pojo.CollectionImage
import kotlinx.android.synthetic.main.list_collectons.view.*

class CollectionsPagedListAdapter(
    private val context: Context,
    private val collectionClickListener: (CollectionImage) -> Unit
) :
    PagedListAdapter<CollectionImage, CollectionsPagedListAdapter.CollectionsPagedViewHolder>(
        DiffCollection()
    ) {

    inner class CollectionsPagedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(i: Int) {
            val collection = getItem(i)
            itemView.apply {
                title_collection.text = collection?.title
                num_s.text = getNumImage(collection?.total_photos!!)
                image_for_collection.setOnClickListener { collectionClickListener(collection) }
            }
            Glide.with(context).load(collection?.cover_photo?.urls?.regular)
                .thumbnail(0.1f)
                .into(itemView.image_for_collection)
        }

        private fun getNumImage(totalPhotos: Int) = "$totalPhotos photo"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsPagedViewHolder {
        return CollectionsPagedViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.list_collectons, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CollectionsPagedViewHolder, position: Int) {
        holder.bind(position)
    }

}

class DiffCollection : DiffUtil.ItemCallback<CollectionImage>() {
    override fun areItemsTheSame(oldItem: CollectionImage, newItem: CollectionImage): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CollectionImage, newItem: CollectionImage): Boolean =
        oldItem.title == newItem.title
}