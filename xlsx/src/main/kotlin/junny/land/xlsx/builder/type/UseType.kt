package junny.land.xlsx.builder.type

import junny.land.xlsx.builder.type.csv.CsvType
import junny.land.xlsx.datas.XlsxFields
import junny.land.xlsx.datas.XlsxHeaders
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

interface UseType {
    fun convert(headers: XlsxHeaders, datas: List<XlsxFields>): Path
}



class PlainType(
    val extension:String = ".txt"
) : UseType {
    private val myType = ExtractType.PLAIN

    override fun convert(headers: XlsxHeaders, datas: List<XlsxFields>) : Path {
        return Files.createTempFile(UUID.randomUUID().toString(), extension)
    }
}


