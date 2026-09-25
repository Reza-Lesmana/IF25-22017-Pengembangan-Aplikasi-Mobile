// Hands-on 3: Sealed Class untuk State
// Tugas: Modelkan hasil pemanggilan network sebagai sealed class NetworkResult
// dengan 3 kemungkinan state: Loading, Success (membawa data), dan Error (membawa pesan).

sealed class NetworkResult {
    object Loading : NetworkResult()
    data class Success(val data: String) : NetworkResult()
    data class Error(val message: String) : NetworkResult()
}

fun describe(result: NetworkResult): String {
    return when (result) {
        is NetworkResult.Loading -> "Sedang memuat..."
        is NetworkResult.Success -> "Berhasil: ${result.data}"
        is NetworkResult.Error -> "Gagal: ${result.message}"
    }
}

fun main() {
    println(describe(NetworkResult.Loading))
    println(describe(NetworkResult.Success("Data pengguna berhasil diambil")))
    println(describe(NetworkResult.Error("Koneksi terputus")))
}