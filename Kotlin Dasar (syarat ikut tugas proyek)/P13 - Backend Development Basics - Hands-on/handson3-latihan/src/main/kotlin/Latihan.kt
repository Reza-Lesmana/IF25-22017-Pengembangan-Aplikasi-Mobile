import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress

// Hands-on 3: Request Handling & Status Code
// Tugas:
//   1. Handle POST /echo — baca body request, lalu kembalikan body yang
//      sama persis sebagai response (status 200).
//   2. Tambahkan handler default ("/") yang mengembalikan status 404
//      Not Found untuk semua path yang tidak dikenal.
//
// CATATAN: File ini SENGAJA belum bisa di-compile sampai kamu melengkapi
// semua TODO di bawah — itu normal untuk latihan ini!

fun main() {
    val server = HttpServer.create(InetSocketAddress("0.0.0.0", 8080), 0)

    server.createContext("/echo") { exchange ->
        if (exchange.requestMethod != "POST") {
            // TODO 1: Status 405 Method Not Allowed
            exchange.sendResponseHeaders(405, -1)
            exchange.close()
            return@createContext
        }

        // TODO 2: Baca seluruh request body menjadi ByteArray
        val requestBytes: ByteArray = exchange.requestBody.readBytes()
        exchange.requestBody.close()

        // TODO 3: Kirim balik requestBytes sebagai response body
        exchange.sendResponseHeaders(200, requestBytes.size.toLong())

        exchange.responseBody.use { outputStream ->
            outputStream.write(requestBytes)
        }
    }

    server.createContext("/") { exchange ->
        // TODO 4: Handler default untuk path yang tidak dikenal
        val path = exchange.requestURI.path
        val body = "Not Found: $path".toByteArray()

        exchange.sendResponseHeaders(404, body.size.toLong())

        exchange.responseBody.use { outputStream ->
            outputStream.write(body)
        }
    }

    server.start()
    println("Server berjalan di http://localhost:8080")
    println("Coba: curl -X POST -d 'halo dunia' http://localhost:8080/echo")
    println("Coba: curl -i http://localhost:8080/tidak-ada")

    Thread.sleep(10_000)
    server.stop(0)
    println("Server dihentikan")
}