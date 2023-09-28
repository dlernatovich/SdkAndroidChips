package com.artlite.skd.chips.ui.abs

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.artlite.skd.chips.core.SdkChips
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Base view class.
 * @property _view View instance.
 * @property mainHandler Handler instance.
 */
abstract class AbsView: LinearLayout {

    /** Instance of the top Activity. */
    protected val activity get() = SdkChips.Managers.activity.topActivity

    /** Instance of the [View]. */
    private lateinit var _view: View

    /** Handler for thread execution. */
    private val mainHandler = Handler(Looper.getMainLooper())

    /** View constructor. */
    constructor(it: Context) : this(it, null)

    /** View constructor. */
    constructor(it: Context, it1: AttributeSet?) : this(it, it1, 0)

    /** View constructor. */
    constructor(it: Context, it1: AttributeSet?, it2: Int) : super(it, it1, it2) {
        this.onInitView(it, it1, it2)
    }

    /**
     * Method which provide the view initialization.
     * @param context Context
     * @param attrs AttributeSet?
     * @param defStyleAttr Int
     */
    private fun onInitView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        this._view = LayoutInflater.from(context).inflate(getLayoutId(), this, true)
        attrs?.let { onApplyAttributes(context, it, defStyleAttr) }
        this.main { onCreateView() }
    }

    /**
     * Method which provide to apply attributes.
     * @param context Context instance.
     * @param attrs AttributeSet instance.
     * @param defStyleAttr Int instance.
     */
    open fun onApplyAttributes(context: Context, attrs: AttributeSet, defStyleAttr: Int) {}

    /** On detach from window functionality. */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (isAttachedToWindow) onDismissed()
    }

    /** Get layout ID functional. */
    abstract fun getLayoutId(): Int

    /** On create view functional. */
    abstract fun onCreateView()

    /** Method which provide the dismissed functional. */
    abstract fun onDismissed()

    /**
     * Method which provide to find view by id.
     * @param id Int value.
     * @return T? view instance.
     */
    protected fun <T: View> find(id: Int): T = _view.findViewById(id)

    /**
     * Method which provide the execute functional.
     * @param background thread action.
     * @param main thread action.
     */
    protected fun execute(background: () -> Unit, main: () -> Unit) {
        (context as? AppCompatActivity)?.lifecycleScope?.launch(Dispatchers.IO) {
            background()
            withContext(Dispatchers.Main) { main() }
        }
    }

    /**
     * Method which provide the post functional to main thread.
     * @param delay Long value.
     * @param action Function1<[@kotlin.ParameterName] Boolean, Unit>
     * @return Boolean
     */
    protected fun main(delay: Long = 100, action: () -> Unit) {
        (context as? AppCompatActivity)?.lifecycleScope?.launch(Dispatchers.IO) {
            delay(delay)
            withContext(Dispatchers.Main) { action() }
        }
    }

    /**
     * Method which provide to play haptic.
     */
    protected fun playHaptic() = SdkChips.Managers.haptic.playHaptic()

}