package com.muntazif.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.muntazif.myapplication.R
import com.muntazif.myapplication.fragments.PostDetailsFragment
import com.muntazif.myapplication.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.images_item_layout.view.*

class MyImagesAdapter(private val mContext: Context,
        /* mantıklı olan aşağıdaki ama adam diğerini yaptı
        private var */
                      mPost : List<Post>)
    : RecyclerView.Adapter<MyImagesAdapter.ViewHolder?>() {

    private var mPost : List<Post>? = null

    init {
        this.mPost = mPost
    }

        inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView){

            var postImage : ImageView

            init {
                postImage = itemView.findViewById(R.id.post_image)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.images_item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = mPost!![position]
        Picasso.get().load(post.getPostimage()).into(holder.postImage)

        holder.postImage.setOnClickListener {
            val editor = mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit()
            editor.putString("postId", post.getPostid())
            editor.apply()
            (mContext as FragmentActivity).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, PostDetailsFragment()).commit()
        }

    }

    override fun getItemCount(): Int {
        return mPost!!.size
    }
}