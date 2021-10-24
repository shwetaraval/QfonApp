package com.qfonapp.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.qfonapp.utils.ImageLoadingUtils
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10

public class BindingHelper {

    companion object {
        @BindingAdapter(value = ["imageUrl", "placeHolderDrawable"], requireAll = false)
        @JvmStatic
        public fun loadImage(view: ImageView, path: String?, @DrawableRes placeHolder: Int) {
            ImageLoadingUtils.loadImage(view, path, placeHolder)
        }

        @BindingAdapter(value = ["pretty_count"])
        @JvmStatic
        public fun showPrettyCount(view: TextView, strNumber: String?) {
            strNumber?.let {
                if (it.isEmpty())
                    return

                var suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
                var number = it.toInt()
                var numValue = number.toDouble()
                var value = floor(log10(numValue))
                var base = (value / 3).toInt();
                var formattedText: String = if (value >= 3 && base < suffix.size) {
                    DecimalFormat("#0.0").format(
                        numValue / Math.pow(
                            10.0,
                            (base * 3).toDouble()
                        )
                    ) + suffix[base];
                } else {
                    DecimalFormat("#,##0").format(numValue);
                }
                view.text = formattedText
            }
        }
    }
}