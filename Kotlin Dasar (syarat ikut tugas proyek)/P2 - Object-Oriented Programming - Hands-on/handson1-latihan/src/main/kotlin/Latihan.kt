// Hands-on 1: Class & Inheritance
// Tugas: Buat hierarki class kendaraan menggunakan open class, primary constructor,
// dan override fungsi. Vehicle adalah base class, Car dan Motorcycle adalah turunannya.

// TODO 1: Jadikan class ini "open" agar bisa diturunkan (inherited).
// Primary constructor sudah punya property name (val) dan maxSpeed (val, dalam km/h).
open class Vehicle(val name: String, val maxSpeed: Int) {

    // TODO 2: Jadikan fungsi ini "open" agar bisa di-override oleh subclass.
    open fun describe(): String {
        return "$name dapat melaju hingga $maxSpeed km/h"
    }
}

// TODO 3: Buat class Car sebagai turunan dari Vehicle.
// Constructor Car menerima name dan jumlah pintu (numberOfDoors: Int),
// lalu meneruskan (name, maxSpeed = 180) ke constructor Vehicle.
// Override describe() untuk menambahkan info jumlah pintu.

class Car(name: String, val numberOfDoors: Int) : Vehicle(name, maxSpeed = 180) {
    override fun describe(): String {
        return "$name dapat melaju hingga $maxSpeed km/h dan punya $numberOfDoors pintu"
    }
}

// TODO 4: Buat class Motorcycle sebagai turunan dari Vehicle.
// Constructor Motorcycle menerima name dan hasSidecar: Boolean,
// lalu meneruskan (name, maxSpeed = 220) ke constructor Vehicle.
// Override describe() untuk menambahkan info sidecar.

class Motorcycle(name: String, val hasSidecar: Boolean) : Vehicle(name, maxSpeed = 220) {
    override fun describe(): String {
        val sidecarText = if (hasSidecar) "dengan sidecar" else "tanpa sidecar"
        return "$name dapat melaju hingga $maxSpeed km/h ($sidecarText)"
    }
}

fun main() {
    val vehicles = listOf<Vehicle>(
        // TODO 5: Buat 1 instance Car dan 1 instance Motorcycle.
        Car("Toyota", numberOfDoors = 4),
        Motorcycle("Ninja", hasSidecar = false)
    )

    // Polymorphism
    vehicles.forEach { println(it.describe()) }
}

// Output:
// Toyota dapat melaju hingga 180 km/h dan punya 4 pintu
// Ninja dapat melaju hingga 220 km/h (tanpa sidecar)