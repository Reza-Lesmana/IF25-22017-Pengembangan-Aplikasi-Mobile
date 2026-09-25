import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

// Hands-on 1: HTTP Server Sederhana
// Tugas: Buat HTTP server (pakai HttpServer bawaan JDK, tanpa framework)
// yang listen di port 8080 dan merespon GET /hello dengan teks
// "Hello, Kotlin Backend!" serta status code 200 OK.

fun main() {
    // TODO 1: Buat instance HttpServer yang listen di 0.0.0.0 port 8080
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    // TODO 2: Daftarkan context "/hello" dengan handler
    server.createContext("/hello") { exchange ->
        val body = "Hello, Kotlin Backend!".toByteArray()

        exchange.sendResponseHeaders(200, body.size.toLong())

        exchange.responseBody.use { outputStream ->
            outputStream.write(body)
        }
    }

    // TODO 3: Jalankan server
    server.start()
    println("Server berjalan di port 8080")

    // Server berhenti otomatis setelah 10 detik
    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan")
}