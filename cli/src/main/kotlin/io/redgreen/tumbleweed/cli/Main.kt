package io.redgreen.tumbleweed.cli

import ch.qos.logback.classic.Logger as LogbackLogger
import ch.qos.logback.classic.Level
import io.redgreen.tumbleweed.cli.dev.convert.ConvertCommand
import io.redgreen.tumbleweed.cli.dev.diff.DiffCommand
import io.redgreen.tumbleweed.cli.dev.json.JsonCommand
import io.redgreen.tumbleweed.cli.dev.view.ViewCommand
import io.redgreen.tumbleweed.cli.watch.WatchCommand
import kotlin.system.exitProcess
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option

const val DEFAULT_PORT = 7070

@Command(
  name = "twd",
  subcommands = [
    WatchCommand::class,
    JsonCommand::class,
    ViewCommand::class,
    ConvertCommand::class,
    DiffCommand::class,
  ],
)
class TumbleweedCommand {
  @Suppress("unused") // Used by picocli.
  @Option(
    names = ["--debug", "-d"],
    description = ["turns on debug level logging"],
    defaultValue = "false",
  )
  fun setDebug(debug: Boolean) {
    val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as LogbackLogger
    val level = if (debug) Level.DEBUG else Level.INFO
    logger.level = level
    logger.debug("Debug mode set to {}", debug)
  }
}

fun main(args: Array<String>) {
  val status = CommandLine(TumbleweedCommand())
    .execute(*args)
  exitProcess(status)
}
