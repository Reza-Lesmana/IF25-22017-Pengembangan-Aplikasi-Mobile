import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.findAnnotation

// Hands-on 3: Custom Annotation + Reflection Validator
// Tugas: Buat annotation class @Required, lalu fungsi validate(obj) yang
// mengecek semua property beranotasi @Required tidak boleh null atau
// String kosong/blank. Kembalikan daftar NAMA FIELD yang gagal validasi.
//
// CATATAN: File ini SENGAJA belum bisa di-compile sampai kamu melengkapi
// semua TODO di bawah — itu normal untuk latihan ini!

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Required

data class RegistrasiForm(
    @Required val nama: String,
    @Required val email: String,
    val alamat: String? // opsional, tidak wajib diisi
)

fun validate(obj: Any): List<String> {
    val gagal = mutableListOf<String>()

    // TODO 2: Iterasi memberProperties dari obj::class
    obj::class.memberProperties.forEach { property ->

        // TODO 3: Cek apakah property memiliki annotation @Required
        val required = property.findAnnotation<Required>()

        if (required != null) {

            // TODO 4: Ambil nilai property
            val value = property.getter.call(obj)

            // TODO 5: Cek null atau String kosong/blank
            if (value == null || (value is String && value.isBlank())) {
                gagal.add(property.name)
            }
        }
    }

    return gagal
}

fun main() {
    val formValid = RegistrasiForm("Siti", "siti@mail.com", null)
    println("Form valid -> gagal: ${validate(formValid)}") // gagal: []

    val formInvalid = RegistrasiForm("", "budi@mail.com", "Jl. Merdeka")
    println("Form invalid -> gagal: ${validate(formInvalid)}") // gagal: [nama]
}