
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.fragment.app.DialogFragment

open class GenericBottomSheetDialog : DialogFragment() {

    private var maxHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        setStyle(STYLE_NO_TITLE, R.style.CustomBottomSheetDialog)
    }

    override fun onStart() {
        super.onStart()
        maxHeight = ((resources.displayMetrics.heightPixels) / 1.2).toInt()
        dialog?.window?.setGravity(Gravity.BOTTOM)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setViewTreeObserver(view)
    }


    private fun setViewTreeObserver(view: View?) {
        view?.viewTreeObserver?.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    if (view.height >= maxHeight) {
                        view.layoutParams.height = maxHeight
                        dialog?.window?.setLayout(
                            WindowManager.LayoutParams.MATCH_PARENT,
                            maxHeight
                        )
                    }
                }
            }
        )
    }
}
