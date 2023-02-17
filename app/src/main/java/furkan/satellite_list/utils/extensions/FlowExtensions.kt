package furkan.satellite_list.utils.extensions

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

suspend fun <T> StateFlow<T>.listen(action: suspend (value: T) -> Unit) {
    this@listen.collectLatest(action)
}