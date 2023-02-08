package com.example.learn_jetpack_compose.UIScreen.MainScreen.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.learn_jetpack_compose.Model.ProductModel
import com.example.learn_jetpack_compose.R
import com.example.learn_jetpack_compose.databinding.ItemProductsBinding

class ProductAdapter(
private val list: ArrayList<ProductModel.ProductInformation>,
private val onItemClickListener: OnProductClick,
private val context:Context,
private var isLoadingNext:Boolean
): RecyclerView.Adapter<ProductAdapter.ViewHolder>()
{


    inner class ViewHolder(item:View):RecyclerView.ViewHolder(item){

        @SuppressLint("SetTextI18n")
        fun bindView(productInformation:ProductModel.ProductInformation, position: Int){
           val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)
           val txtPrice = itemView.findViewById<TextView>(R.id.txt_price)
           val txtDiscount = itemView.findViewById<TextView>(R.id.txt_discount)
           val txtIndex = itemView.findViewById<TextView>(R.id.txt_index)

           val imgProduct = itemView.findViewById<ImageView>(R.id.img_product)
           val card = itemView.findViewById<View>(R.id.layout_card)

           card.setOnClickListener {
               onItemClickListener.itemClick(position = position)
           }

           txtTitle.text = productInformation.title
           txtPrice.text = "${productInformation.price} $"
           txtDiscount.text ="${productInformation.discountPercentage} %"

            Glide.with(context).load(productInformation.images[0]).into(imgProduct)

            if (isLoadingNext && position==list.size-1){
                itemView.findViewById<View>(R.id.progress_more).visibility = View.VISIBLE
            }
            else{
                itemView.findViewById<View>(R.id.progress_more).visibility = View.GONE
            }

            txtIndex.text = position.toString()
        }
    }



    interface OnProductClick{
        fun itemClick(position:Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
         LayoutInflater.from(parent.context).inflate(R.layout.item_products,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position],position)
    }
}