package com.artlite.skd.chips.ui.abs

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.artlite.skd.chips.core.SdkChips
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Base activity class.
 */
abstract class AbsActivity(@LayoutRes private val layout: Int): AppCompatActivity() {

    /** Handler for thread execution. */
    private val mainHandler = Handler(Looper.getMainLooper())

    /**
     * Create activity functional.
     * @param bundle Bundle? instance.
     */
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(layout)
        this.main { onActivityCreated(bundle) }
    }

    /**
     * On Destroy functional.
     */
    override fun onDestroy() {
        super.onDestroy()
        this.onActivityDestroyed()
    }

    /**
     * Method which provide the activity created functional.
     * @param bundle Bundle? instance.
     */
    abstract fun onActivityCreated(bundle: Bundle?)

    /**
     * Method which provide the action when activity destroyed.
     */
    abstract fun onActivityDestroyed()

    /**
     * Method which provide the execute functional.
     * @param background thread action.
     * @param main thread action.
     */
    protected fun execute(background: () -> Unit, main: () -> Unit) {
        this.lifecycleScope.launch(Dispatchers.IO) {
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
        this.lifecycleScope.launch(Dispatchers.IO) {
            delay(delay)
            withContext(Dispatchers.Main) { action() }
        }
    }

    /**
     * Method which provide to play haptic.
     */
    protected fun playHaptic() = SdkChips.Managers.haptic.playHaptic()

}