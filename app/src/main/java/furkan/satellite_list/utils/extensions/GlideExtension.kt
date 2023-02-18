package furkan.satellite_list.utils.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.loadImage(@DrawableRes res : Int){
    Glide.with(this)
        .load(res)
        .into(this)
}