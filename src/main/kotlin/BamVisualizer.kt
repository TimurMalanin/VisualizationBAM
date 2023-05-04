import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import javax.swing.*
import javax.swing.filechooser.FileFilter

class BamVisualizer : JFrame(), ActionListener {

    private val uploadFileButton = JButton("Upload BAM file")
    private val inputRegionField = JTextField("chrM:1-1000")
    private val visualizeButton = JButton("Visualize")
    private val progressBar = JProgressBar()
    private var bamFile: File? = null
    private val firstTrackPanel = JPanel()
    private val secondTrackPanel = JPanel()

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(800, 600)
        layout = BorderLayout()

        add(createTopPanel(), BorderLayout.NORTH)
        add(createTracksPanel(), BorderLayout.CENTER)
        add(progressBar, BorderLayout.SOUTH)

        uploadFileButton.addActionListener(this)
        visualizeButton.addActionListener(this)
    }

    private fun createTopPanel(): JPanel = JPanel().apply {
        layout = BoxLayout(this, BoxLayout.X_AXIS)
        add(Box.createHorizontalStrut(10))
        add(uploadFileButton)
        add(Box.createHorizontalStrut(10))
        add(inputRegionField)
        add(Box.createHorizontalStrut(10))
        add(visualizeButton)
    }

    private fun createTracksPanel(): JPanel = JPanel().apply {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        firstTrackPanel.preferredSize = Dimension(800, 200)
        firstTrackPanel.background = Color.WHITE
        add(firstTrackPanel)
        secondTrackPanel.preferredSize = Dimension(800, 200)
        secondTrackPanel.background = Color.WHITE
        add(secondTrackPanel)
    }

    override fun actionPerformed(event: ActionEvent) {
        when (event.source) {
            uploadFileButton -> uploadFileButtonHandler()
            visualizeButton -> visualizeButtonHandler()
        }
    }

    private fun visualizeButtonHandler() {
        TODO()
    }

    private fun uploadFileButtonHandler() {
        class BamFileFilter : FileFilter() {
            override fun accept(file: File): Boolean {
                return file.name.endsWith(".bam") || file.isDirectory
            }

            override fun getDescription(): String {
                return "BAM files (*.bam)"
            }
        }

        val chooser = JFileChooser()
        chooser.fileFilter = BamFileFilter()
        val returnVal = chooser.showOpenDialog(this)
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            bamFile = chooser.selectedFile
        }
    }

}

private fun createAndShowGUI() {
    val frame = BamVisualizer()
    frame.isVisible = true
}

fun main() {
    EventQueue.invokeLater(::createAndShowGUI)
}