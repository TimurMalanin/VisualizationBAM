import htsjdk.samtools.SAMRecord
import htsjdk.samtools.SamReaderFactory
import java.io.File

class BAMFileReader(file: File) {
    private val samReader = SamReaderFactory.makeDefault().open(file)

    fun read(): List<SAMRecord> = samReader.use { reader ->
        reader.toList()
    }
}