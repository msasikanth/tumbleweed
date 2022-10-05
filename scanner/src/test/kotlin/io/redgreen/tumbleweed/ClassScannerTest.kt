package io.redgreen.tumbleweed

import java.lang.StringBuilder
import org.approvaltests.Approvals
import org.junit.jupiter.api.Test

class ClassScannerTest {
  @Test
  fun `it can scan an empty class`() {
    // given
    val emptyClass = ClassFileLocation(
      compiledClassesDirectory = "../bytecode-samples/build/classes/kotlin/main",
      fqClassName = "io.redgreen.tumbleweed.samples.EmptyClass"
    )

    // when
    val classStructure = ClassScanner.scan(emptyClass)

    // then
    Approvals.verify(classStructure.printable)
  }
}

private val ClassStructure.printable: Any
  get() {
    with(this) {
      val classStructurePrintableBuilder = StringBuilder()

      classStructurePrintableBuilder
        .appendLine("Package: $`package`")
        .appendLine("Class: $className")

      return classStructurePrintableBuilder.toString()
    }
  }