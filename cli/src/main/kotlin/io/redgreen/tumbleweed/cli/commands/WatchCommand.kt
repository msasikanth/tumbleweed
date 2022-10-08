package io.redgreen.tumbleweed.cli.commands

import io.redgreen.tumbleweed.filesystem.CompiledClassFileFinder
import io.redgreen.tumbleweed.web.TumbleweedServer
import java.io.File
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

@Command(
  name = "watch",
  description = ["see the real-time structure of a JVM class in your browser"],
)
class WatchCommand : Runnable {
  @Parameters(
    index = "0",
    description = ["uniquely identifiable (partially or fully) qualified name of the class"],
    arity = "1",
  )
  lateinit var className: String

  @Option(
    names = ["-b", "--buildDir"],
    description = ["path to the build directory"],
    required = false,
  )
  var buildDir: File? = null

  @Option(
    names = ["-p", "--port"],
    description = ["port number to run the server on"],
    required = false,
  )
  var port: Int = 7070

  override fun run() {
    val classFilePath = CompiledClassFileFinder
      .find(className, (buildDir ?: File("")).absolutePath)
      ?: throw IllegalArgumentException("Class file not found for $className")

    TumbleweedServer().start(port, classFilePath.toFile())
  }
}