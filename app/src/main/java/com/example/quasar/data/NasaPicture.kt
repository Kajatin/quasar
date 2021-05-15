package com.example.quasar.data

//@Entity(tableName = "nasaPictures")
data class NasaPicture(
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "date")
    val id: Int,
    val title: String = "Title",
    val description: String = "Description",
    val imageUrl: String = ""
) {
    fun getDate(): String {
        return id.toString()
    }
}
