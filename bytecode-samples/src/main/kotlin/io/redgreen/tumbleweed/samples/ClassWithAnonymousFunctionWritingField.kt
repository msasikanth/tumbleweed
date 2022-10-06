@file:Suppress("unused", "RedundantLambdaOrAnonymousFunction")

package io.redgreen.tumbleweed.samples

class ClassWithAnonymousFunctionWritingField {
  var counter: Int = 0

  fun incrementCounter() {
    { counter++ }.invoke()
  }
}
