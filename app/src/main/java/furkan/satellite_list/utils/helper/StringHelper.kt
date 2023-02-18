package furkan.satellite_list.utils.helper

fun isSearchable(input: String): Boolean {
    val letterCount = input.count { it.isLetter() }
    return letterCount == 0 || letterCount > 2
}