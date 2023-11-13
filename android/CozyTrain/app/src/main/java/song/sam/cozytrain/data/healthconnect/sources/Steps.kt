package song.sam.cozytrain.data.healthconnect.sources

import android.util.Log
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import song.sam.cozytrain.data.healthconnect.HealthConnectSource
import java.time.Instant
import javax.inject.Inject

/**
 * Implementation of Steps datasource implementing [HealthConnectSource]
 * @param healthConnectClient: proportionate HealthConnect's read and write primitives
 */

class Steps @Inject constructor(
    private val healthConnectClient: HealthConnectClient
) : HealthConnectSource<StepsRecord>(healthConnectClient) {
    override val readPermissions = setOf(
        HealthPermission.getReadPermission(StepsRecord::class)
    )

    override suspend fun readSource(startTime: Instant, endTime: Instant): List<StepsRecord> {
        val stepsRequest = ReadRecordsRequest(
            recordType = StepsRecord::class,
            timeRangeFilter = TimeRangeFilter.between(startTime, endTime),
            ascendingOrder = false
        )
        val stepsItems = healthConnectClient.readRecords(stepsRequest)
        Log.d("Steps readSource", "${stepsItems.records}")
        return stepsItems.records
    }
}