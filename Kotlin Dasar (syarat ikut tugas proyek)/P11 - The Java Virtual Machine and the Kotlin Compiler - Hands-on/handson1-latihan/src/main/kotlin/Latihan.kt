// Hands-on 1: Inline Function & Reified Generics
//
// Konteks: JVM menerapkan TYPE ERASURE pada generics — informasi tipe generik
// (mis. List<String> vs List<Int>) DIHAPUS saat dikompilasi menjadi bytecode
// .class, sehingga pada saat runtime JVM hanya melihat "List" biasa. Karena
// itu, fungsi generik biasa TIDAK BISA melakukan pengecekan `value is T`.
//
// Kotlin punya solusi: kombinasi `inline` + `reified`. Fungsi inline "disalin"
// (di-inline) langsung ke tempat pemanggilannya oleh compiler backend Kotlin
// SEBELUM menjadi bytecode, sehingga tipe T yang konkret (mis. String) ikut
// tersalin dan tidak pernah "dihapus" oleh JVM.
//
// Tugas: Lengkapi fungsi isType() di bawah agar bisa mengecek tipe value pada
// runtime.

inline fun <reified T> isType(value: Any): Boolean {
    return value is T
}

fun main() {
    println(isType<String>("Hello"))   // harus mencetak: true
    println(isType<Int>("Hello"))      // harus mencetak: false
    println(isType<String>(123))       // harus mencetak: false

    // Pertanyaan diskusi (jawab di komentar):
    // Kenapa `fun <T> isType(value: Any): Boolean = value is T` TIDAK BISA
    // dikompilasi tanpa `inline` + `reified`? Jelaskan hubungannya dengan
    // type erasure pada JVM bytecode.

    // Jawaban:
    // Karena JVM menerapkan type erasure pada generic type.
    // Akibatnya, tipe T tidak tersedia lagi saat runtime sehingga
    // pengecekan `value is T` tidak dapat dilakukan.
    // Dengan `inline` + `reified`, tipe T yang konkret diketahui oleh
    // compiler dan disisipkan ke kode saat proses inline.
}