package furkan.satellite_list.utils.extensions

import kotlinx.coroutines.*

    fun CoroutineScope.launchOnIO(block : suspend CoroutineScope.() -> Unit) : Job {
        return launch(Dispatchers.IO, block = block)
    }
