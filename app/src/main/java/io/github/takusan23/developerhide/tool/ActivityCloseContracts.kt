package io.github.takusan23.developerhide.tool

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ActivityCloseContracts : ActivityResultContract<Intent, Unit>() {
    override fun createIntent(context: Context, input: Intent): Intent {
        // launch()のIntentがinput
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?) {
        // とくになし
    }
}
