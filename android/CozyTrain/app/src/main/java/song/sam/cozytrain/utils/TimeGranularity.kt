package song.sam.cozytrain.utils

import androidx.annotation.StringRes
import song.sam.cozytrain.R

enum class TimeGranularity(@StringRes val label: Int) {
    Day(R.string.day),
    Week(R.string.week),
    Month(R.string.month);

    companion object {
        /**
         * Get all [TimeGranularity]
         * @return [List] of [TimeGranularity]
         */
        fun get(): List<TimeGranularity> = values().asList()
    }
}
