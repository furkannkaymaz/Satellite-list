package furkan.satellite_list.utils.response

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null,
    var state: UIStatus = UIStatus.LOADING
) {
    class Success<T>(data: T, state: UIStatus) : Resource<T>(data, null, state)
    class Error(message: String?, state: UIStatus) : Resource<Nothing>(null, message, state)
    class Loading(state: UIStatus) : Resource<Nothing>(null, null, state)
}